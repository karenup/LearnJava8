package lambda;

/**
 * @author songkaiwen
 * @date 2020/12/8 3:17 下午
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的四大核心函数式接口
 * Consumer<T>：消费型接口
 *    void accept(T t);
 * Supplier<T>: 供给型接口
 *    T get();
 * Function<T,R>: 函数型接口
 *    R apply(T t);
 * Predicate<T>: 断言型接口
 *    boolean test(T t);
 *
 * 一些子接口
 * BiFunction<T,U,R>(Function子接口)
 *      R apply(T t, U u);
 * UnaryOperator<T>(Function子接口)
 *      static <T> UnaryOperator<T> identity() {
 *         return t -> t;
 *     }
 * 等。
 *
 */
public class TestLambda3 {
    //断言型接口

    @Test
    public void test4(){
        List<String> strList = Arrays.asList("hello","world","Lambda","OK");
        List<String> result = filterStr(strList,(s) -> s.length()>3);
        System.out.println(result);
    }
    //需求：将满足条件的字符串添加到集合中
    public List<String> filterStr(List<String> l, Predicate<String> pre){
        List<String> list = new ArrayList<>();
        for (String s:  l) {
            if(pre.test(s)){
                list.add(s);
            }
        }
        return list;
    }
    //函数型接口
    @Test
    public void test3(){
        String result1 = processStr("karen",(s) -> s.toUpperCase());
        System.out.println(result1);
    }
    //需求：用于处理字符串
    public String processStr(String str, Function<String,String> fun){
        return fun.apply(str);
    }
    //供给型接口
    @Test
    public void test2(){
        List<Integer> result = getNumList(10,() -> (int)(Math.random()*100));
        for(Integer i:result){
            System.out.println(i);
        }
    }
    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer>list = new ArrayList<>();
        for(int i=0;i<num;i++){
            Integer e = sup.get();
            list.add(e);
        }
        return list;
    }
    //消费型接口
    @Test
    public void test1(){
        happy(1000,(m) -> System.out.println("buying clothes consume "+m+" every time"));
    }
    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

}
