package DAO;

import logic.Employee;

import java.util.ArrayList;

/**
 * Created by ASUP-9 on 24.07.2017.
 */
public interface EmployeeDAO {
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public Employee getEmployee(Long id);
    public ArrayList<Employee> getAllEmployees();
    public void deleteEmployee(Employee employee);
    public ArrayList<String> getHeaders(Class aClass);
    public ArrayList<Employee> getAllEmployeesByABC(String firstChar);
    public ArrayList<Employee> getAllEmployeesByDepartment(String department);
    public ArrayList<Employee> getEmployeesByQuery(String searchQuery);
    public ArrayList<String> getPosts();
    public ArrayList<String> getCategories();
}
