package dev.sevora.simplecalculator.domain;

public class NumberFormatter {
    private static NumberFormatter soleInstance;

    private NumberFormatter(){

    }

    public static NumberFormatter getSoleInstance(){
        if(soleInstance == null){
            soleInstance = new NumberFormatter();
        }
        return soleInstance;
    }
    /**
     * Use to format a String number and remove unnecessary digits, usually trailing zeroes.
     * @param number This is a string that only has numbers in it, can
     * have the decimal point or not.
     * @return String that is formatted as a number properly, removing unnecessary
     * trailing zeroes if there are any.
     */
    public String formatStringNumber(String number) {
        int decimalIndex = number.indexOf(".");
        if (decimalIndex == -1) {
            return number;
        }

        int safeIndex = number.length() - 1;
        while (safeIndex >= decimalIndex && number.charAt(safeIndex) == '0') {
            --safeIndex;
        }

        if (number.charAt(safeIndex) == '.') {
            --safeIndex;
        }

        return number.substring(0, safeIndex + 1);
    }
}
