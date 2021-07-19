# ExerciciosBootcamp

Pasta contendo as soluções de exercicios de Carlos André Feldmann Júnior


# Common
Alguns projetos utilizam a biblioteca [./Common](./Common), por isso é necessário instalar ela no repositório local:
```bash
cd Common/
mvn clean install
```
## Utilitários encontrados:

### JsonRepository
Permite criar um repositorio de qualquer entidade e armazena de forma automática em JSON.

<details> 
  <summary>Exemplo de uso</summary>


```java
class Product implements Identifiable{
    private Long id;
    private String name;

    public Produto(String name){
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

@Repository
class ProductRepository extends JsonRepository<Product>{
    @AutoWired
    public ProductRepository(ObjectMapper mapper){
        super(mapper,"storage/produtos.json");
    }
}

class ProductService{
    public void something(){
        Product product = ...;
        repository.save(product);
        repository.findById(1); // returns Optional<Product>
        repository.delete(product); 
        repository.all(); // returns List<Product>
    }
}
```
</details>

---

## Modulos
* 🟢 Modulo 5: Fundamentos de Java + OOP [./Modulo5](./Modulo5)
* 🟢 Modulo 6: Desenvolvimento web com Spring [./Modulo6](./Modulo6)
* 🟢 Modulo 10: Testing automatizado com junit [./Diplomas](./Diplomas)
* 🔵 Modulo 13: Introdução a base da dados [./Modulo13](./Modulo13)





> 🟢 Finalizado  
> 🔵 Em andamento  
> 🔴 Não iniciado  