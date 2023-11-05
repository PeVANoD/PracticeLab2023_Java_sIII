package operations;

import functions.MathFunction;
public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator<MathFunction> {

    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x + getStep()) - function.apply(x)) / getStep();
            }
        };
    }
}