package dev.sevora.simplecalculator.domain.specialOperation;

import dev.sevora.simplecalculator.domain.NumberFormatter;

public class SpecialOperationImpl {

    private static SpecialOperationImpl soleInstance;

    private SpecialOperationImpl(){
    }

    public static SpecialOperationImpl getSoleInstance(){
        if(soleInstance == null){
            soleInstance = new SpecialOperationImpl();
        }
        return soleInstance;
    }


    /**
     * Use to clear the whole state
     * of the calculator.
     */
    public void clear(String left, String right, String operation) {
        left = "";
        right = "";
        operation = "";
    }

    /**
     * Use to remove a single numerical value from the calculator,
     * this is literally backspace but it has some extra instruction
     * to remove the decimal point if a number next to it is being removed.
     */
    public String backspace(String number) {
        if (!number.isEmpty()) {
            number = number.substring(0, number.length() - 1);
            if (number.endsWith(".")) {
                number = number.substring(0, number.length() - 1);
            }
        }
        return number;
    }

    /**
     * Use to convert the number to a decimal, just literally adds the decimal point.
     */
    public String toggleDecimalPoint(String number) {
        if (number.indexOf('.') == -1) {
            return NumberFormatter.getSoleInstance().formatStringNumber(number) + '.';
        }
        return null;
    }

    /**
     * Use to change the sign of the number, just multiplies -1 on the current value.
     */
    public String toggleSign(String number) {
        if (number != "0") {
            double newValue = Double.parseDouble(number) * -1.0;
            String newValueString = String.valueOf(newValue);
            return NumberFormatter.getSoleInstance().formatStringNumber(newValueString);
        }
        return null;
    }

    /**
     * Use to convert the number to a rate, it literally just divides the current number by 100.
     */
    public String toggleRate(String number) {
        if (number != "0") {
            double newValue = Double.parseDouble(number) / 100;
            String newValueString = String.valueOf(newValue);
            return NumberFormatter.getSoleInstance().formatStringNumber(newValueString);
        }
        return null;
    }
}
