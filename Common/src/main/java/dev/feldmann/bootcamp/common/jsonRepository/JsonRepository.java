package dev.feldmann.bootcamp.common.jsonRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonRepository<T extends Identifiable> {


    private File file;
    private ObjectMapper mapper;

    private JsonStorage<T> cache = null;

    public JsonRepository(ObjectMapper mapper, String filePath) {
        this.file = new File(filePath);
        this.mapper = mapper;
    }


    private JsonStorage<T> getCache() throws IOException {
        if (cache == null) {
            this.cache = loadFromFile();
        }
        return cache;
    }


    public List<T> all() throws IOException {
        return new ArrayList<>(this.getCache().getEntities().values());
    }


    public Optional<T> findById(Long id) throws IOException {
        var cache = getCache();
        if (cache.getEntities().containsKey(id)) {
            return Optional.of(cache.getEntities().get(id));
        }
        return Optional.empty();
    }

    public void delete(T entity) throws IOException {
        if (entity.getId() != null) {
            getCache().getEntities().remove(entity.getId());
        }
    }

    public void save(T entity) throws IOException {
        if (entity.getId() == null) {
            entity.setId(getCache().getNewId());

        }
        getCache().getEntities().put(entity.getId(), entity);
        this.saveIntoFile(this.getCache());
    }


    private JsonStorage<T> loadFromFile() throws IOException {
        if (!this.file.exists()) {
            return new JsonStorage<T>(1L, new HashMap<>());
        }
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        ResolvableType generic = resolvableType.getGeneric(0);

        Class<T> aClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), JsonRepository.class);

        JavaType javaType = mapper.getTypeFactory().constructParametricType(JsonStorage.class, aClass);
        return mapper.readValue(file, javaType);
    }

    private void saveIntoFile(JsonStorage<T> storage) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        this.mapper.writeValue(file, storage);
    }


}



