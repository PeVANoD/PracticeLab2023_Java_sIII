package operations;

import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {


    protected TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return this.factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {

        Point[] asPointsArray = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            asPointsArray[i] = point;
            ++i;
        }
        return asPointsArray;
    }


    TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {

        if (a.getCount() != b.getCount()) throw new InconsistentFunctionsException();
        else {
            Point[] arraysA = asPoints(a);
            Point[] arraysB = asPoints(b);

            double[] xValue = new double[a.getCount()];
            double[] yValue = new double[a.getCount()];

            for (int i = 0; i < a.getCount(); i++) {
                if (arraysA[i].x == arraysB[i].x) xValue[i] = arraysA[i].x;
                else throw new InconsistentFunctionsException();
                yValue[i] = operation.apply(arraysA[i].y, arraysB[i].y);
            }
            return factory.create(xValue, yValue);
        }
    }

    public TabulatedFunction add(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u + v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction subtraction(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u - v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction multiplication(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u * v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction division(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u / v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    private interface BiOperation {
        double apply(double u, double v);
    }
}