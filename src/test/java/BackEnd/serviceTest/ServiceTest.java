package BackEnd.serviceTest;

import BackEnd.dto.EmployeeDto;
import BackEnd.entity.Employee;
import BackEnd.repository.EmployeeRepository;
import BackEnd.service.EmployeeService;
import BackEnd.service.impl.ImplService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.BDDMockito.given;




@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private ImplService implService;
    @Autowired
    private ModelMapper modelMapper;
private Employee employee;

    private EmployeeDto employeeDto;


    @BeforeEach
    public void setUp(){
        employeeDto = EmployeeDto.builder()
                .id(1l)
                .firstName("Ajay")
                .email("AjayDeno@gmail.com")
                .lastName("Deno")
                .build();
    }
    @Test
   public void  givenId_WhenFindByID_ThenGetEmployee(){
        //givrn
        given(employeeRepository.findById(employeeDto.getId())).willReturn(Optional.ofNullable(employee));
       //when
      EmployeeDto employee1= implService.getUserById(employeeDto.getId());
      //then
        Assertions.assertNotNull(employee1);


    }
}
