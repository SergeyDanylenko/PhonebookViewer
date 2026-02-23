package logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Comparator;

/**
 * Created by ASUP-9 on 21.07.2017.
 */
@Entity
@Table(name = "employees")
//public class Employee implements Comparable<Employee> {
public class Employee {
    private Long id;
    private String short_name;
    private String header_name;
    private String last_name;
    private String first_name;
    private String middle_name;
    private String category_name;
    private String post_name;
    private String home_phone;
    private String work_phone;
    private String email_address;
    private String note_string;

    public static final Comparator<Employee> COMPARE_BY_SHORT_NAME = (a, b) -> a.getShort_name().compareTo(b.getShort_name());

    public Employee() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "short_name")
    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    @Column(name = "header_name")
    public String getHeader_name() {
        return header_name;
    }

    public void setHeader_name(String header_name) {
        this.header_name = header_name;
    }

    @Column(name = "last_name")
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "first_name")
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "middle_name")
    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    @Column(name = "category_name")
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Column(name = "post_name")
    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    @Column(name = "home_phone")
    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    @Column(name = "work_phone")
    public String getWork_phone() {
        return work_phone;
    }

    public void setWork_phone(String work_phone) {
        this.work_phone = work_phone;
    }

    @Column(name = "email_address")
    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    @Column(name = "note_string")
    public String getNote_string() {
        return note_string;
    }

    public void setNote_string(String note_string) {
        this.note_string = note_string;
    }

//    @Override
//    public int compareTo(Employee o) {
//        return short_name.compareTo(o.getShort_name());
//    }
}
