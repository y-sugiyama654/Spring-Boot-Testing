package net.javaguides.springboottesting.repository;

import net.javaguides.springboottesting.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
}
