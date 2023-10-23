package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);

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

    protected int floorIndexOfX(double x) {
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
        if (count == 1) {
            return yValues[0];
        } else {
            double leftX = getX(floorIndex - 1);
            double rightX = getX(floorIndex);
            double leftY = getY(floorIndex - 1);
            double rightY = getY(floorIndex);
            return interpolate(x, leftX, rightX, leftY, rightY);
        }

    }

    protected double extrapolateLeft(double x) {
        if (count == 1) return yValues[0];
        else
            return (yValues[0] + (((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0])));
    }

    protected double extrapolateRight(double x) {
        if (count == 1) return yValues[0];
        else
            return (yValues[count - 2] + (((yValues[count - 1] - yValues[count - 2]) / (xValues[count - 1] - xValues[count - 2])) * (x - xValues[count - 2])));
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
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
        if (!(o instanceof ArrayTabulatedFunction)) return false;
        ArrayTabulatedFunction that = (ArrayTabulatedFunction) o;
        if (count != that.count) return false;
        for (int i = 0; i < count; i++) {
            if (this.xValues[i] != that.xValues[i] || this.yValues[i] != that.yValues[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(xValues) ^ Arrays.hashCode(yValues);
    }

    @Override
    public Object clone() {
        try {
            ArrayTabulatedFunction clone = (ArrayTabulatedFunction) super.clone();
            clone.xValues = Arrays.copyOf(this.xValues, this.count);
            clone.yValues = Arrays.copyOf(this.yValues, this.count);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}