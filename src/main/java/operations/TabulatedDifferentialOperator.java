package operations;

import functions.TabulatedFunction;
import functions.Point;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import concurrent.SynchronizedTabulatedFunction;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    protected TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {

        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {

        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return this.factory;
    }

    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(function);
        double[] xValue = new double[function.getCount()];
        double[] yValue = new double[function.getCount()];
        int j = 0;
        while (j < (xValue.length - 1)) {
            xValue[j] = arrayOfPoints[j].x;
            yValue[j] = (arrayOfPoints[j + 1].y - arrayOfPoints[j].y) / (arrayOfPoints[j + 1].x - arrayOfPoints[j].x);
            j++;
        }
        xValue[j] = arrayOfPoints[j].x;
        yValue[j] = yValue[j - 1];
        return factory.create(xValue, yValue);

    }


    public SynchronizedTabulatedFunction deriveSynchronously (TabulatedFunction function){
        SynchronizedTabulatedFunction synchronizedFunction = (function instanceof SynchronizedTabulatedFunction) ?
                (SynchronizedTabulatedFunction) function :
                new SynchronizedTabulatedFunction(function);

        return synchronizedFunction.doSynchronously(func -> new SynchronizedTabulatedFunction(derive(func)));
    }


}