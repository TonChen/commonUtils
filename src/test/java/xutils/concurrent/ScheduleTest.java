package xutils.concurrent;

import org.junit.Test;
import xutils.concurrent.schedule.AbstractCallableTask;
import xutils.concurrent.schedule.ScheduledTaskExecutor;

import java.util.concurrent.Future;

/**
 * 任务执行测试
 *
 * Created by xuan on 17/8/14.
 */
public class ScheduleTest {

    @Test
    public void testSubmit(){
        ScheduledTaskExecutor scheduledTaskExecutor = new ScheduledTaskExecutor(50);
        Future<String> future = scheduledTaskExecutor.submit(new AbstractCallableTask<String>("taskName") {
            @Override
            public String processTask() throws Exception {
                System.out.println("++++++++++processTask");
                return "xuan";
            }
        });
        try {
            future.get();
        }catch (Exception e){
            //Ignore
        }

        System.out.println("++++++++++finish");
    }
}
