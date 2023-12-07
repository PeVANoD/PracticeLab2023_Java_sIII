package operations;

import functions.MathFunction;

public abstract class SteppingDifferentialOperator<T extends MathFunction> implements DifferentialOperator<T> {
    protected double step;

    protected SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException("Invalid step value");
        }
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException("Invalid step value");
        }
        this.step = step;
    }

    public abstract T derive(T function);
}