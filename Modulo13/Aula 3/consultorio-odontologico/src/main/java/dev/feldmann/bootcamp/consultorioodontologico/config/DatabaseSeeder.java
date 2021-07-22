package dev.feldmann.bootcamp.consultorioodontologico.config;


import dev.feldmann.bootcamp.consultorioodontologico.repository.DentistRepository;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements ApplicationRunner {


  private final DentistRepository dentistRepository;
  private final DataSource dataSource;

  @Autowired
  public DatabaseSeeder(DentistRepository dentistRepository,
      DataSource dataSource) {
    this.dentistRepository = dentistRepository;
    this.dataSource = dataSource;
  }


  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (dentistRepository.count() > 0) {
      return;
    }
    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(
        false,
        false,
        "UTF-8",
        new ClassPathResource("initialData.sql")
    );
    resourceDatabasePopulator.execute(dataSource);


  }


}
