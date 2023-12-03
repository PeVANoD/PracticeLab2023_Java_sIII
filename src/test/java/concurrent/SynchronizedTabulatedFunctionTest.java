package concurrent;

import functions.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedTabulatedFunctionTest {
    final int count = 201;
    final int leftBound = -50;
    final int rightBound = 50;

    TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(new IdentityFunction(), leftBound, rightBound, count);
    SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(tabulatedFunction);

    @Test
    public void iterateThroughValues() {
        int index = 0;
        for (Point point : synchronizedTabulatedFunction) {
            assertEquals(point.x, tabulatedFunction.getX(index));
            assertEquals(point.y, tabulatedFunction.getY(index));
            index++;
        }
    }

    @Test
    public void iterateThroughListValues() {
        double[] xValues = {1, 2, 3, 4};
        double[] yValues = {5, 6, 7, 8};
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction synchronizedList = new SynchronizedTabulatedFunction(list);
        Iterator<Point> iterator = synchronizedList.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assertions.assertEquals(point.x, list.getX(index));
            Assertions.assertEquals(point.y, list.getY(index));
            index++;
        }
    }

    @Test
    public void getCountOfElements() {
        assertEquals(count, synchronizedTabulatedFunction.getCount());
    }

    @Test
    public void getXValueAtIndex() {
        for (int i = 0; i < count; i++)
            assertEquals(tabulatedFunction.getX(i), synchronizedTabulatedFunction.getX(i));
    }

    @Test
    public void getYValueAtIndex() {
        for (int i = 0; i < count; i++)
            assertEquals(tabulatedFunction.getY(i), synchronizedTabulatedFunction.getY(i));
    }


    @Test
    public void getLeftBound() {
        assertEquals(leftBound, synchronizedTabulatedFunction.leftBound());
    }

    @Test
    public void getRightBound() {
        assertEquals(rightBound, synchronizedTabulatedFunction.rightBound(), 0.000_001);
    }

    @Test
    public void getIndexForXValue() {
        for (int i = leftBound; i < rightBound; i++)
            assertEquals(tabulatedFunction.indexOfX(i), synchronizedTabulatedFunction.indexOfX(i));
    }

    @Test
    public void getIndexForYValue() {
        for (int i = leftBound; i < rightBound; i++)
            assertEquals(tabulatedFunction.indexOfY(i * i), synchronizedTabulatedFunction.indexOfY(i * i));
    }
}