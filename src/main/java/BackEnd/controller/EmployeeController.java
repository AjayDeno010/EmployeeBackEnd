package BackEnd.controller;

import BackEnd.dto.EmployeeDto;
import BackEnd.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createUser(
            @RequestBody EmployeeDto employeeDto,
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader) {

        // Check if Authorization header is present
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            // Decode the Base64-encoded credentials
            String credentials = new String(
                    Base64.getDecoder().decode(authorizationHeader.substring("Basic ".length())),
                    StandardCharsets.UTF_8
            );

            // Split the credentials into username and password
            String[] parts = credentials.split(":");
            String username = parts[0];
            String password = parts[1];

            // Now you have the username and password, you can validate them as needed
            // For simplicity, you can use the values in your authentication logic

            // Proceed with creating the user if authentication is successful
            EmployeeDto createdUser = employeeService.createUser(employeeDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            // No Authorization header provided
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{id}/getUser")
    public ResponseEntity<EmployeeDto> getUser(@PathVariable   Long id){
      EmployeeDto employeeDto=  employeeService.getUserById(id);
      return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{id}/updateUser")
    public ResponseEntity<EmployeeDto> updateUser(@RequestBody EmployeeDto employeeDto,@PathVariable Long id){
      EmployeeDto updatedUser=  employeeService.updateUser(employeeDto,id);
      return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("{id}/delete")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteMapping(Long id){
        employeeService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.NO_CONTENT);
    }

}
