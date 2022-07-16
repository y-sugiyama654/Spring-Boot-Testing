package net.javaguides.springboottesting.repository;

import net.javaguides.springboottesting.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("jUnit test for save employee operation")
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("jUnit test for get all employee operation")
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("fumio")
                .lastName("kishida")
                .email("fuga@gmail.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("junit test for get employee by id operation")
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        Assertions.assertThat(employeeDb).isNotNull();
    }

    @Test
    @DisplayName("jUnit test for get employee by email operation")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        Assertions.assertThat(employeeDb).isNotNull();
    }

    @Test
    @DisplayName("jUnit test for update employee operation")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("fuga@gmail.com");
        savedEmployee.setFirstName("suzuki");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("fuga@gmail.com");
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("suzuki");
    }

    @Test
    @DisplayName("jUnit test for delete employee operation")
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        // employeeRepository.delete(employee);
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getId());

        // then - verify the output
        Assertions.assertThat(deletedEmployee).isEmpty();
    }

    @Test
    @DisplayName("jUnit test for custom query using JPQL with index")
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        employeeRepository.save(employee);
        String firstName = "yuta";
        String lastName = "sugiyama";

        // when - action or the behaviour that we are going test
        Employee findEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        Assertions.assertThat(findEmployee).isNotNull();
        Assertions.assertThat(findEmployee.getFirstName()).isEqualTo("yuta");
        Assertions.assertThat(findEmployee.getLastName()).isEqualTo("sugiyama");
    }

    @Test
    @DisplayName("jUnit test for custom query using JPQL with named params")
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("yuta")
                .lastName("sugiyama")
                .email("hoge@gmail.com")
                .build();

        employeeRepository.save(employee);
        String firstName = "yuta";
        String lastName = "sugiyama";

        // when - action or the behaviour that we are going test
        Employee findEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        Assertions.assertThat(findEmployee).isNotNull();
        Assertions.assertThat(findEmployee.getFirstName()).isEqualTo("yuta");
        Assertions.assertThat(findEmployee.getLastName()).isEqualTo("sugiyama");
    }
}
