package com.example.a_calculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculator implements Parcelable {
    private CalculatorView view;
    private ArrayList<String> expression = new ArrayList<>();
    private final String WRONG = "Wrong expression!";

    public Calculator(CalculatorView view) {
        this.view = view;
    }

    protected Calculator(Parcel in) {
        expression = in.createStringArrayList();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public String getExpressionString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < expression.size(); i++) {
            if (res.length() > 0) {
                res.append(expression.get(i));
            } else {
                res = new StringBuilder(String.valueOf(expression.get(0)));
            }
        }
        return res.toString();
    }

    private void expressionFillingNum(String num) {
        if (expression.isEmpty()) {
            expression.add(num);
        } else if (expression.get(expression.size() - 1).endsWith(".") || isNum(expression.get(expression.size() - 1))) {
            expression.add(expression.get(expression.size() - 1).concat(num));
            expression.remove(expression.size() - 2);
        } else {
            expression.add(num);
        }
        printResult(expression);
    }

    private void expressionFillingSign(String sign) {
        if(expression.size() == 0){
            view.showResult(WRONG);
        }
        else if (isSign(expression.get(expression.size() - 1)) ||
                expression.get(expression.size() - 1).equals(".")) {
            expression.remove(expression.size() - 1);
        }
        expression.add(sign);
        printResult(expression);
    }

    private boolean isNum(String str) {
        return str.endsWith("1") || str.endsWith("2") || str.endsWith("3") ||
                str.endsWith("4") || str.endsWith("5") || str.endsWith("6") ||
                str.endsWith("7") || str.endsWith("8") || str.endsWith("9") || str.endsWith("0");
    }

    private boolean isSign(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals(".");
    }

    public void printResult(ArrayList<String> list) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (res.length() > 0) {
                res.append(list.get(i));
            } else {
                res = new StringBuilder(String.valueOf(list.get(0)));
            }
        }
        view.showResult(res.toString());
    }

    public void onButton1Clicked() {
        expressionFillingNum("1");
    }

    public void onButton2Clicked() {
        expressionFillingNum("2");
    }

    public void onButton3Clicked() {
        expressionFillingNum("3");
    }

    public void onButton4Clicked() {
        expressionFillingNum("4");
    }

    public void onButton5Clicked() {
        expressionFillingNum("5");
    }

    public void onButton6Clicked() {
        expressionFillingNum("6");
    }

    public void onButton7Clicked() {
        expressionFillingNum("7");
    }

    public void onButton8Clicked() {
        expressionFillingNum("8");
    }

    public void onButton9Clicked() {
        expressionFillingNum("9");
    }

    public void onButton0Clicked() {
        expressionFillingNum("0");
    }

    public void onButtonPlusClicked() {
        expressionFillingSign("+");
    }

    public void onButtonMinusClicked() {
        expressionFillingSign("-");
    }

    public void onButtonMultiClicked() {
        expressionFillingSign("*");
    }

    public void onButtonDivClicked() {
        expressionFillingSign("/");
    }

    public void onButtonPointClicked() {
        if(expression.isEmpty()){
            expression.add("0.");
        }
        else if (!expression.get(expression.size() - 1).contains(".") &&
                !expression.get(expression.size() - 1).endsWith(".") &&
                !isSign(expression.get(expression.size() - 1))) {
            expression.add(expression.get(expression.size() - 1).concat("."));
            expression.remove(expression.size() - 2);
        }
        printResult(expression);
    }

    public void onButtonCancelClicked() {
        if (!expression.isEmpty()) {
            expression.remove(expression.size() - 1);
        }
        printResult(expression);
    }

    public void onButtonEqualClicked() {
        if(expression.isEmpty()){
            expression.add("0");
        }
        if(!isSign(expression.get(0))){
            DecimalFormat decimalFormat = new DecimalFormat("#.########");
            String result = decimalFormat.format(parseExpression());
            view.showResult(result);
        } else {
            view.showResult(WRONG);
        }
    }

    public double parseExpression() {
        double result;
        while (expression.size() != 1) {
            if (expression.contains("/")) {
                int index = expression.indexOf("/");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = binaryOperation(first, second, Operation.DIV);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            } else if (expression.contains("*")) {
                int index = expression.indexOf("*");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = binaryOperation(first, second, Operation.MULT);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            } else if (expression.contains("-")) {
                int index = expression.indexOf("-");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = binaryOperation(first, second, Operation.SUB);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            } else if (expression.contains("+")) {
                int index = expression.indexOf("+");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = binaryOperation(first, second, Operation.ADD);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            }
        }
        return Double.parseDouble(expression.get(0));
    }


    double binaryOperation(double argOne, double argTwo, Operation operation) {
        switch (operation) {
            case ADD:
                return argOne + argTwo;
            case SUB:
                return argOne - argTwo;
            case MULT:
                return argOne * argTwo;
            case DIV:
                return argOne / argTwo;
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(expression);
    }
}
