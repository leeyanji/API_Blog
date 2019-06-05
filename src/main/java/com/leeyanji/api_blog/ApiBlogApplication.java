package com.leeyanji.api_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.leeyanji.api_blog"})
@EnableJpaRepositories(value = "com.leeyanji.api_blog.repository")
public class ApiBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBlogApplication.class, args);
	}

}
