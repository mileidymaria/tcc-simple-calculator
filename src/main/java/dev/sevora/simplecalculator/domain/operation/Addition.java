package dev.sevora.simplecalculator.domain;

public class Addition implements Operation {
    @Override
    public double execute(double left, double right) {
        return left + right;
    }
}
