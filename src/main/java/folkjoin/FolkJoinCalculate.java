package folkjoin;

import org.junit.Test;

import java.util.concurrent.RecursiveTask;

/**
 * @author songkaiwen
 * @date 2020/12/27 1:13 下午
 */
public class FolkJoinCalculate extends RecursiveTask<Long> {
    private long start;
    private long end;

    private static final long THRESHOLD= 10000;

    public FolkJoinCalculate(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THRESHOLD){
            long sum = 0;
            for(long i = start;i<=end;i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            FolkJoinCalculate left = new FolkJoinCalculate(start,middle);
            left.fork();
            FolkJoinCalculate right = new FolkJoinCalculate(middle + 1,end);
            right.fork();
            return left.join() + right.join();
        }
    }

}
