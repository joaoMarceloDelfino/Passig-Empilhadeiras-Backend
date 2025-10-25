package com.faculdade.passig_empilhadeiras;

import com.faculdade.passig_empilhadeiras.config.CreateDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PassigEmpilhadeirasApplication {

	public static void main(String[] args) {
        CreateDatabase.createDatabase();
		SpringApplication.run(PassigEmpilhadeirasApplication.class, args);
	}

}
