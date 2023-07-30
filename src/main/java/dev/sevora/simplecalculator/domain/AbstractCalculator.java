package dev.sevora.simplecalculator.domain;

public abstract class AbstractCalculator {

    protected String left = "";
    protected String right = "";
    protected String operation = "";

    protected String previousRight = "";
    protected String previousOperation = "";

    protected boolean hasError = false;

    public static String NUMBERS = "0123456789";
    public static String OPERATIONS = "+-x÷";
    public static String SPECIAL = "AC C % +/- .";

    protected abstract void evaluate();
    protected abstract void evaluateOnce();
    protected abstract void evaluateSpecialOperation(String operation);

    protected boolean canEvaluateOnce(){
        return !this.checkE()
                && this.isLengthBiggerThan(this.left.length(), 0)
                && this.isLengthBiggerThan(this.right.length(), 0)
                && this.isLengthBiggerThan(this.operation.length(), 0);
    }

    protected boolean isLengthBiggerThan(int length, int size){
        return length > size;
    }

    private boolean checkE(){
        return (this.left.endsWith("E") || this.left.endsWith("E"));
    }

    protected String getTreatedNumberAtSide(){
        return getNumberAtSide().isEmpty() ? getNumberAtSide() : "0";
    }

    /**
     * Use to figure out which String number should be modified at this point
     * according to the current state.
     * @return String that says whether the left side or right side of the
     * expression to be evaluated should be modified.
     */
    private String getSide() {
        return operation.length() > 0 ? "right" : "left";
    }

    /**
     * This returns the value of the current String number that should be modified.
     * @return A String with just numbers in it.
     */
    protected String getNumberAtSide() {
        return getSide() == "left" ? left : right;
    }

    /**
     * Use to set the number that should be modified.
     * @param number String that consists of numerical values only.
     */
    protected void setNumberAtSide(String number) {
        if (getSide() == "left") {
            left = number;
        } else {
            right = number;
        }
    }

    /**
     * This just sets the current operation.
     * @param operation String that should match one of values from the
     * static operation variable.
     */
    protected void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Use to clear the previous operation state
     * to allow a different operation.
     */
    protected void clearHistory() {
        this.previousRight = "";
        this.previousOperation = "";
    }
}
