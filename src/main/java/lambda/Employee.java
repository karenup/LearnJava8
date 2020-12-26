package lambda;

import com.sun.org.apache.xerces.internal.util.Status;

import java.util.Objects;

public class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee.Status getStatus() {
        return Status;
    }

    public void setStatus(Employee.Status status) {
        Status = status;
    }

    private Status Status;

    public Employee(){}
    public Employee(int age){
        this.age = age;
    }
    public Employee(int age,double salary){
        this.age = age;
        this.salary = salary;
    }
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, int age, double salary, Employee.Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        Status = status;
    }


    @Override
    public String toString() {
        return "Employee [name="+name+", age="+age+", salary="+salary+", Status="+Status+"]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION
    }
}
