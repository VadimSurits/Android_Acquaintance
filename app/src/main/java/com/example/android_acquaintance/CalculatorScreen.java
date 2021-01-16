package com.example.android_acquaintance;

import android.util.Log;

public class CalculatorScreen {
    private final String TAG = "Android_Acquaintance - ";
    private long incomingValue;
    private long firstValue;
    private long secondValue;
    private long result;
    private long memoryValue;
    private String mathSymbol;

    public CalculatorScreen() {
        incomingValue = 0;
        firstValue = 0;
        secondValue = 0;
        result = 0;
        memoryValue = 0;
        mathSymbol = String.valueOf(R.string.empty_symbol);
    }

    public Long getPlusActionResult() {
        result = firstValue + secondValue;
        return result;
    }

    public long getMinusActionResult() {
        result = firstValue - secondValue;
        return result;
    }

    public long getMultiplyActionResult() {
        result = firstValue * secondValue;
        return result;
    }

    public long getDivideActionResult() {
        try {
            return firstValue / secondValue;
        } catch (ArithmeticException e) {
            Log.e(TAG, "Division on 0");
            result = 0;
            return result;
        }
    }

    public long getIncomingValue() {
        return incomingValue;
    }

    public void setIncomingValue(long incomingValue) {
        this.incomingValue = incomingValue;
    }

    public void setFirstValue(long firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(long secondValue) {
        this.secondValue = secondValue;
    }

    public void setResult(long result) {
        this.result = result;
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
}
