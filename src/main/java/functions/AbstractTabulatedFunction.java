package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    public abstract double apply(double x);

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Массивы разной длины");
        }
    }

    public static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] > xValues[i + 1]) throw new ArrayIsNotSortedException("массив не отсортирован");

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