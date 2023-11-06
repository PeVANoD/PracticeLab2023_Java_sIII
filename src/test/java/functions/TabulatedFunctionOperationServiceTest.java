package functions;

import functions.factory.ArrayTabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;
import org.junit.Test;
import functions.factory.TabulatedFunctionFactory;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPoints() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction function = factory.create(new double[]{0, 1, 2}, new double[]{0, 1, 2});
        Point[] points = function.asPoints();

        assertEquals(3, points.length);
        assertEquals(0, points[0].x, 0.0);
        assertEquals(0, points[0].y, 0.0);
        assertEquals(1, points[1].x, 0.0);
        assertEquals(1, points[1].y, 0.0);
        assertEquals(2, points[2].x, 0.0);
        assertEquals(2, points[2].y, 0.0);
    }

    TabulatedFunction a = new ArrayTabulatedFunction(new double[]{0, 1, 2}, new double[]{1, 2, 3});
    TabulatedFunction b = new ArrayTabulatedFunction(new double[]{0, 1, 2}, new double[]{4, 5, 6});

    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
    @Test
    public void testAdd() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        TabulatedFunction expected = new ArrayTabulatedFunction(new double[]{0, 1, 2}, new double[]{5, 7, 9});

        TabulatedFunction result = service.add(a, b);

        assertEquals(expected, result);
    }

    @Test
    public void testSubtraction() {

        TabulatedFunction expected = new ArrayTabulatedFunction(new double[]{0, 1, 2}, new double[]{-3, -3, -3});

        TabulatedFunction result = service.subtraction(a, b);
        assertEquals(expected, result);
    }
    @Test
    public void testMultiplication() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValuesA = {1.0, 2.0, 3.0};
        double[] yValuesB = {4.0, 5.0, 6.0};

        TabulatedFunction a = new ArrayTabulatedFunction(xValues, yValuesA);
        TabulatedFunction b = new ArrayTabulatedFunction(xValues, yValuesB);

        TabulatedFunction result = service.multiplication(a,b);

        double[] expectedXValues = {0.0, 1.0, 2.0};
        double[] expectedYValues = {0.0, 10.0, 18.0};
        TabulatedFunction expected = new ArrayTabulatedFunction(expectedXValues, expectedYValues);

        assertArrayEquals(expected.getXValues(), result.getXValues(), 0.0);
        assertArrayEquals(expected.getYValues(), result.getYValues(), 0.0);
    }

    @Test
    public void testDivision() {

        TabulatedFunction expected = new ArrayTabulatedFunction(new double[]{0, 1, 2}, new double[]{0.25, 0.4, 0.5});

        TabulatedFunction result = service.division(a, b);
        assertEquals(expected, result);
    }
}