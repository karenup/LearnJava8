package folkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author songkaiwen
 * @date 2020/12/27 1:52 下午
 */
public class TestFolkJoin {
    /**
     * Fork/join框架
     */

    @Test
    public void test1(){
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new FolkJoinCalculate(0,1000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费时间为： "+Duration.between(start,end).toMillis());//286
    }
    /**
     * 普通for循环
     */
    @Test
    public void test2(){
        long sum = 0;
        Instant start = Instant.now();
        for(long i = 0;i <= 1000000000L; i++){
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("耗费时间为"+Duration.between(start,end).toMillis());//439
    }

    /**
     * Java8并行流
     */
    @Test
    public void test3(){
        /**
         * 提供公共的fork/join池
         */
        Instant start = Instant.now();

        Long sum = LongStream.rangeClosed(0,1000000000L)
                .parallel()//sequential()，并行流底层就是Fork/Join
                .reduce(0,Long::sum);

        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("并行流耗费时间为"+Duration.between(start,end).toMillis());//202
        System.out.println("串行流耗费时间为"+Duration.between(start,end).toMillis());//462

    }

}
