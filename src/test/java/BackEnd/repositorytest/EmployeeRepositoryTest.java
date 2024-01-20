package BackEnd.repositorytest;

import BackEnd.entity.Employee;
import BackEnd.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

//    @BeforeEach
//    public void setUp() {
//        employee = Employee.builder()
//                .firstName("Ajay")
//                .email("AjayDeno@gmail.com")
//                .lastName("Deno")
//                .build();
//    }

    @Test
    @Transactional
    public void givenEmployeeDto_WhenSave_ThenReturnSavedEmployee() {
        // given
        Employee employee = Employee.builder()
                .firstName("Ajay")
                .email("AjayDeno@gmail.com")
                .lastName("Deno")
                .build();

        // when
        Employee savedEmployee = employeeRepository.save(employee);

        // then
        Assertions.assertNotNull(savedEmployee);
        // Add further assertions if needed
    }

    @Test
    public void given_WhenFindAll_ThenGetAllValuesFromTable(){
        //given
        Employee employee = Employee.builder()
                .firstName("Ajay")
                .email("AjayDeno@gmail.com")
                .lastName("Deno")
                .build();
        employeeRepository.save(employee);
        Employee employee1 = Employee.builder()
                .firstName("Ajay")
                .email("AjayDeno@gmail.com")
                .lastName("Deno")
                .build();

        //when
       List<Employee> employeeList= employeeRepository.findAll();

       //then
        Assertions.assertNotNull(employeeList);

    }
}
