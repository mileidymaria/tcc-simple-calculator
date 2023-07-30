package dev.sevora.simplecalculator.domain;

import dev.sevora.simplecalculator.domain.factory.OperationFactory;
import dev.sevora.simplecalculator.domain.specialOperation.SpecialOperation;

public class Calculator extends AbstractCalculator {

    public void punch(String entry) {

        if (hasError) {
            this.left = this.right = this.operation = "";
            hasError = false;
            return;
        }

        switch (entry) {
            case "=":
                evaluate();
                break;
            default:
                processEntry(entry);
                break;
        }
    }

    private void processEntry(String entry) {
        if (this.NUMBERS.indexOf(entry) != -1) {
            processNumberEntry(entry);
        } else if (this.OPERATIONS.indexOf(entry) != -1) {
            processOperationEntry(entry);
        } else if (this.SPECIAL.contains(entry)) {
            evaluateSpecialOperation(entry);
        }
    }

    private void processNumberEntry(String entry) {
        String number = this.getNumberAtSide();
        if (number.length() == 1 && number.equals("0")) {
            this.setNumberAtSide("");
        }
        number = getNumberAtSide() + entry;
        this.setNumberAtSide(number);
    }

    private void processOperationEntry(String entry) {
        this.setOperation(entry);
        this.clearHistory();
    }

    protected void evaluateOnce() {
        if (this.canEvaluateOnce()) {
            try {
                double leftValue = Double.parseDouble(this.left);
                double rightValue = Double.parseDouble(this.right);

                double result = OperationFactory.getSoleInstance()
                        .getOperation(this.operation)
                        .execute(leftValue, rightValue);

                this.left = NumberFormatter.getSoleInstance().formatStringNumber(String.valueOf(result));
            } catch (ArithmeticException arithmeticException) {
                this.hasError = true;
                this.left = "Error";
            }

            this.previousRight = this.right;
            this.previousOperation = this.operation;
            this.right = "";
            this.operation = "";
        }
    }

    /**
     * This just evaluates the current expression. This part mainly adds
     * storing and using the history to allow using the same operation
     * without repeating what was input.
     */
    protected void evaluate() {
        if (this.isLengthBiggerThan(this.previousRight.length(), 0)
                && this.isLengthBiggerThan(this.previousOperation.length(), 0)) {
            this.right = this.previousRight;
            this.operation = this.previousOperation;
        }
        this.evaluateOnce();
    }

    /**
     * Use to evaluate a special operation, to support extra features on
     * the calculator.
     * @param operation String of a single special
     * command check out the static definitions above.
     */
    protected void evaluateSpecialOperation(String operation) {
        String number;
        if ("AC".equals(operation)) {
            this.left = this.right = this.operation = "";
        } else if ("C".equals(operation)) {
            number = SpecialOperation.getSoleInstance().backspace(this.getTreatedNumberAtSide());
            this.setNumberAtSide(number);
        } else if ("+/-".equals(operation)) {
            number = SpecialOperation.getSoleInstance().toggleSign(this.getTreatedNumberAtSide());
            this.setNumberAtSide(number);
        } else if ("%".equals(operation)) {
            number = SpecialOperation.getSoleInstance().toggleRate(this.getTreatedNumberAtSide());
            this.setNumberAtSide(number);
        } else if (".".equals(operation)) {
            number = SpecialOperation.getSoleInstance().toggleDecimalPoint(this.getTreatedNumberAtSide());
            this.setNumberAtSide(number);
        }
    }

    public String display() {
        String result = "";
        if (left.isEmpty()) {
            result = "0";
        } else if (right.isEmpty()) {
            result = left;
        } else {
            result = right;
        }
        return result;
    }


}
