package dev.sevora.simplecalculator.domain;

public class Division implements Operation{
    @Override
    public double execute(double left, double right) {
        if (right == 0) {
            throw new ArithmeticException("Divisão por zero");
        }
        return left / right;
    }
}
