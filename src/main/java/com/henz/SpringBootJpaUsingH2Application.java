package com.henz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.henz.entity.Person;
import com.henz.jpa.PersonJpaRepository;

@SpringBootApplication
public class SpringBootJpaUsingH2Application implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonJpaRepository personJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaUsingH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//to show running sql, add spring.jpa.show-sql=true to prop file
		logger.info("user id 1 -> {}", personJpaRepository.findById(1));
		
		//id 3 will be ignored because we use hibernate db sequence
		logger.info("user id 3 insert -> {}", personJpaRepository.insert(new Person(3, "mani", "bärschwil", new Date())));
		logger.info("user id 4 insert -> {}", personJpaRepository.insert(new Person(4, "anita", "bärschwil", new Date())));
	}

}
