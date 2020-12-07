package lambda;

/**
 * @author songkaiwen
 * @date 2020/11/25 12:09 下午
 */

@FunctionalInterface
public interface MyFun<T> {
    public Integer getValue(Integer num);
}
