package com.industrial.junerestapiapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class CacheManager {

    @Autowired
    EmployeeRepository employeeRepository;

    public static HashMap<Integer,Employee> cache=new HashMap<>();

    @Scheduled(cron = "* * * * * *")
    public void loadCache(){

        System.out.println("cache loading started");
        List<Employee> employeeList=employeeRepository.findAll();
        employeeList.forEach(employee -> cache.put(employee.getEId(),employee));
        System.out.println("cache loading ended");
    }
}
