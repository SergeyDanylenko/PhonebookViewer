package logic;

import java.util.Comparator;

/**
 * Created by ASUP-9 on 18.08.2017.
 */
public class EmployeeComparator implements Comparator<Employee> {
    public int compare(Employee a, Employee b){
        return a.getShort_name().compareTo(b.getShort_name());
    }
}
