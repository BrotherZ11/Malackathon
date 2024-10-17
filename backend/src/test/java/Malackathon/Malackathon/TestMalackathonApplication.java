package Malackathon.Malackathon;

import org.springframework.boot.SpringApplication;

public class TestMalackathonApplication {

	public static void main(String[] args) {
		SpringApplication.from(MalackathonApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
