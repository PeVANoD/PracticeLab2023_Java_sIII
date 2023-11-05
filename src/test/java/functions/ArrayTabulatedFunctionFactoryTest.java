package functions;

import exceptions.InterpolationException;
import functions.factory.ArrayTabulatedFunctionFactory;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayTabulatedFunctionFactoryTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);

    @Test
    void createTest() {
        ArrayTabulatedFunctionFactory arrFactory = new ArrayTabulatedFunctionFactory();
        double[] xValueFactory = {1, 2, 3, 4};
        double[] yValueFactory = {5, 6, 7, 8};
        boolean flag = arrayTabulatedFunction.getClass() == (arrFactory.create(xValueFactory, yValueFactory)).getClass();
        assertTrue(flag);

    }
}