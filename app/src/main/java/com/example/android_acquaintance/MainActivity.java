package com.example.android_acquaintance;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "Android_Acquaintance - ";
    private TextView mainScreen;
    private TextView memoryScreen;
    private CalculatorScreen calculatorScreen;
    private boolean equalIsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorScreen = new CalculatorScreen();
        initView();
    }

    private void initView() {
        mainScreen = findViewById(R.id.main_screen);
        memoryScreen = findViewById(R.id.memory_screen);
        if (mainScreen.getText() == "") {
            setMainScreenText(getString(R.string.button_0));
        }
        initButtonsClickListener();
        equalIsDone = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (String.valueOf(getCalculatorScreenValue()).length() <= R.string.available_screen_space_for_digits) {
            switch (v.getId()) {
                case R.id.button_1:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_1));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_1));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_1));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_2:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_2));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_2));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_2));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_3:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_3));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_3));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_3));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_4:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_4));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_4));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_4));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_5:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_5));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_5));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_5));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_6:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_6));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_6));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_6));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_7:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_7));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_7));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_7));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_8:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_8));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_8));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_8));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_9:
                    if (getMathSymbol().equals(getString(R.string.button_equal))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_9));
                        setMathSymbol(getString(R.string.empty_symbol));
                    } else if (getCalculatorScreenValue() == Long.parseLong(getString(R.string.button_0))
                            || getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_9));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_9));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_0:
                    if (getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    } else if (getCalculatorScreenValue() != Long.parseLong(getString(R.string.button_0))
                            || calculatorScreen.isSecondValue()
                            && !getMainScreenText().equals(getString(R.string.button_0))) {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_0));
                    } else {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_00:
                    if (getMainScreenText().endsWith(getString(R.string.button_plus))
                            || getMainScreenText().endsWith(getString(R.string.button_minus))
                            || getMainScreenText().endsWith(getString(R.string.button_divide))
                            || getMainScreenText().endsWith(getString(R.string.button_multiply))) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    } else if (getCalculatorScreenValue() != Long.parseLong(getString(R.string.button_0))
                            || calculatorScreen.isSecondValue()
                            && !getMainScreenText().equals(getString(R.string.button_0))) {
                        setCalculatorScreenValueAndMainScreenText(getCalculatorScreenValue() +
                                getString(R.string.button_00));
                    }
                    if (equalIsDone) {
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_erase:
                    if (getCalculatorScreenValue() != Long.parseLong(getString(R.string.button_0))) {
                        char[] data = String.valueOf(getCalculatorScreenValue()).toCharArray();
                        String result = String.copyValueOf(data, 0, data.length - 1);
                        if (result.equals("") || result.equals(getString(R.string.button_minus))) {
                            setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                        } else {
                            setCalculatorScreenValueAndMainScreenText(result);
                        }
                    }
                    break;
                case R.id.button_c:
                    setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    resetTempValues();
                    equalIsDone = false;
                    break;
                case R.id.button_m:
                    if (calculatorScreen.getMemoryValue() == 0
                            && !getMainScreenText().equals(getString(R.string.button_0))) {
                        String result = getMainScreenText();
                        calculatorScreen.setMemoryValue(Long.parseLong(result));
                        memoryScreen.setText(getString(R.string.button_m));
                        resetTempValues();
                        equalIsDone = false;
                    } else if (calculatorScreen.getMemoryValue() != 0) {
                        setCalculatorScreenValueAndMainScreenText(String.valueOf
                                (calculatorScreen.getMemoryValue()));
                        memoryScreen.setText("");
                        calculatorScreen.setMemoryValue(0);
                        setCalculatorFirstValue(0);
                        calculatorScreen.setIsSecondValue(false);
                        equalIsDone = false;
                    }
                    break;
                case R.id.button_plus:
                    if (equalIsDone) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    } else if (getCalculatorFirstValue() == 0 && !calculatorScreen.isSecondValue()) {
                        setCalculatorFirstValue(getCalculatorScreenValue());
                        try {
                            setMainScreenText(getString(R.string.button_plus));
                        } catch (NumberFormatException e) {
                            Log.e(TAG, "button_plus");
                        }
                        setMathSymbol(getString(R.string.button_plus));
                        calculatorScreen.setIsSecondValue(true);
                    }
                    break;
                case R.id.button_minus:
                    if (equalIsDone) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    } else if (getCalculatorFirstValue() == 0 && !calculatorScreen.isSecondValue()) {
                        setCalculatorFirstValue(getCalculatorScreenValue());
                        try {
                            setMainScreenText(getString(R.string.button_minus));
                        } catch (NumberFormatException e) {
                            Log.e(TAG, "button_minus");
                        }
                        setMathSymbol(getString(R.string.button_minus));
                        calculatorScreen.setIsSecondValue(true);
                    }
                    break;
                case R.id.button_multiply:
                    if (equalIsDone) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    } else if (getCalculatorFirstValue() == 0 && !calculatorScreen.isSecondValue()) {
                        setCalculatorFirstValue(getCalculatorScreenValue());
                        try {
                            setMainScreenText(getString(R.string.button_multiply));
                        } catch (NumberFormatException e) {
                            Log.e(TAG, "button_multiply");
                        }
                        setMathSymbol(getString(R.string.button_multiply));
                        calculatorScreen.setIsSecondValue(true);
                    }
                    break;
                case R.id.button_divide:
                    if (equalIsDone) {
                        setCalculatorScreenValueAndMainScreenText(getString(R.string.button_0));
                    } else if (getCalculatorFirstValue() == 0 && !calculatorScreen.isSecondValue()) {
                        setCalculatorFirstValue(getCalculatorScreenValue());
                        try {
                            setMainScreenText(getString(R.string.button_divide));
                        } catch (NumberFormatException e) {
                            Log.e(TAG, "button_divide");
                        }
                        setMathSymbol(getString(R.string.button_divide));
                        calculatorScreen.setIsSecondValue(true);
                    }
                    break;
                case R.id.button_equal:
                    if (getMathSymbol().equals(getString(R.string.button_plus))
                            && calculatorScreen.isSecondValue()) {
                        Long result = Long.sum(getCalculatorFirstValue(), getCalculatorScreenValue());
                        setCalculatorScreenValueAndMainScreenText(String.valueOf(result));
                        resetTempValues();
                    } else if (getMathSymbol().equals(getString(R.string.button_minus))
                            && calculatorScreen.isSecondValue()) {
                        Long result = getCalculatorFirstValue() - getCalculatorScreenValue();
                        setCalculatorScreenValueAndMainScreenText(String.valueOf(result));
                        resetTempValues();
                    } else if (getMathSymbol().equals(getString(R.string.button_multiply))
                            && calculatorScreen.isSecondValue()) {
                        Long result = getCalculatorFirstValue() * getCalculatorScreenValue();
                        setCalculatorScreenValueAndMainScreenText(String.valueOf(result));
                        resetTempValues();
                    } else if (getMathSymbol().equals(getString(R.string.button_divide))
                            && calculatorScreen.isSecondValue()) {
                        if (getCalculatorScreenValue() != 0) {
                            Long result = getCalculatorFirstValue() / getCalculatorScreenValue();
                            if (getCalculatorFirstValue() % getCalculatorScreenValue() != 0) {
                                try {
                                    setCalculatorScreenValueAndMainScreenText(
                                            String.format(Locale.getDefault(), "%d%s",
                                                    result, ",.."));
                                } catch (NumberFormatException e) {
                                    Log.e(TAG, "button_equal - double_result");
                                }
                            } else {
                                setCalculatorScreenValueAndMainScreenText(String.valueOf(result));
                            }
                        }
                        resetTempValues();
                    }
                    equalIsDone = true;
                    setMathSymbol(getString(R.string.button_equal));
                    break;
            }
        }
    }

    public void initButtonsClickListener() {
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button button0 = findViewById(R.id.button_0);
        Button button00 = findViewById(R.id.button_00);
        Button buttonM = findViewById(R.id.button_m);
        Button buttonC = findViewById(R.id.button_c);
        Button multiply = findViewById(R.id.button_multiply);
        Button divide = findViewById(R.id.button_divide);
        Button plus = findViewById(R.id.button_plus);
        Button minus = findViewById(R.id.button_minus);
        Button erase = findViewById(R.id.button_erase);
        Button equal = findViewById(R.id.button_equal);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        button00.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        erase.setOnClickListener(this);
        equal.setOnClickListener(this);
    }

    public void resetTempValues() {
        setCalculatorFirstValue(0);
        setMathSymbol("");
        calculatorScreen.setIsSecondValue(false);
    }

    public void setCalculatorScreenValueAndMainScreenText(String s) {
        setMainScreenText(s);
        setCalculatorScreenValue(Long.parseLong(s));
    }

    public void setMainScreenText(String s) {
        mainScreen.setText(s);
    }

    public void setCalculatorScreenValue(long i) {
        calculatorScreen.setCalculatorScreenValue(i);
    }

    public void setCalculatorFirstValue(long i) {
        calculatorScreen.setFirstValue(i);
    }

    public void setMathSymbol(String s) {
        calculatorScreen.setMathSymbol(s);
    }

    public String getMainScreenText() {
        return String.valueOf(mainScreen.getText());
    }

    public long getCalculatorScreenValue() {
        return calculatorScreen.getCalculatorScreenValue();
    }

    public long getCalculatorFirstValue() {
        return calculatorScreen.getFirstValue();
    }

    public String getMathSymbol() {
        return calculatorScreen.getMathSymbol();
    }
}