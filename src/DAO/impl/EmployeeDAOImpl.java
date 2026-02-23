package DAO.impl;

import DAO.EmployeeDAO;
import logic.Employee;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.persister.entity.AbstractEntityPersister;
import util.HibernateUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ASUP-9 on 21.07.2017.
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void addEmployee(Employee employee) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            Hibernate.initialize(employee);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            Hibernate.initialize(employee);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Employee getEmployee(Long id) {
        Session session = null;
        Employee employee = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employee = (Employee) session.load(Employee.class, id);
            Hibernate.initialize(employee);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employee;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        Session session = null;
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employees = (ArrayList<Employee>) session.createCriteria(Employee.class).list();
            Hibernate.initialize(employees);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
            Hibernate.initialize(employee);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<String> getHeaders(Class aClass) {
        Session session = null;
        ArrayList<String> headers = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            AbstractEntityPersister aep = (AbstractEntityPersister) session.getSessionFactory().getClassMetadata(aClass);

            String[] idNames = aep.getIdentifierColumnNames();
            Collections.addAll(headers, idNames);

            headers.add("Представление");
            headers.add("Буква");
            headers.add("Фамилия");
            headers.add("Имя");
            headers.add("Отчество");
            headers.add("Отдел/Цех");
            headers.add("Должность");
            headers.add("Дом.тел");
            headers.add("Раб.тел");
            headers.add("Почта");
            headers.add("Коммент");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return headers;
    }

    @Override
    public ArrayList<Employee> getAllEmployeesByABC(String firstChar) {
        Session session = null;
        ArrayList<Employee> employees;
        Employee employee;
        ArrayList<Employee> arrayListEmployees = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employees = (ArrayList<Employee>) session.createCriteria(Employee.class).list();
            for (Employee employee1 : employees) {
                if (employee1.getHeader_name().equals(firstChar)) {
                    employee = employee1;
                    arrayListEmployees.add(employee);
                }
            }
            Hibernate.initialize(employees);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        arrayListEmployees.sort(Employee.COMPARE_BY_SHORT_NAME);
        return arrayListEmployees;
    }

    @Override
    public ArrayList<Employee> getAllEmployeesByDepartment(String department) {
        Session session = null;
        ArrayList<Employee> employees;
        Employee employee;
        ArrayList<Employee> arrayListEmployees = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employees = (ArrayList<Employee>) session.createCriteria(Employee.class).list();
            for (Employee employee1 : employees) {
                if (employee1.getCategory_name().equals(department)) {
                    employee = employee1;
                    arrayListEmployees.add(employee);
                }
            }
            Hibernate.initialize(employees);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        arrayListEmployees.sort(Employee.COMPARE_BY_SHORT_NAME);
        return arrayListEmployees;
    }

    @Override
    public ArrayList<Employee> getEmployeesByQuery(String searchQuery) {
        Session session = null;
        ArrayList<Employee> employees = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String firstChar = searchQuery.substring(0, 1).toUpperCase();
            employees = (ArrayList<Employee>) session.createQuery("FROM Employee where last_name like '%" + searchQuery
                    + "%' OR last_name LIKE '%" + firstChar + searchQuery.substring(1)
                    + "%' OR work_phone LIKE '%" + searchQuery + "%' ORDER BY last_name").list();
            Hibernate.initialize(employees);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public ArrayList<String> getPosts() {
        Session session = null;
        ArrayList<String> posts = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            posts = (ArrayList<String>) session.createQuery("select distinct post_name FROM Employee ORDER BY post_name").list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return posts;
    }

    @Override
    public ArrayList<String> getCategories() {
        Session session = null;
        ArrayList<String> categories = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            categories = (ArrayList<String>) session.createQuery("select distinct category_name FROM Employee ORDER BY category_name").list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Телефонний довідник ПВРЗ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }
}
