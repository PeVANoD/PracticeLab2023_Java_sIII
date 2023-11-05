package functions;

import operations.TabulatedFunctionOperationService;
import exceptions.InconsistentFunctionsException;
import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    @Test
    void testAsPoints() throws InconsistentFunctionsException {
        TabulatedFunction function = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{2, 4, 6});

        Point[] expectedPoints = new Point[]{
                new Point(1, 2),
                new Point(2, 4),
                new Point(3, 6)
        };

        Point[] actualPoints = TabulatedFunctionOperationService.asPoints(function);

        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    void testAsPointsWithEmptyFunction() {
        TabulatedFunction function = new ArrayTabulatedFunction();

        assertThrows(IllegalArgumentException.class, () -> TabulatedFunctionOperationService.asPoints(function));
    }
}