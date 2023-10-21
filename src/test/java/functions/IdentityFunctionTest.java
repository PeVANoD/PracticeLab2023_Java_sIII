package functions;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.Assert;


public class IdentityFunctionTest {
    MathFunction test1 = new IdentityFunction();
    @Test
    public void testEquals(){
        Assert.assertEquals(15.0, test1.apply(15));
    }

    @Test
    public void testToString() {
        IdentityFunction identityFunction = new IdentityFunction();
        assertEquals("IdentityFunction", identityFunction.toString());
    }

    @Test
    public void testHashCode() {
        IdentityFunction identityFunction1 = new IdentityFunction();
        IdentityFunction identityFunction2 = new IdentityFunction();
        assertEquals(identityFunction1.hashCode(), identityFunction2.hashCode());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
    	IdentityFunction identityFunction1 = new IdentityFunction();
    	IdentityFunction identityFunction2 = identityFunction1.clone();
    	assertEquals(identityFunction1, identityFunction2);
    }
}