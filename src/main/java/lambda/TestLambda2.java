package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda表达式的基础语法:Java8中引入了一个新的操作符"->",该操作符称为箭头操作符或Lambda操作符
 *                          箭头操作符将Lambda表达式分成两部分
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，也就是Lambda体
 *
 * 语法格式1：无参数，无返回值
 *      () -> System.out.println("Hello Lambda!");
 * 语法格式2：有一个参数，并且无返回值
 *      (x) -> System.out.println(x);
 * 语法格式3：若只有一个参数，参数的小括号可以省略不写
 *       x -> System.out.println(x);
 * 语法格式4：若有两个及以上参数，有返回值，并且Lambda体中有多条语句
 *      Comparator<Integer> com = (x,y) -> {
 *             System.out.println("函数式接口");
 *             return Integer.compare(x,y);
 *         };
 * 语法格式5：若Lambda体中只有一条语句，则return和{}都可省略不写
 *      Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
 * 语法格式6：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出数据类型，即"类型推断"
 *      Comparator<Integer> com = (Integer x,Integer y) -> Integer.compare(x,y);
 *
 * 左右遇一括号省
 * 左侧推断类型省
 *
 * 二. Lambda表达式需要"函数式接口"的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口
 *          可以用注解@FunctionalInterface注释，可以检查是否是函数式接口
 */
public class TestLambda2 {
    @Test
    public void test1(){
        /**
         * 在匿名局部类中使用同级别的变量，jdk 1.7之前，该变量必须用final修饰
         * 在1.7之后虽然不同手动+final，但是该变量本质上还是final不可变的
         */
        int num = 0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!"+num);
            }
        };
        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r.run();
        System.out.println("-------------------");
        r1.run();
    }

    @Test
    public void test2(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("Karen");
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        System.out.println(com.compare(1,2));
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
    }

    //需求：对一个数进行运算
    public Integer operation(Integer num,MyFun mf){
        return mf.getValue(num);
    }

    @Test
    public void test5(){
        Integer result = operation(100,x -> x * x);
        System.out.println(result);

        System.out.println(operation(200,x -> x+200));
    }

}

