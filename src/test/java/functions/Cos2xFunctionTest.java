package functions;

import org.junit.Test;
import junit.framework.Assert;

public class Cos2xFunctionTest {
    MathFunction test5 = new Cos2xFunction();

    @Test
    public void testEquals() {
        Assert.assertEquals(1.0, test5.apply(0));
    }
}