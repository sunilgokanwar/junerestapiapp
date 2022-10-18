package com.industrial.junerestapiapp;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Employee {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
private int eId;
private String eName;
private String eAddress;
}
