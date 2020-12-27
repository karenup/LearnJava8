package stream;

import lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author songkaiwen
 * @date 2020/12/27 12:24 下午
 */
public class TestStreamAPI {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,9999, Employee.Status.FREE),
            new Employee("李四",50,8888, Employee.Status.BUSY),
            new Employee("田七",38,7777, Employee.Status.FREE),
            new Employee("田七",38,7777, Employee.Status.VOCATION),
            new Employee("田七",38,7777, Employee.Status.BUSY)
    );
    /**
     * 1. 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     *  给定【1，2，3，4，5】应该返回【1，4，9，16，25】
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        List<Integer> op = list.stream()
                .map(i -> i*i)
                .collect(Collectors.toList());
        System.out.println(op);
    }

    /**
     * 2. 怎么用map和reduce方法数一数流中有多少Employee？
     */
    @Test
    public void test2(){
        Optional<Integer> i = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(i.get());
    }
}
