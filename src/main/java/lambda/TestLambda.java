package lambda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class TestLambda {
    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }
    //Lambda表达式
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,9999),
            new Employee("李四",50,8888),
            new Employee("王五",38,7777),
            new Employee("赵六",16,6666),
            new Employee("田七",8,5555)
    );
    //需求1：查出公司中员工年龄大于35的员工信息
    @Test
    public void test3(){
        List<Employee> result = filterEmployees(employees);
        for(Employee emp:result){
            System.out.println(emp.getName());
        }
    }
    public List<Employee> filterEmployees(List<Employee>list){
        List<Employee> emps = new ArrayList<>();
        for(Employee emp:list){
            if(emp.getAge()>=35){
                emps.add(emp);
            }
        }
        return emps;
    }
    //需求2：查处公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee>list){
        List<Employee> emps = new ArrayList<>();
        for(Employee emp:list){
            if(emp.getSalary()>=5000){
                emps.add(emp);
            }
        }
        return emps;
    }
    //优化方式1:策略设计模式
    @Test
    public void test4(){
        List<Employee> result = filterEmployee3(employees,new FilterEmployeeByAge());
        for(Employee emp:result){
            System.out.println(emp.getName());
        }
        System.out.println("-------------------------");
        List<Employee> result2 = filterEmployee3(employees,new FilterEmployeeBySalary());
        for(Employee emp:result2){
            System.out.println(emp.getName());
        }
    }
    public List<Employee> filterEmployee3(List<Employee>list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for(Employee emp:list){
            if(mp.test(emp)){
                emps.add(emp);
            }
        }
        return emps;
    }
    //优化方式2：匿名内部类
    @Test
    public void test5(){
       List<Employee> result = filterEmployee3(employees,new MyPredicate<Employee>(){
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 7000;
            }
        });
       for(Employee emp:result){
           System.out.println(emp.getName());
       }
    }
    //优化方式3：Lambda表达式
    @Test
    public void test6(){
        List<Employee> result = filterEmployee3(employees, (employee) -> employee.getAge()<=35);
        result.forEach(employee -> System.out.println(employee.getName()));
    }
    //优化方式4:Stream API
    @Test
    public void test7(){
        employees.stream()
                .filter((employee -> employee.getAge()<=35))
                .limit(2)
                .forEach(employee -> System.out.println(employee.getName()));
        System.out.println("-----------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
