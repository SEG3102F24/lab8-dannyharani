package seg3x02.employeeGql.resolvers

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.*

@Controller
class EmployeesResolver(private val employeesRepository: EmployeesRepository) {
    // newEmployee, seems to be the only function called from client
    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput): Employee {
        if (input.name == null || input.dateOfBirth == null || input.city == null || input.salary == null) {
            throw Exception("Missing required fields")
        }

        val employee = Employee(name = input.name,
                                dateOfBirth = input.dateOfBirth,
                                city = input.city,
                                gender = input.gender,
                                email = input.email,
                                salary = input.salary)
        
        employee.id = UUID.randomUUID().toString()
        employeesRepository.save(employee)
        return employee;
    }
}
