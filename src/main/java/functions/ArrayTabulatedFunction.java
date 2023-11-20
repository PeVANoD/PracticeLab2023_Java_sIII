package functions;

import exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction, Serializable {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

    }

    public double apply(double x) {
        double result;
        if (x < xValues[0]) {
            result = extrapolateLeft(x);
        } else if (x > xValues[count - 1]) {
            result = extrapolateRight(x);
        } else {
            if (indexOfX(x) != -1) {
                result = getY(indexOfX(x));
            } else {
                result = interpolate(x, floorIndexOfX(x));
            }
        }
        return result;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        double[] myArrayX = new double[count];
        double[] myArrayY = new double[count];
        if (xFrom > xTo) {

            double step = (xFrom - xTo) / (count - 1);
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xTo + (i * step);
                myArrayY[i] = source.apply(xTo + (i * step));
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        } else if (xTo > xFrom) {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xFrom + (i * step);
                myArrayY[i] = source.apply(xFrom + (i * step));
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        } else {
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xFrom;
                myArrayY[i] = source.apply(xFrom);
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        }

    }

    public int getCount() {
        return count;
    }

    public double getX(int index) {
        return xValues[index];
    }

    public double getY(int index) {
        return yValues[index];
    }

    public void setY(int index, double value) {
        yValues[index] = value;
    }

    public double leftBound() {
        return xValues[0];
    }

    public double rightBound() {
        return xValues[count - 1];
    }

    public int indexOfX(double x) {
        int index = 0;
        while (index <= count - 1) {
            if (xValues[index] == x) return index;
            else index++;
        }
        return -1;
    }

    public int indexOfY(double y) {
        int index = 0;
        while (index <= count - 1) {
            if (yValues[index] == y) return index;
            else index++;
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        if (xValues[0] > x) return 0;
        else if (xValues[count - 1] < x) return count;
        else {
            for (int index = 0; ; index++) {
                if (xValues[index] == x) return index;
                else if (xValues[index] > x) return index - 1;
            }
        }
    }

    protected double interpolate(double x, int floorIndex) {
        if (getX(floorIndex) >= x)
            throw new InterpolationException("Range error for interpolation");

        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    public double extrapolateLeft(double x) {
        if (count == 1) return yValues[0];
        else
            return (yValues[0] + (((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0])));
    }

    public double extrapolateRight(double x) {
        if (count == 1) return yValues[0];
        else
            return (yValues[count - 2] + (((yValues[count - 1] - yValues[count - 2]) / (xValues[count - 1] - xValues[count - 2])) * (x - xValues[count - 2])));
    }

    public double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (count == 1) return yValues[0];
        else
            return (leftY + (((rightY - leftY) / (rightX - leftX)) * (x - leftX)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append("(").append(xValues[i]).append(", ").append(yValues[i]).append(")");
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTabulatedFunction that = (ArrayTabulatedFunction) o;
        return Arrays.equals(xValues, that.xValues) && Arrays.equals(yValues, that.yValues);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(xValues);
        result = 31 * result + Arrays.hashCode(yValues);
        return result;
    }

    @Override
    public ArrayTabulatedFunction clone() {
        double[] clonedXValues = Arrays.copyOf(xValues, xValues.length);
        double[] clonedYValues = Arrays.copyOf(yValues, yValues.length);

        return new ArrayTabulatedFunction(clonedXValues, clonedYValues);
    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Point point = new Point(xValues[i], yValues[i]);
                i++;

                return point;
            }
        };
    }

    @Override
    public Point[] asPoints() {
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = new Point(xValues[i], yValues[i]);
        }
        return points;
    }

    @Override
    public double[] getXValues() {
        return new double[0];
    }

    @Override
    public double[] getYValues() {
        return new double[0];
    }
    @Serial
    private static final long serialVersionUID = 1L;
}