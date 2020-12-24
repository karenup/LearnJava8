package stream;

/**
 * @author songkaiwen
 * @date 2020/12/22 3:09 下午
 */

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一. Stream的三个操作步骤：
 *      1. 创建stream
 *      2. 中间操作
 *      3. 终止操作（终端操作）
 *
 */
public class TestStreamAPI2 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,9999),
            new Employee("李四",50,8888),
            new Employee("田七",38,7777),
            new Employee("田七",38,7777),
            new Employee("田七",38,7777)
    );
    //中间操作
    /**
     * 排序
     * sorted()-自然排序(Comparable)
     * sorted(Comparator com)-定制排序
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("ccc","bbb","aaa","ddd","eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("----------------------");
        employees.stream()
                .sorted((emp1,emp2) -> {
                    if(emp1.getName().equals(emp2.getName())){
                        if(emp1.getAge()==emp2.getAge()){
                            if(emp1.getSalary() == emp2.getSalary()){
                                return 1;
                            }else{
                                return Double.compare(emp1.getSalary(),emp2.getSalary());
                            }
                        }else{
                            return Integer.compare(emp1.getAge(),emp2.getAge());
                        }
                    }else{
                        return emp1.getName().compareTo(emp2.getName());
                    }
                })
                .forEach(System.out::println);
    }
    /**
     * 映射
     * map,接收Lambda,将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用在每一个元素上，
     * 并将其映射成一个新的元素。
     * flatMap，接收一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有流都连成一个流
     */

    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-------------------");
        Stream<Stream<Character>> stream = list.stream()
                                               .map(TestStreamAPI2::filterCharacter);
        stream.forEach((sm) ->{
            sm.forEach(System.out::println);
        });
        System.out.println("-------------------");
        list.stream()
                .flatMap(TestStreamAPI2::filterCharacter)
                .forEach(System.out::println);
    }

    /**
     * 将一个字符串中的每一个字符转换为一个流输出
     */
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for(Character ch: str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }
    /**
     * 筛选与切片
     * filter，接收Lambda，从流中排除某些元素
     * limit，截断流，使其元素不超过给定数量
     * skip，跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit（n）互补
     * distinct，筛选，通过流所生成元素的hashCode和equals去除重复元素
     */
    @Test
    public void test5(){
        employees.stream()
                .filter(employee -> employee.getSalary()>5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        employees.stream()
                .filter((employee -> employee.getSalary()>5000))
                .skip(2)
                .forEach(System.out::println);
    }
    @Test
    public void test3(){
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路");//&& ||
                    return e.getSalary()>5000;
                })
                .limit(2)
                .forEach(System.out::println);

    }

    //内部迭代：迭代操作由StreamAPI完成
    @Test
    public void test1(){
        /**
         * 惰性求值：多个中间操作可以连接起来成为一个流水线，除非流水线上触发终止操作，否则中间操作不会进行
         * 任何处理，而是在终止操作时一次性全部处理，称为"惰性求值"。
         */
        //中间操作：不会执行任何操作
        Stream<Employee> stream = employees.stream()
                                            .filter((e) -> e.getAge()>35);
        //终止操作：一次性执行全部内容
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2(){
        Iterator<Employee> it = employees.iterator();
        while(it.hasNext()){
            System.out.println(it.next().getName());
        }
    }
}
