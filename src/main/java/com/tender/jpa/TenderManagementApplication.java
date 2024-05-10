package com.tender.jpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.tender.jpa.pojo")
@EnableJpaRepositories("com.tender.jpa.repository")
public class TenderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenderManagementApplication.class, args);
	}




	/*@Autowired
	DepartmentRepository  departmentRepository;



	@Bean
	CommandLineRunner start(DepartmentRepository depatmentRepository){
		return args -> {
			Department department  = new Department( );
			department.setDepartmentName("salesforce");
			department.setDepartmentNumber(2);
			departmentRepository.save(department);
			Department department1  = new Department( );
			department1.setDepartmentName("B2C");
			department1.setDepartmentNumber(3);
			departmentRepository.save(department1);

			*//*Employee employee = new Employee().withAge(12D).withId(36).withDepartment(department1);
			tenderRepository.save(employee);*//*
		};
	}*/
}
