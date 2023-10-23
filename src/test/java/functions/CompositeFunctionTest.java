package functions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CompositeFunctionTest {

    MathFunction cos2xFunc = new Cos2xFunction();
    MathFunction sqrFunc = new SqrFunction();
    MathFunction unitFunc = new UnitFunction();
    MathFunction test3 = new CompositeFunction(sqrFunc, cos2xFunc);
    MathFunction test4 = new CompositeFunction(cos2xFunc, unitFunc);

    @Test
    public void TestEquals() {
        assertEquals(1.0, test3.apply(0));
        assertEquals(1.0, test4.apply(0));
    }

    @Test
    public void andThen() {
        assertEquals(1.0, cos2xFunc.andThen(sqrFunc).apply(0));
        assertNotEquals(0.0, cos2xFunc.andThen(sqrFunc).apply(1.0));

        assertEquals(1.0, unitFunc.andThen(sqrFunc).apply(0.0));
        assertNotEquals(0, unitFunc.andThen(sqrFunc).apply(0));
    }

    @Test
    public void andThanList() {
        double[] xValue = {0, 0.5, 1, 1.5, 2};
        double[] yValue = {2, 3, 1, 0, 2.5};
        MathFunction myTestFunc = new LinkedListTabulatedFunction(xValue, yValue);
        MathFunction sqrTestFunc = new SqrFunction();
        MathFunction cos2xTestFunc = new Cos2xFunction();
        MathFunction sqrMy = sqrTestFunc.andThen(myTestFunc);
        MathFunction cos2xMy = cos2xTestFunc.andThen(myTestFunc);

        assertEquals(4.0, sqrMy.apply(0));
        assertNotEquals(1, cos2xMy.apply(0));
        assertEquals(1, cos2xMy.apply(1.5));
        assertNotEquals(0, cos2xMy.apply(1.5));
    }


}