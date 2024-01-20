package BackEnd.service.impl;

import BackEnd.dto.EmployeeDto;
import BackEnd.entity.Employee;
import BackEnd.repository.EmployeeRepository;
import BackEnd.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplService implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeDto getUserById(Long id) {
       Employee employee= employeeRepository.findById(id).get();
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto createUser(EmployeeDto employeeDto) {
   Employee employee= modelMapper.map(employeeDto,Employee.class);
  Employee savedUser= employeeRepository.save(employee);
        return modelMapper.map(savedUser,EmployeeDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto updateUser(EmployeeDto employeeDto,Long id) {
      Employee employee= employeeRepository.findById(id).get();
      employee.setId(employeeDto.getId());
      employee.setEmail(employeeDto.getEmail());
      employee.setFirstName(employeeDto.getFirstName());
      employee.setLastName(employeeDto.getLastName());
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
