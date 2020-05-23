package com.vault.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(VaultConfiguration.class)
@SpringBootApplication
public class VaultDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultDemoApplication.class, args);
	}

	@Autowired
	VaultConfiguration vaultConfiguration;

	@Bean
	public CommandLineRunner openVault() {
		return (args) -> {
			System.out.println(vaultConfiguration.getUsername() + "/" + vaultConfiguration.getPassword());
		};
	}
}
