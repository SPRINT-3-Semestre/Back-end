package com.example.EditMatch;

import com.example.EditMatch.customer.CustomerRepository;
import com.example.EditMatch.s3.S3Services;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EditMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EditMatchApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			CustomerRepository customerRepository,
			PasswordEncoder passwordEncoder,
			S3Services s3Service){
		return args -> {
			s3Service.putObject(
					"fs-editmatch-custumer-test",
					"foo",
					"Hello World".getBytes()
			);

			byte[] obj = s3Service.getObject(
					"fs-editmatch-custumer-test",
					"foo"
			);

			System.out.println("Hooray: " + new String(obj));
		};
	}

}
