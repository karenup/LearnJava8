package lambda;

/**
 * @author songkaiwen
 * @date 2020/12/10 11:38 上午
 */

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一. 方法引用：在Lambda方法体中的内容有方法已经实现了，可以使用"方法饮用"
 *          （或者可以理解为Lambda的另外一种表现形式）
 * 主要有3种语法格式：
 *
 *      对象::实例方法名
 *
 *      类::静态方法名
 *
 *      类::实例方法名
 *
 * 注意：
 * 1. Lambda体中调用方法的参数列表和返回值，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 * 2. 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用类::实例方法。
 *
 * 二. 构造器引用
 *      ClassName::new；
 * 注意：Lambda体中调用的构造函数的参数列表，要与函数式接口中抽象方法的参数列表保持一致！
 *
 * 三. 数组引用
 * Type[]::new；
 */
public class TestMethodRef {
    //数组引用
    @Test
    public void test7(){
        Function<Integer,String[]> fun = (x) -> new String[x];

        Function<Integer,String[]> fun1 = String[]::new;

        System.out.println(fun.apply(10).length);
        System.out.println(fun1.apply(11).length);
    }
    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> su = () -> new Employee();

        Supplier<Employee> su1 = Employee::new;//自动匹配对应的无参数构造器

        System.out.println(su.get().getName());
        System.out.println(su.get().getAge());
    }
    @Test
    public void test6(){
        Function<Integer,Employee> fun = (x) -> new Employee(x);

        Function<Integer,Employee> fun1 = Employee::new;//自动匹配对应的有参构造器

        System.out.println(fun.apply(20).getAge());
        System.out.println(fun1.apply(30).getAge());

        BiFunction<Integer,Double,Employee> bf = Employee::new;
        System.out.println(bf.apply(23,22000.0).getSalary());

    }
    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String,String> bi = (x,y) -> x.equals(y);
        BiPredicate<String,String> bi1 = String::equals;

        System.out.println(bi.test("karen","karen"));
        System.out.println(bi1.test("karen","karen1"));
    }
    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> com1 = Integer::compare;

        System.out.println(com.compare(1,2));
        System.out.println(com1.compare(3,2));
    }
    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> con = (x) -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> coms = ps::println;
        Consumer<String> com1 = System.out::println;

        con.accept("karen");
        coms.accept("good");
        com1.accept("girl");
    }
    @Test
    public void test2(){
        Employee emp = new Employee();
        emp.setName("karen");
        Supplier<String> sup = () -> emp.getName();

        Supplier<String> sup1 = emp::getName;

        System.out.println(sup.get());

    }

}
