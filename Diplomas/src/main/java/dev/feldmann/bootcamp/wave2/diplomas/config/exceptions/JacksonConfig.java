package dev.feldmann.bootcamp.wave2.diplomas.config.exceptions;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {


  @Bean
  @Primary
  public ObjectMapper configureObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
    return objectMapper;
  }
}
