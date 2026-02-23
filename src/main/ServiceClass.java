package main;

import logic.Employee;
import static DAO.Factory.getInstance;
/**
 * Created by ASUP-9 on 27.07.2017.
 */
public class ServiceClass {
    public static void updateStudent(long id, String last_name, String first_name, String middle_name, String category,
                              String post, String work_tel, String home_tel, String mail, String note) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLast_name(last_name);
        employee.setFirst_name(first_name);
        employee.setMiddle_name(middle_name);
        employee.setCategory_name(category);
        employee.setPost_name(post);
        employee.setWork_phone(work_tel);
        employee.setHome_phone(home_tel);
        employee.setEmail_address(mail);
        employee.setNote_string(note);
        employee.setHeader_name(last_name.substring(0, 1));
        employee.setShort_name(last_name + " " + first_name.substring(0, 1) + "." + middle_name.substring(0, 1) + ".");

        getInstance().getEmployeeDAO().updateEmployee(employee);
    }

    public static void addEmployee(String last_name, String first_name, String middle_name, String category,
                                   String post, String work_tel, String home_tel, String mail, String note) {
        Employee employee = new Employee();
        employee.setLast_name(last_name);
        employee.setFirst_name(first_name);
        employee.setMiddle_name(middle_name);
        employee.setCategory_name(category);
        employee.setPost_name(post);
        employee.setWork_phone(work_tel);
        employee.setHome_phone(home_tel);
        employee.setEmail_address(mail);
        employee.setNote_string(note);
        employee.setHeader_name(last_name.substring(0, 1));
        employee.setShort_name(last_name + " " + first_name.substring(0, 1) + "." + middle_name.substring(0, 1) + ".");
        getInstance().getEmployeeDAO().addEmployee(employee);
    }

    public static void main(String[] args) {
//        Employee employee = getInstance().getEmployeeDAO().getEmployee(Long.parseLong("2"));
//        updateStudent(employee.getId(), employee.getLast_name(), employee.getFirst_name(), employee.getMiddle_name(),
//                employee.getCategory_name(), employee.getPost_name(), "12345678910", employee.getHome_phone(),
//                employee.getEmail_address(), employee.getNote_string());
        addEmployee("Іваненко","Іван","Іванович","Відділ робітників","Робітник","123456789","987654321","ivanenko_ii@usv.com.ua","хз шо писать сюди");
    }
}
