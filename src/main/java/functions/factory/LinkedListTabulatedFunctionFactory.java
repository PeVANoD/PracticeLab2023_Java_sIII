package functions.factory;

import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction createTabulatedFunction(MathFunction function, double xFrom, double xTo, int size) {

        return new LinkedListTabulatedFunction(function, xFrom, xTo, size);

    }
}