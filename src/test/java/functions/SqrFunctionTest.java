package functions;

import org.junit.Test;
import junit.framework.Assert;

public class SqrFunctionTest {
    MathFunction test0 = new SqrFunction();

    @Test
    public void testEquals() {
        Assert.assertEquals(0.0, test0.apply(0));
    }
}