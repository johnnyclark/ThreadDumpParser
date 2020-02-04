package com.johnnyclark.threaddumpparser;

import com.johnnyclark.threaddumpparser.parser.TdParserDriver;
import com.johnnyclark.threaddumpparser.repository.TdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ThreadDumpParserApplication {
	private static final Logger log = LoggerFactory.getLogger(ThreadDumpParserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ThreadDumpParserApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TdRepository tdRepository) {
		return (args) -> {
			tdRepository.deleteAll();
			TdParserDriver driver = new TdParserDriver();
			driver.process(tdRepository);
			log.info("");
		};
	}
}
