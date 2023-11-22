package br.com.spolador.services;

import br.com.spolador.dto.PersonDepartmentDto;
import br.com.spolador.dto.PersonDto;
import br.com.spolador.entities.Department;
import br.com.spolador.entities.Person;
import br.com.spolador.repositories.DepartmentRepository;
import br.com.spolador.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public PersonDepartmentDto insert(PersonDepartmentDto dto) {

        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        Department dept = departmentRepository.getReferenceById(dto.getDepartment().getId());

        //Department dept = new Department();
        //dept.setId(dto.getDepartment().getId());

        entity.setDepartment(dept);

        entity = repository.save(entity);

        return new PersonDepartmentDto(entity);
    }


    public PersonDto insert(PersonDto dto) {

        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        //Department dept = departmentRepository.getReferenceById(dto.getDepartmentId());

        Department dept = new Department();
        dept.setId(dto.getDepartmentId());

        entity.setDepartment(dept);

        entity = repository.save(entity);

        return new PersonDto(entity);
    }

}
