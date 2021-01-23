package com.example.android_acquaintance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Constants {
    private TextView mainScreen;
    private TextView memoryScreen;
    private boolean isPlusSymbolPressed;
    private boolean isMinusSymbolPressed;
    private boolean isMultiplySymbolPressed;
    private boolean isDivideSymbolPressed;
    private boolean isEqualSymbolPressed;
    private CalculatorScreen calculatorScreen;
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 777;
    private int codeStyle = 0;
    private ConstraintLayout mainActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme());
        setContentView(R.layout.activity_main);

        calculatorScreen = new CalculatorScreen();
        initView();
        if (codeStyle == My_CALCULATOR_STYLE_DARK) {
            mainActivityLayout.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.dark_background));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_CALCULATOR_SCREEN, calculatorScreen);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorScreen = savedInstanceState.getParcelable(KEY_CALCULATOR_SCREEN);
        restoreScreen();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            codeStyle = data.getExtras().getInt(APP_THEME);
            setAppTheme();
            recreate();
        }
    }

    private void initView() {
        mainScreen = findViewById(R.id.main_screen);
        memoryScreen = findViewById(R.id.memory_screen);
        if (mainScreen.getText() == "") {
            setMainScreenText(getString(R.string.button_0));
        }
        initButtonsClickListener();
        isPlusSymbolPressed = false;
        isMinusSymbolPressed = false;
        isMultiplySymbolPressed = false;
        isDivideSymbolPressed = false;
        isEqualSymbolPressed = false;
    }

    //получение настроек (темы в данном случае)
    private int getAppTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        codeStyle = sharedPreferences.getInt(APP_THEME, codeStyle);
        if (codeStyle == My_CALCULATOR_STYLE_DARK) {
            return R.style.MyCalculatorStyle_Dark;
        } else {
            return R.style.MyCalculatorStyle;
        }
    }

    //сохранение настроек (темы в данном случае)
    private void setAppTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"NonConstantResourceId", "DefaultLocale"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_1)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_1))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_2:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_2)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_2))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_3:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_3)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_3))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_4:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_4)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_4))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_5:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_5)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_5))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_6:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_6)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_6))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_7:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_7)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_7))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_8:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_8)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_8))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_9:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_9)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_9))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_0:
                if (String.valueOf(getIncomingValue()).length() <= Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits))) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_0)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_0))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_00:
                if (String.valueOf(getIncomingValue()).length() <= (Integer.parseInt(getString
                        (R.string.available_screen_space_for_digits)) - 1)) {
                    if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_0)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(String.format
                                ("%d%s", getIncomingValue(), getString(R.string.button_00))));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_erase:
                if (!getMainScreenText().equals(getString(R.string.button_0))) {
                    char[] data = String.valueOf(getMainScreenText()).toCharArray();
                    String result = String.copyValueOf(data, 0, data.length - 1);
                    if (result.equals("") || result.equals(getString(R.string.button_minus))) {
                        setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_0)));
                    } else {
                        setIncomingValueAndMainScreenValue(Long.parseLong(result));
                    }
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_c:
                setIncomingValueAndMainScreenValue(Long.parseLong(getString(R.string.button_0)));
                setFirstValue(Long.parseLong(getString(R.string.button_0)));
                setSecondValue(Long.parseLong(getString(R.string.button_0)));
                setResult(Long.parseLong(getString(R.string.button_0)));
                setMathSymbol("");
                isPlusSymbolPressed = false;
                isMinusSymbolPressed = false;
                isMultiplySymbolPressed = false;
                isDivideSymbolPressed = false;
                isEqualSymbolPressed = false;
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_m:
                if (getMemoryValue() == Long.parseLong(getString(R.string.button_0))
                        && !getMainScreenText().equals(getString(R.string.button_0))
                        && !getMainScreenText().equals(getString(R.string.button_minus))
                        && !getMainScreenText().equals(getString(R.string.button_plus))
                        && !getMainScreenText().equals(getString(R.string.button_divide))
                        && !getMainScreenText().equals(getString(R.string.button_multiply))) {
                    setMemoryValue(Long.parseLong(getMainScreenText()));
                    memoryScreen.setText(getString(R.string.button_m));
                    setIncomingValue(Long.parseLong(getString(R.string.button_0)));
                } else if (getMemoryValue() != Long.parseLong(getString(R.string.button_0))) {
                    setIncomingValueAndMainScreenValue(getMemoryValue());
                    memoryScreen.setText("");
                    setMemoryValue(0);
                    setIsEqualSymbolPressed();
                }
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_plus:
                if (isEqualSymbolPressed) {
                    setFirstValue(Long.parseLong(getMainScreenText()));
                    setMainScreenText(getString(R.string.button_plus));
                    setIsPlusSymbolPressed();
                } else if (getFirstValue() != Long.parseLong(getString(R.string.button_0)) &&
                        isMinusSymbolPressed || isMultiplySymbolPressed || isDivideSymbolPressed) {
                    setMainScreenText(String.format("%d%s", getFirstValue(), getString(R.string.button_plus)));
                    setIsPlusSymbolPressed();
                } else if (!isPlusSymbolPressed) {
                    setFirstValue(getIncomingValue());
                    setMainScreenText(getString(R.string.button_plus));
                    setIsPlusSymbolPressed();
                } else {
                    setSecondValue(getIncomingValue());
                    setIncomingValueAndMainScreenValue(getPlusActionResult());
                }
                setMathSymbol(getString(R.string.button_plus));
                setIncomingValue(Long.parseLong(getString(R.string.button_0)));
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_minus:
                if (getIncomingValue() == Long.parseLong(getString(R.string.button_0))) {
                    setMainScreenText(getString(R.string.button_minus));
                } else if (isEqualSymbolPressed) {
                    setFirstValue(Long.parseLong(getMainScreenText()));
                    setMainScreenText(getString(R.string.button_minus));
                    setIsMinusSymbolPressed();
                } else if (getFirstValue() != Long.parseLong(getString(R.string.button_0)) &&
                        isPlusSymbolPressed || isMultiplySymbolPressed || isDivideSymbolPressed) {
                    setMainScreenText(String.format("%d%s", getFirstValue(), getString(R.string.button_minus)));
                    setIsMinusSymbolPressed();
                } else if (!isMinusSymbolPressed) {
                    setFirstValue(getIncomingValue());
                    setMainScreenText(getString(R.string.button_minus));
                    setIsMinusSymbolPressed();
                } else {
                    setSecondValue(getIncomingValue());
                    setIncomingValueAndMainScreenValue(getMinusActionResult());
                }
                setMathSymbol(getString(R.string.button_minus));
                setIncomingValue(Long.parseLong(getString(R.string.button_0)));
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_multiply:
                if (getMainScreenText().equals(getString(R.string.button_multiply))) {
                    setMainScreenText("");
                }
                if (isEqualSymbolPressed) {
                    setFirstValue(Long.parseLong(getMainScreenText()));
                    setMainScreenText(getString(R.string.button_multiply));
                    setIsMultiplySymbolPressed();
                } else if (getFirstValue() != Long.parseLong(getString(R.string.button_0)) &&
                        isPlusSymbolPressed || isMinusSymbolPressed || isDivideSymbolPressed) {
                    setMainScreenText(String.format("%d%s", getFirstValue(), getString(R.string.button_multiply)));
                    setIsMultiplySymbolPressed();
                } else if (!isMultiplySymbolPressed) {
                    setFirstValue(getIncomingValue());
                    setMainScreenText(getString(R.string.button_multiply));
                    setIsMultiplySymbolPressed();
                } else {
                    setSecondValue(getIncomingValue());
                    setIncomingValueAndMainScreenValue(getMultiplyActionResult());
                }
                setMathSymbol(getString(R.string.button_multiply));
                setIncomingValue(Long.parseLong(getString(R.string.button_0)));
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_divide:
                if (isEqualSymbolPressed) {
                    setFirstValue(Long.parseLong(getMainScreenText()));
                    setMainScreenText(getString(R.string.button_divide));
                    setIsDivideSymbolPressed();
                } else if (getFirstValue() != Long.parseLong(getString(R.string.button_0)) &&
                        isPlusSymbolPressed || isMinusSymbolPressed || isMultiplySymbolPressed) {
                    setMainScreenText(String.format("%d%s", getFirstValue(), getString(R.string.button_divide)));
                    setIsDivideSymbolPressed();
                } else if (!isDivideSymbolPressed) {
                    setFirstValue(getIncomingValue());
                    setMainScreenText(getString(R.string.button_divide));
                    setIsDivideSymbolPressed();
                } else {
                    setSecondValue(getIncomingValue());
                    setIncomingValueAndMainScreenValue(getDivideActionResult());
                }
                setMathSymbol(getString(R.string.button_divide));
                setIncomingValue(Long.parseLong(getString(R.string.button_0)));
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_equal:
                setSecondValue(getIncomingValue());
                if (getMathSymbol().equals(getString(R.string.button_plus))) {
                    setIncomingValueAndMainScreenValue(getPlusActionResult());
                    setIsPlusSymbolPressed();
                } else if (getMathSymbol().equals(getString(R.string.button_minus))) {
                    setIncomingValueAndMainScreenValue(getMinusActionResult());
                    setIsMinusSymbolPressed();
                } else if (getMathSymbol().equals(getString(R.string.button_multiply))) {
                    setIncomingValueAndMainScreenValue(getMultiplyActionResult());
                    setIsMultiplySymbolPressed();
                } else if (getMathSymbol().equals(getString(R.string.button_divide))) {
                    setIncomingValueAndMainScreenValue(getDivideActionResult());
                    setIsDivideSymbolPressed();
                }
                setIncomingValue(Long.parseLong(getString(R.string.button_0)));
                setIsEqualSymbolPressed();
                setCurrentScreenState(getMainScreenText());
                break;
            case R.id.button_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTING_ACTIVITY);
                break;
        }
    }

    public void initButtonsClickListener() {
        mainActivityLayout = findViewById(R.id.main_activity_layout);
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
        Button button_settings = findViewById(R.id.button_settings);
        if (codeStyle == My_CALCULATOR_STYLE_DARK) {
            button_settings.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.forest_green));
            button_settings.setTextColor(ContextCompat.getColor(this, R.color.black));
        }

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
        button_settings.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long getPlusActionResult() {
        long l = calculatorScreen.getPlusActionResult();
        setFirstValue(l);
        return l;
    }

    public long getMinusActionResult() {
        long l = calculatorScreen.getMinusActionResult();
        setFirstValue(l);
        return l;
    }

    public long getMultiplyActionResult() {
        long l = calculatorScreen.getMultiplyActionResult();
        setFirstValue(l);
        return l;
    }

    public long getDivideActionResult() {
        long l = calculatorScreen.getDivideActionResult();
        setFirstValue(l);
        return l;
    }

    public void setCurrentScreenState(String s) {
        calculatorScreen.setCurrentScreenState(s);
    }

    public void setIncomingValue(long l) {
        calculatorScreen.setIncomingValue(l);
    }

    public void setIncomingValueAndMainScreenValue(long l) {
        calculatorScreen.setIncomingValue(l);
        setMainScreenText(l);
    }

    public void setMainScreenText(Long l) {
        mainScreen.setText(String.valueOf(l));
    }

    public void setMainScreenText(String s) {
        mainScreen.setText(s);
    }

    public void setFirstValue(long l) {
        calculatorScreen.setFirstValue(l);
    }

    public void setSecondValue(long l) {
        calculatorScreen.setSecondValue(l);
    }

    public void setMemoryValue(long l) {
        calculatorScreen.setMemoryValue(l);
    }

    public void setResult(long l) {
        calculatorScreen.setResult(l);
    }

    public void setMathSymbol(String s) {
        calculatorScreen.setMathSymbol(s);
    }

    public String getMainScreenText() {
        return String.valueOf(mainScreen.getText());
    }

    public long getIncomingValue() {
        return calculatorScreen.getIncomingValue();
    }

    public long getFirstValue() {
        return calculatorScreen.getFirstValue();
    }

    public long getMemoryValue() {
        return calculatorScreen.getMemoryValue();
    }

    public String getMathSymbol() {
        return calculatorScreen.getMathSymbol();
    }

    public void setIsPlusSymbolPressed() {
        isPlusSymbolPressed = true;
        isMinusSymbolPressed = false;
        isMultiplySymbolPressed = false;
        isDivideSymbolPressed = false;
        isEqualSymbolPressed = false;
    }

    public void setIsMinusSymbolPressed() {
        isPlusSymbolPressed = false;
        isMinusSymbolPressed = true;
        isMultiplySymbolPressed = false;
        isDivideSymbolPressed = false;
        isEqualSymbolPressed = false;
    }

    public void setIsMultiplySymbolPressed() {
        isPlusSymbolPressed = false;
        isMinusSymbolPressed = false;
        isMultiplySymbolPressed = true;
        isDivideSymbolPressed = false;
        isEqualSymbolPressed = false;
    }

    public void setIsDivideSymbolPressed() {
        isPlusSymbolPressed = false;
        isMinusSymbolPressed = false;
        isMultiplySymbolPressed = false;
        isDivideSymbolPressed = true;
        isEqualSymbolPressed = false;
    }

    public void setIsEqualSymbolPressed() {
        isPlusSymbolPressed = false;
        isMinusSymbolPressed = false;
        isMultiplySymbolPressed = false;
        isDivideSymbolPressed = false;
        isEqualSymbolPressed = true;
    }

    public void restoreScreen() {
        setMainScreenText(calculatorScreen.getCurrentScreenState());
        if (getMemoryValue() != Long.parseLong(getString(R.string.button_0))) {
            memoryScreen.setText(getString(R.string.button_m));
        }
    }
}