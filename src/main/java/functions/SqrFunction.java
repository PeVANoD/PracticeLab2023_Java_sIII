package functions;

class SqrFunction implements MathFunction {
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}