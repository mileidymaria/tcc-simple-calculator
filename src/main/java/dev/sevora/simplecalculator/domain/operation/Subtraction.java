package dev.sevora.simplecalculator.domain.operation;

public class Subtraction implements Operation{
    @Override
    public double execute(double left, double right) {
        return left - right;
    }
}
