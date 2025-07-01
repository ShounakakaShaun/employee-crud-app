package com.shaunstudies12.gmail.com.SpringProject.controllers;

//Operations on Employees
// GET /employees
// POST /employees
// DELETE /employees/{id}
import com.shaunstudies12.gmail.com.SpringProject.dto.EmployeeDTO;
import com.shaunstudies12.gmail.com.SpringProject.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path= "/employees")
public class EmployeeController {
    // Dummy in-memory list of employees
     private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //@GetMapping(path = "/employees/{id}") // already defined path above in request mapping above so no need to redefine again
    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeId ) {
        return employeeService.getEmployeeById(employeeId);
    }
    @PutMapping(path = "/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    /*
    //@GetMapping(path="/employees")  // already defined path above in request mapping above so no need to redefine again
    //to get this we type http://localhost:8080/employees?sortBy=name&limit=10
    @GetMapping()  // already define
    public String getData(@PathParam("sortBy") String sortBy,
                          @PathParam("limit") Integer limit){
        return "Hello "+sortBy+" "+limit;
    }
    */


    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) //ReqBody brings the full object
    {
        return  employeeService.createNewEmployee(employeeDTO);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteEmployeeById(@PathVariable Long id){
        return employeeService.deleteEmployeeById(id);
    }
}
