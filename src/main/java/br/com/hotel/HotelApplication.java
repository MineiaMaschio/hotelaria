package br.com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.entities.hospedagem", "br.com.entities.hospede"})
@EntityScan({"br.com.entities.hospedagem", "br.com.entities.hospede"})
@EnableJpaRepositories({"br.com.entities.hospedagem", "br.com.entities.hospede"})
public class HotelApplication {
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
}
