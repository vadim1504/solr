package by.vadim.spring.data.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@SpringBootApplication
@EnableSolrRepositories(basePackages = "by.vadim.spring.data.solr.repository", namedQueriesLocation = "classpath:solr-named-queries.properties", multicoreSupport = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}