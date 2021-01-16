package com.example.android_acquaintance;

public class CalculatorScreen {
    private long calculatorScreenValue;
    private long firstValue;
    private long memoryValue;
    private String mathSymbol;
    private boolean isSecondValue;

    public CalculatorScreen() {
        calculatorScreenValue = 0;
        firstValue = 0;
        memoryValue = 0;
        mathSymbol = String.valueOf(R.string.empty_symbol);
        isSecondValue = false;
    }

    public long getCalculatorScreenValue() {
        return calculatorScreenValue;
    }

    public void setCalculatorScreenValue(long calculatorScreenValue) {
        this.calculatorScreenValue = calculatorScreenValue;
    }

    public long getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(long firstValue) {
        this.firstValue = firstValue;
    }

    public long getMemoryValue() {
        return memoryValue;
    }

    public void setMemoryValue(long memoryValue) {
        this.memoryValue = memoryValue;
    }

    public String getMathSymbol() {
        return mathSymbol;
    }

    public void setMathSymbol(String mathSymbol) {
        this.mathSymbol = mathSymbol;
    }

    public boolean isSecondValue() {
        return isSecondValue;
    }

    public void setIsSecondValue(boolean isSecondValue) {
        this.isSecondValue = isSecondValue;
    }
}
