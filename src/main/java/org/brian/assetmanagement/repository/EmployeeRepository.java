/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.repository;

import org.brian.assetmanagement.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Kavitha
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee getByEmail(String email);
}
