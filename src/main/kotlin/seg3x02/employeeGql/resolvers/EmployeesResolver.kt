package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller

@Controller
class EmployeesResolver {
    // newEmployee, seems to be the only function called from client
    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: createEmployeeInput): Employee {
        if (input.name == null || input.dateOfBirth == null || input.city == null || input.salary == null) {
            throw Exception("Missing required fields")
        }

        val employee = Employee(name = input.name,
                                dateOfBirth = input.dateOfBirth,
                                city = input.city,
                                salary = input.salary)
        
        employee.UUID.randomUUID().toString()
        employeeRepository.save(employee)
        return employee;
    }
}
