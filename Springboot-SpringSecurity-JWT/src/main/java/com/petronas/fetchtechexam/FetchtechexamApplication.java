package com.petronas.fetchtechexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//SecurityAutoConfiguration.class ,
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
public class FetchtechexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchtechexamApplication.class, args);
    }
}
