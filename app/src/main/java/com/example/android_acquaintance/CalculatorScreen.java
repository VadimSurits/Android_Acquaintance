package com.example.android_acquaintance;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class CalculatorScreen implements Parcelable {
    private String currentScreenState;
    private long incomingValue;
    private long firstValue;
    private long secondValue;
    private long result;
    private long memoryValue;
    private String mathSymbol;

    public CalculatorScreen() {
        currentScreenState = String.valueOf(R.string.button_0);
        incomingValue = 0;
        firstValue = 0;
        secondValue = 0;
        result = 0;
        memoryValue = 0;
        mathSymbol = String.valueOf(R.string.empty_symbol);
    }

    //конструктор для передачи данных с помощью Parcelable
    protected CalculatorScreen(Parcel in) {
        currentScreenState = in.readString();
        incomingValue = in.readLong();
        firstValue = in.readLong();
        secondValue = in.readLong();
        result = in.readLong();
        memoryValue = in.readLong();
        mathSymbol = in.readString();
    }

    //реализация Parcelable
    public static final Creator<CalculatorScreen> CREATOR = new Creator<CalculatorScreen>() {
        @Override
        public CalculatorScreen createFromParcel(Parcel in) {
            return new CalculatorScreen(in);
        }

        @Override
        public CalculatorScreen[] newArray(int size) {
            return new CalculatorScreen[size];
        }
    };

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
            String TAG = "Android_Acquaintance - ";
            Log.e(TAG, "Division on 0");
            result = 0;
            return result;
        }
    }

    public String getCurrentScreenState() {
        return currentScreenState;
    }

    public void setCurrentScreenState(String currentScreenState) {
        this.currentScreenState = currentScreenState;
    }

    public long getIncomingValue() {
        return incomingValue;
    }

    public void setIncomingValue(long incomingValue) {
        this.incomingValue = incomingValue;
    }

    public long getFirstValue() {
        return firstValue;
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

    @Override//переопределение метода по умолчанию из Parcelable
    public int describeContents() {
        return 0;
    }

    @Override //переопределение метода из Parcelable с указанием моих переменных
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentScreenState);
        dest.writeLong(incomingValue);
        dest.writeLong(firstValue);
        dest.writeLong(secondValue);
        dest.writeLong(result);
        dest.writeLong(memoryValue);
        dest.writeString(mathSymbol);
    }
}
