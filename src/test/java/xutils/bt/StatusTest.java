package xutils.bt;

import org.junit.Test;
import xutils.bt.status.StatusChecker;
import xutils.bt.status.impl.LoadStatusChecker;
import xutils.bt.status.impl.MemoryStatusChecker;


public class StatusTest {

    @Test
    public void testStatus() {
        StatusChecker m = new MemoryStatusChecker();
        System.out.println("++++++++++m:" + m.check());

        StatusChecker l = new LoadStatusChecker();
        System.out.println("++++++++++l:" + l.check());
    }

}
