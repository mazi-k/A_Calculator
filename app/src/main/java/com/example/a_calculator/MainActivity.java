package com.example.a_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CalculatorView{
    private TextView resultText;
    private Calculator calculator;
    private final static String KEY = "Calculator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.input_text_field);
        calculator = new Calculator(this);
        initialButtons();

        findViewById(R.id.button_settings).setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable(KEY);
        showResult(calculator.getExpressionString());
    }

    @Override
    public void showResult(String result) {
        resultText.setText(result);
    }

    private void initialButtons(){
        findViewById(R.id.button_num0).setOnClickListener(v -> calculator.onButton0Clicked());
        findViewById(R.id.button_num1).setOnClickListener(v -> calculator.onButton1Clicked());
        findViewById(R.id.button_num2).setOnClickListener(v -> calculator.onButton2Clicked());
        findViewById(R.id.button_num3).setOnClickListener(v -> calculator.onButton3Clicked());
        findViewById(R.id.button_num4).setOnClickListener(v -> calculator.onButton4Clicked());
        findViewById(R.id.button_num5).setOnClickListener(v -> calculator.onButton5Clicked());
        findViewById(R.id.button_num6).setOnClickListener(v -> calculator.onButton6Clicked());
        findViewById(R.id.button_num7).setOnClickListener(v -> calculator.onButton7Clicked());
        findViewById(R.id.button_num8).setOnClickListener(v -> calculator.onButton8Clicked());
        findViewById(R.id.button_num9).setOnClickListener(v -> calculator.onButton9Clicked());
        findViewById(R.id.button_point).setOnClickListener(v -> calculator.onButtonPointClicked());
        findViewById(R.id.button_plus).setOnClickListener(v -> calculator.onButtonPlusClicked());
        findViewById(R.id.button_minus).setOnClickListener(v -> calculator.onButtonMinusClicked());
        findViewById(R.id.button_multi).setOnClickListener(v -> calculator.onButtonMultiClicked());
        findViewById(R.id.button_div).setOnClickListener(v -> calculator.onButtonDivClicked());
        findViewById(R.id.button_eq).setOnClickListener(v -> calculator.onButtonEqualClicked());
        findViewById(R.id.button_c).setOnClickListener(v -> calculator.onButtonCancelClicked());
    }
}