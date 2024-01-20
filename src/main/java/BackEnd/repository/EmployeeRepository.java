package BackEnd.repository;

import BackEnd.dto.EmployeeDto;
import BackEnd.entity.Employee;
import BackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Override
    <S extends Employee> S save(S entity);
    @Query("select e FROM Employee e where e.id = :id And e.firstName = :firstName")
  Employee  findByIdAndFirstName(@Param("id") Long id,@Param("firstName") String firstName);

    @Override
//    @Query("select e FROM Employee")
    List<Employee> findAll();

//   Optional<EmployeeDto> findByUserNameOrEmail(String userName, String email);


}
