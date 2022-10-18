package com.industrial.junerestapiapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
public class EployeeController {

   @Autowired
   private EmployeeRepository employeeRepository;

    @GetMapping("/getGreetings/{username}")
    public static String getGreetings(@PathVariable String username) {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay < 12) {
            return "Hi, Good Morning "+username+" ...";
        } else if (timeOfDay < 16) {
            return "Hi, Good Afternoon "+username+" ...";
        } else if (timeOfDay < 21) {
            return "Hi, Good Evening "+username+" ...";
        } else {
            return "Hi, Good Night "+username+" ...";
        }
    }
    @PostMapping("/createEmp")
    public Employee createEmployees(@RequestBody  Employee employee){
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    @GetMapping("/getAllEmps")
    public String getAllEmployees(){

        //return employeeRepository.findAll();
        return  CacheManager.cache.values().toString();

    }

    @GetMapping("/getEmp/{eId}")
    public String getEmpById(@PathVariable int eId){
       // return employeeRepository.findById(eId).get();

        Employee employee=CacheManager.cache.get(eId);
        if (CacheManager.cache.keySet().contains(eId)) {//or employee !=null

            return CacheManager.cache.get(eId).toString();
        }else {
            return "Employee is not present";
        }
    }


    @PutMapping("/updateEmp/{eId}")
    public String updateEmpbyId(@RequestBody Employee employee, @PathVariable int eId){
       Optional<Employee> employee1= employeeRepository.findById(eId);
       if (employee1.isPresent()){
           Employee processEmployee=employee1.get();
           processEmployee.setEName(employee.getEName());
           processEmployee.setEAddress(employee.getEAddress());
           return employeeRepository.save(processEmployee).toString();
       }else {
           return "Employee is not present";
       }
    }
    @DeleteMapping("/deleteEmp/{eId}")
    public String deleteEmpById(@PathVariable int eId){
        Optional<Employee> employee1= employeeRepository.findById(eId);
        if (employee1.isPresent()){
            employeeRepository.deleteById(eId);
            return "Employee is deleted";
        }else {
            return "Employee is not present";
        }

    }
}
