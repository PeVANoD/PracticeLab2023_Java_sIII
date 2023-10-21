package functions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ArrayTabulatedFunctionTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);
    public void testGetY() {
        assertEquals(3, arrayTabulatedFunction.getY((int) 1));
        assertEquals(4, arrayTabulatedFunction.getY((int) 2));
        assertEquals(5, arrayTabulatedFunction.getY(3));
        assertNotEquals(2, arrayTabulatedFunction.getY((int) 1));
        assertNotEquals(1, arrayTabulatedFunction.getY((int) 2));
        assertNotEquals(4, arrayTabulatedFunction.getY(3));
    }

    @Test
    public void testGetX() {
        assertEquals(1.0, arrayTabulatedFunction.getX(0));
        assertEquals(2.5, arrayTabulatedFunction.getX(3));
        assertEquals(3.0, arrayTabulatedFunction.getX(4));
        assertNotEquals(0.0, arrayTabulatedFunction.getX(0));
        assertNotEquals(3.5, arrayTabulatedFunction.getX(3));
        assertNotEquals(5.0, arrayTabulatedFunction.getX(4));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(2, arrayTabulatedFunction.indexOfX(2.0));
        assertEquals(4, arrayTabulatedFunction.indexOfX(3.0));
        assertEquals(-1, arrayTabulatedFunction.indexOfX(4.5));
        assertNotEquals(1, arrayTabulatedFunction.indexOfX(2.0));
        assertNotEquals(3, arrayTabulatedFunction.indexOfX(3.0));
        assertNotEquals(0, arrayTabulatedFunction.indexOfX(4.5));
    }

    @Test
    public void testLeftBound() {
        double expected = 1;
        double actual = arrayTabulatedFunction.leftBound();
        assertEquals(expected, actual, 0.0001);
        assertNotEquals(2, actual, 0.0001);
    }

    @Test
    public void testRightBound() {
        double expected = 3;
        double actual = arrayTabulatedFunction.rightBound();
        assertEquals(expected, actual, 0.0001);
        assertNotEquals(4, actual, 0.0001);
    }

    @Test
    public void testExtrapolateLeft() {
        double x = 0.5;
        double expected = 1;
        double result = arrayTabulatedFunction.extrapolateLeft(x);
        assertEquals(expected, result, 0.0001);
        assertNotEquals(2, result, 0.0001);
    }

    @Test
    public void testExtrapolateRight() {
        double x = 3.5;
        double expected = 7;
        double result = arrayTabulatedFunction.extrapolateRight(x);
        assertEquals(expected, result, 0.0001);
        assertNotEquals(6, result, 0.0001);
    }

    @Test
    public void testInterpolate() {
        double x = 1.75;
        double leftX = 1.5;
        double rightX = 2;
        double leftY = 3;
        double rightY = 4;
        double expected = 3.5;
        double result = arrayTabulatedFunction.interpolate(x, leftX, rightX, leftY, rightY);
        assertEquals(expected, result, 0.0001);
        assertNotEquals(3.0, result, 0.0001);
    }

    @Test
    public void testSetY() {
        arrayTabulatedFunction.setY(2, 10);
        assertEquals(10, arrayTabulatedFunction.getY(2), 0.0001);
        assertNotEquals(5, arrayTabulatedFunction.getY(2), 0.0001);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(2, arrayTabulatedFunction.indexOfY(4));
        assertNotEquals(1, arrayTabulatedFunction.indexOfY(4));
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(1, arrayTabulatedFunction.floorIndexOfX(1.7));
        assertNotEquals(2, arrayTabulatedFunction.floorIndexOfX(1.7));
    }
}