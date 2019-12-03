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

//	@Autowired TdRepository tdRepository;

	public static void main(String[] args) {
		SpringApplication.run(ThreadDumpParserApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(TdRepository tdRepository) {
		return (args) -> {
			tdRepository.deleteAll();
			TdParserDriver driver = new TdParserDriver();
			driver.process(tdRepository);

			// save a few customers
//			tdRepository.save(TdEntity.fromTd(new Td(new TdHeader("abc"), ImmutableList.of(), new TdTrailer("def"))));
//			tdRepository.save(new Customer("Chloe", "O'Brian"));
//			tdRepository.save(new Customer("Kim", "Bauer"));
//			tdRepository.save(new Customer("David", "Palmer"));
//			tdRepository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
//			for (Customer customer : tdRepository.findAll()) {
//				log.info(customer.toString());
//			}
			log.info("");

			// fetch an individual customer by ID
//			Customer customer = tdRepository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			tdRepository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
			// for (Customer bauer : tdRepository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}
