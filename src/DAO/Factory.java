package DAO;

import DAO.impl.EmployeeDAOImpl;

public class Factory {
    private static EmployeeDAO employeDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public EmployeeDAO getEmployeeDAO() {
        if (employeDAO == null) {
            employeDAO = new EmployeeDAOImpl();
        }
        return employeDAO;
    }
}
