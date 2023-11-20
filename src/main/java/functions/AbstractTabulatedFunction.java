package functions;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;

abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);
    protected abstract double extrapolateLeft(double x);
    protected abstract double extrapolateRight(double x);
    protected abstract double interpolate(double x, int floorIndex);
    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);
    public abstract double apply(double x);

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Length of arrays is different");
        }
    }
    public static void checkSorted(double[] xValues) {
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i-1]) {
                throw new ArrayIsNotSortedException("Array is not sorted");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append(" size = ");
        builder.append(this.getCount());
        builder.append("\n");
        for (Point point : this
        ) {
            builder.append("[");
            builder.append(point.x);
            builder.append("; ");
            builder.append(point.y);
            builder.append("]\n");
        }

        return builder.toString();
    }
}