package com.devsuperior.aulalazy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.aulalazy.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT obj FROM Employee obj JOIN FETCH obj.department") // ja busca o departamento juntamente com o os employees
	List<Employee> findEmployeesWithDepartments();

	List<Employee> findByNameIgnoreCase(String name); // ignore case -> busca os nomes independente de maiusc ou minusc

//	findByNameContainingIgnoreCase -> containing -> pega todos os casos que contem a string ...?name="string" passada como parametro.
//	EXEMPLO: http://localhost:8080/employees?name=mar  --> pega todos os casos que possui "mar" no nome. Nesse caso, busca Maria e Marco
}
