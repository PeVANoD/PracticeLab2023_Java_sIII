package functions;

import org.junit.Test;
import junit.framework.Assert;

public class LnFunctionTest {
    MathFunction test2 = new LnFunction();

    @Test
    public void testEquals() {
        Assert.assertEquals(0.0, test2.apply(1));
    }
}
