package functions;

import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;
import operations.TabulatedDifferentialOperator;
import static org.junit.jupiter.api.Assertions.*;


public class TabulatedDifferentialOperatorTest {
    double[] xValue = {1, 2, 5, 7};
    double[] yValue = {5, 6, 9, 15};

    @Test
    void setFactoryTest() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator();
        operation.setFactory(fact);

        assertTrue(operation.getFactory().getClass() == fact.getClass());

    }

    @Test
    void deriveTest() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValue, yValue);
        TabulatedFunction differential_func = operation.derive(func);
        assertEquals(1, differential_func.getY(0));
        assertEquals(1, differential_func.getY(1));
        assertEquals(3, differential_func.getY(2));
        assertEquals(3, differential_func.getY(3));
    }

}