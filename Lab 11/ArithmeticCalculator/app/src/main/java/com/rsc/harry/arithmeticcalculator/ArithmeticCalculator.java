package com.rsc.harry.arithmeticcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Window;
import android.view.WindowManager;

public class ArithmeticCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic_calculator);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#00897B"));

        final Calculate calculate = new Calculate();

        final TextView screen1 = (TextView)findViewById(R.id.textView);
        final TextView answer = (TextView)findViewById(R.id.ansView);

        Button num1 = (Button) findViewById(R.id.num1);
        Button num2 = (Button) findViewById(R.id.num2);
        Button num3 = (Button) findViewById(R.id.num3);
        Button num4 = (Button) findViewById(R.id.num4);
        Button num5 = (Button) findViewById(R.id.num5);
        Button num6 = (Button) findViewById(R.id.num6);
        Button num7 = (Button) findViewById(R.id.num7);
        Button num8 = (Button) findViewById(R.id.num8);
        Button num9 = (Button) findViewById(R.id.num9);
        Button num0 = (Button) findViewById(R.id.num0);

        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        Button divide = (Button) findViewById(R.id.division);
        Button multi = (Button) findViewById(R.id.multi);
        Button equals = (Button) findViewById(R.id.equals);

        Button clear = (Button) findViewById(R.id.clear);
        Button del = (Button) findViewById(R.id.del);
        Button period = (Button) findViewById(R.id.period);
        Button plus_or_minus = (Button) findViewById(R.id.plus_or_minus);

        num1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("1");
            }
        });

        num2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("2");
            }
        });

        num3.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("3");
            }
        });

        num4.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("4");
            }
        });

        num5.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("5");
            }
        });

        num6.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("6");
            }
        });

        num7.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("7");
            }
        });

        num8.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("8");
            }
        });

        num9.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("9");
            }
        });

        num0.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (calculate.getEqualPressed()) {
                    calculate.reset();
                    calculate.setEqualPressed(false);
                    screen1.setText("");
                    answer.setText("");
                }

                screen1.append("0");
            }
        });

        period.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if (calculate.checkDecimal(input) == 1)
                    screen1.append(".");
            }
        });

        plus.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if(input.length() != 0) {
                    if (calculate.checkExistingOperator()) {
                        input = calculate.checkLastChar(input, "+");
                        screen1.setText(input);
                    } else {
                        calculate.createFirst(input);
                        screen1.append("+");
                        calculate.setType("+");
                    }
                }
            }
        });

        minus.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if(input.length() != 0) {
                    if (calculate.checkExistingOperator()) {
                        input = calculate.checkLastChar(input, "-");
                        screen1.setText(input);
                    } else {
                        calculate.createFirst(input);
                        screen1.append("-");
                        calculate.setType("-");
                    }
                }
            }
        });

        divide.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if(input.length() != 0) {
                    if (calculate.checkExistingOperator()) {
                        input = calculate.checkLastChar(input, "÷");
                        screen1.setText(input);
                    } else {
                        calculate.createFirst(input);
                        screen1.append("÷");
                        calculate.setType("÷");
                    }
                }
            }
        });

        multi.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if(input.length() != 0) {
                    if(calculate.checkExistingOperator()){
                        input = calculate.checkLastChar(input, "×");
                        screen1.setText(input);
                    }
                    else {
                        calculate.createFirst(input);
                        screen1.append("×");
                        calculate.setType("×");
                    }
                }
            }
        });

        equals.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if (screen1.getText().length() != 0){
                    char last = input.charAt(input.length() - 1);

                    if (Character.isDigit(last) && calculate.getType().equals("")){
                        answer.setText(calculate.factorAnswer(Double.valueOf(input)));

                        calculate.setEqualPressed(true);
                    }
                    else if (Character.isDigit(last) && !calculate.getType().equals("")){
                        calculate.createSecond(input);
                        answer.setText(calculate.arithmetic());

                        calculate.setEqualPressed(true);
                    }
                }
            }
        });

        clear.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                calculate.reset();
                screen1.setText("");
                answer.setText("");
            }
        });

        del.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                if(input.length() != 0 && calculate.getEqualPressed() == false){
                    char last = input.charAt(input.length() - 1);

                    if(!Character.isDigit(last) && calculate.getPlusOrMinusStatus())
                        calculate.setType("");

                    calculate.deletePlusOrMinus(last);

                    screen1.setText(input.substring(0, input.length() - 1));
                }
            }
        });

        plus_or_minus.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                String input = screen1.getText().toString();

                int ret = calculate.checkPlusOrMinus(input);

                switch(ret){
                    case 0:
                        break;
                    case 1:
                        screen1.append("-");
                        break;
                    case 2:
                        screen1.append("-");
                        break;
                    case 3:
                        screen1.setText(input.substring(0, input.length() - 1));
                        break;
                    case 4:
                        screen1.setText(input.substring(0, input.length() - 1));
                        break;
                    case 5:
                        calculate.reset();
                        calculate.setEqualPressed(false);
                        screen1.setText("");
                        answer.setText("");

                        screen1.append("-");
                        break;
                }
            }
        });
    }
}
