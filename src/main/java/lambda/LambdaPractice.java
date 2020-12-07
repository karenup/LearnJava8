package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author songkaiwen
 * @date 2020/11/25 12:20 下午
 */
public class LambdaPractice {
    /**
     * 练习1：调用Collection.sort()方法，通过定制排序比较两个Employee(先按年龄比，年龄相同按姓名比)，使用Lambda作为参数传递
     */
    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,9999),
            new Employee("李四",50,8888),
            new Employee("王五",38,7777),
            new Employee("赵六",16,6666),
            new Employee("田七",8,5555)
    );
    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }
            return -Integer.compare(e1.getAge(),e2.getAge());
        });
        for(Employee emp : employees){
            System.out.println(emp.getName()+emp.getAge());
        }
    }

    /**
     * 练习2：
     * 声明函数式接口，接口中声明抽象方法，public String getValue(String str)；
     * 声明类TestLambda，类中编写方法使用接口作为参数，将一个字符串转换为大写，并作为方法的返回值
     * 再将一个字符串的第2个和第4个索引位置进行截取子串
     */
    public String process(String str,Practice3 p3){
        return p3.getValue1(str);
    }
    @Test
    public void test2(){
        String upper = process("karen",str -> str.toUpperCase());
        System.out.println(upper);
        String subString = process("songkaiwen",str -> str.substring(4,7));
        System.out.println(subString);
    }

    /**
     * 练习3：
     * 声明一个带两个泛型的函数式接口，泛型类型为<T,R>,T为参数，R为返回值
     * 在接口中声明对应抽象方法
     * 在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和
     * 再计算两个long型参数的乘积
     */
}
