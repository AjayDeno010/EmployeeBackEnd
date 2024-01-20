package BackEnd.service;

import BackEnd.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getUserById(Long id);
    EmployeeDto createUser(EmployeeDto employeeDto);
   void deleteUser(Long id);

    EmployeeDto updateUser(EmployeeDto employeeDto,Long id);

}
