package lambda;

/**
 * @author songkaiwen
 * @date 2020/12/8 3:04 下午
 */
@FunctionalInterface
public interface Practice4<T,R> {
    public R getValue(T t1,T t2);

}
