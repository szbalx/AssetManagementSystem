/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.service;

import java.util.List;
import org.brian.assetmanagement.bean.Employee;

/**
 *
 * @author Kavitha
 */
public interface EmployeeService {
    public void save(Employee employee);
    public Employee getEmployee(Long employeeId);
    public void delete(Long employeeId);
    public void delete(Employee employee);
    public List<Employee> getAll();
}
