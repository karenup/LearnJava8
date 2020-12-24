package stream;

/**
 * @author songkaiwen
 * @date 2020/12/22 2:47 下午
 */

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一. Stream的三个操作步骤：
 *      1. 创建stream
 *      2. 中间操作
 *      3. 终止操作（终端操作）
 *
 */
public class TestStreamAPI1 {
    //创建stream
    @Test
    public void test1(){
        //通过Collection系列集合的stream()方法或者parallelStream()方法
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //通过Arrays中的静态方法stream获取数据流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> (x+2));
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(10)
                .forEach(System.out::println);
    }
}
