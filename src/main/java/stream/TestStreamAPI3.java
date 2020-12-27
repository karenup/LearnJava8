package stream;

/**
 * @author songkaiwen
 * @date 2020/12/26 2:16 下午
 */

import lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * StreamAPI的终止操作
 */
public class TestStreamAPI3 {
    /**
     * 查找与匹配：
     * alLMatch-检查是否匹配所有元素c
     * anyMatch-检查是否至少匹配一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意一个元素
     * count-返回元素的总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */
    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,9999, Employee.Status.FREE),
            new Employee("李四",50,8888, Employee.Status.BUSY),
            new Employee("田七",38,7777, Employee.Status.FREE),
            new Employee("田七",38,7777, Employee.Status.VOCATION),
            new Employee("田七",38,7777, Employee.Status.BUSY)
    );
    /**
     * 收集
     * collect-将流转为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test10(){
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","---","---"));
        System.out.println(str);
    }
    @Test
    public void test9(){
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss);
    }
    @Test
    public void test8(){
        //分区
        Map<Boolean,List<Employee>> par = employees.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getSalary()>8000));
        System.out.println(par);
    }
    @Test
    public void test7(){
        //多级分组
        Map<Employee.Status,Map<String,List<Employee>>>emp = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy((e) ->{
                    if(e.getAge() <= 35){
                        return "青年";
                    }else if(e.getAge()<=50){
                        return "中年";
                            }else {
                        return "老年";
                    }
                        }
                        )));
        System.out.println(emp);
    }
    @Test
    public void test6(){
        //分组
        Map<Employee.Status,List<Employee>> map = employees.stream()
            .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }
    @Test
    public void test5(){
        //计数
        Long sum = employees.stream()
                .collect(Collectors.counting());
        System.out.println(sum);
        System.out.println("-----------------");
        //平均值
        Double dou = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(dou);
        System.out.println("-----------------");
        //求和
        Double dou1 = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(dou1);
        //最大值
        Optional<Employee> emp = employees.stream()
                .collect(Collectors.maxBy((x,y) -> Double.compare(x.getSalary(),y.getSalary())));
        System.out.println(emp.get());
        System.out.println("------------------");
        //最小值
        Optional<Double> dou2 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(dou2);

    }
    @Test
    public void test4(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------------------");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("-------------------");
        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator)/reduce(BinaryOperator)-可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test3(){
        /**
         * map-reduce模式，因Google用它来进行网络搜索而出名
         */
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer op1 = list.stream()
                .reduce(0,(x,y) -> x+y);
        System.out.println(op1);
        System.out.println("---------------");
        Optional<Double> op2= employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);//没有指定起始值，所以有可能为空
        System.out.println(op2.get());
    }
    @Test
    public void test2(){
        Long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> op1 = employees.stream()
                .max((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()));
        System.out.println(op1.get());

        Optional<Double> dou= employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(dou.get());
    }
    @Test
    public void test1(){
        boolean b1 = employees.stream()
                              .allMatch((e -> e.getStatus().equals(Employee.Status.BUSY) ));
        System.out.println(b1);

        boolean b2 = employees.stream()
                              .anyMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = employees.stream()
                              .noneMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        Optional<Employee> emp = employees.stream()
                .sorted((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))
                .findFirst();
        System.out.println(emp.get());

        Optional<Employee> emp2 = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(emp2.get());
    }
}
