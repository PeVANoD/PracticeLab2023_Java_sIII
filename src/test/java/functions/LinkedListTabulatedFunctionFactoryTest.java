package functions;

import functions.factory.ArrayTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListTabulatedFunctionFactoryTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction listTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);

    @Test
    void createTest() {
        ArrayTabulatedFunctionFactory listFactory = new ArrayTabulatedFunctionFactory();
        double[] xValueFactory = {1, 2, 3, 4};
        double[] yValueFactory = {5, 6, 7, 8};
        boolean flag = listTabulatedFunction.getClass() == (listFactory.create(xValueFactory, yValueFactory)).getClass();
        assertTrue(flag);
    }
}