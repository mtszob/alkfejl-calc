package hu.alkfejl;

import hu.alkfejl.controller.CalcInfoController;
import hu.alkfejl.controller.CalcInfoControllerImp;
import hu.alkfejl.model.CalcInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Locale;

public class PrimaryController {
    private boolean muveletVege = true;
    private CalcInfoController controller = new CalcInfoControllerImp();
    private CalcInfo ci = new CalcInfo(0);

    @FXML
    TextField display;

    public void atvaltas() {
        try {
            App.setRoot("secondary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void one() {
        if(muveletVege) {
            display.setText("1");
            muveletVege = false;
        } else {
            display.appendText("1");
        }
    }

    public void two() {
        if(muveletVege) {
            display.setText("2");
            muveletVege = false;
        } else {
            display.appendText("2");
        }
    }

    public void three() {
        if(muveletVege) {
            display.setText("3");
            muveletVege = false;
        } else {
            display.appendText("3");
        }
    }

    public void four() {
        if(muveletVege) {
            display.setText("4");
            muveletVege = false;
        } else {
            display.appendText("4");
        }
    }

    public void five() {
        if(muveletVege) {
            display.setText("5");
            muveletVege = false;
        } else {
            display.appendText("5");
        }
    }

    public void six() {
        if(muveletVege) {
            display.setText("6");
            muveletVege = false;
        } else {
            display.appendText("6");
        }
    }

    public void seven() {
        if(muveletVege) {
            display.setText("7");
            muveletVege = false;
        } else {
            display.appendText("7");
        }
    }

    public void eight() {
        if(muveletVege) {
            display.setText("8");
            muveletVege = false;
        } else {
            display.appendText("8");
        }
    }

    public void nine() {
        if(muveletVege) {
            display.setText("9");
            muveletVege = false;
        } else {
            display.appendText("9");
        }
    }

    public void zero() {
        if(muveletVege) {
            display.setText("0");
        } else if(!display.getText().equals("0")) {
            display.appendText("0");
        }
    }

    private void appendOperation(String operation) {
        String str = display.getText();
        if (str.substring(str.length() - 1).equals(".") ||
                str.substring(str.length() - 1).equals("+") ||
                str.substring(str.length() - 1).equals("-") ||
                str.substring(str.length() - 1).equals("*") ||
                str.substring(str.length() - 1).equals("/") ||
                str.substring(str.length() - 1).equals("\\") ||
                str.substring(str.length() - 1).equals("%")) {

            display.setText(str.substring(0, str.length() - 1) + operation);
        } else {
            if (!operation.equals(".") && (str.contains("+") || str.contains("-") ||
               str.contains("*") || str.contains("/") || str.contains("\\") || str.contains("%"))) {

                result();
            }

            display.appendText(operation);
        }
    }

    public void comma() {
        if(muveletVege){
            display.setText("0.");
            muveletVege = false;
        }else {
            String str = display.getText();
            int limit = 1;

            if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/"))
                limit++;

            if (str.length() - str.replace(".", "").length() < limit) {
                appendOperation(".");
            }
        }
    }

    public void add() {
        appendOperation("+");
        muveletVege = false;
    }

    public void sub() {
        appendOperation("-");
        muveletVege = false;
    }

    public void mul() {
        appendOperation("*");
        muveletVege = false;
    }

    public void div() {
        appendOperation("/");
        muveletVege = false;
    }

    public void intdiv() {
        appendOperation("\\");
        muveletVege = false;
    }

    public void mod() {
        appendOperation("%");
        muveletVege = false;
    }

    public void result() {
        String str = display.getText();
        double result;

        if(!str.endsWith("+") && !str.endsWith("-") && !str.endsWith("*") && !str.endsWith("/") &&
           !str.endsWith("\\") && !str.endsWith("%") && !str.endsWith(".")) {

            String[] params = str.split("\\+|\\*|/|%|\\\\");

            if (str.contains("+")) {
                result = Double.parseDouble(params[0]) + Double.parseDouble(params[1]);

                if(result % 1 == 0) display.setText(Integer.toString((int)result));
                else display.setText(Double.toString(result));

            } else if (str.contains("*")) {
                result = Double.parseDouble(params[0]) * Double.parseDouble(params[1]);

                if(result % 1 == 0) display.setText(Integer.toString((int)result));
                else display.setText(Double.toString(result));

            } else if (str.contains("/")) {
                result = Double.parseDouble(params[0]) / Double.parseDouble(params[1]);

                if(result % 1 == 0) display.setText(Integer.toString((int)result));
                else display.setText(Double.toString(result));

            } else if (str.contains("\\")) {
                display.setText(Integer.toString(Math.floorDiv((int) Double.parseDouble(params[0]), (int) Double.parseDouble(params[1]))));
            } else if (str.contains("%")) {
                result = Double.parseDouble(params[0]) % Double.parseDouble(params[1]);

                if(result % 1 == 0) display.setText(Integer.toString((int)result));
                else display.setText(Double.toString(result));
            } else if (str.contains("-") && !str.contains("E")) {
                if ((str.length() - str.replace("-","").length()) == 2) {
                    str = str.substring(1);

                    params = str.split("-", 2);
                    result = (-1) * (Double.parseDouble(params[0]) + Double.parseDouble(params[1]));

                    if(result % 1 == 0) display.setText(Integer.toString((int)result));
                } else if(!str.startsWith("-")){
                    params = str.split("-", 2);
                    result = Double.parseDouble(params[0]) - Double.parseDouble(params[1]);
                    if(result % 1 == 0)
                        display.setText(Integer.toString((int)result));
                    else
                        display.setText(Double.toString(result));
                }
            }

            muveletVege = true;
        }
    }

    private  boolean isAdvancedOperation(){
        String str = display.getText();

        if (!str.endsWith("+") && !str.endsWith("-") && !str.endsWith("*") && !str.endsWith("/") &&
                !str.endsWith("\\") && !str.endsWith("%") && !str.endsWith(".")){

            if ((str.contains("+") || str.contains("-") || str.contains("*") ||
                    str.contains("/") || str.contains("\\") || str.contains("%"))){

                result();
            }

            muveletVege = true;
            return true;
        }

        return false;
    }

    public void rec() {
        if(isAdvancedOperation()){
            display.setText(Double.toString( 1/Double.parseDouble(display.getText())));
        }
    }

    public void square() {
        if(isAdvancedOperation()){
            display.setText(Double.toString(Double.parseDouble(display.getText())*Double.parseDouble(display.getText())));
        }
    }

    public void sqrt() {
        if(isAdvancedOperation()){
            double result = Math.sqrt(Double.parseDouble(display.getText()));
            if (result % 1 == 0) display.setText(Integer.toString((int)result));
            else display.setText(Double.toString(result));
        }
    }

    public void sin() {
        if(isAdvancedOperation()){
            double result = Math.sin(Math.toRadians(Double.parseDouble(display.getText())));
            display.setText(String.format(Locale.ENGLISH,"%.8f", result));
        }
    }

    public void cos() {
        if(isAdvancedOperation()){
            double result = Math.cos(Math.toRadians(Double.parseDouble(display.getText())));
            display.setText(String.format(Locale.ENGLISH,"%.8f", result));
        }
    }

    public void tan() {
        if(isAdvancedOperation() && Double.parseDouble(display.getText()) % 180 != 90){
            double result = Math.tan(Math.toRadians(Double.parseDouble(display.getText())));
            display.setText(String.format(Locale.ENGLISH,"%.8f", result));
        }else{
            display.setText("Infinity");
        }
    }

    public void clear() {
        display.setText("0");
        muveletVege = true;
    }

    public void mClear() {
        ci.setMentettErtek(0);
        controller.setInfo(ci);
    }

    public void mRecall() {
        if(controller.getInfo() != null) {
            double mentettErtek = controller.getInfo().getMentettErtek();
            String str = display.getText();

            if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/") ||
               str.endsWith("\\") || str.endsWith("%")){

                if (mentettErtek % 1 == 0) display.appendText(Integer.toString((int) mentettErtek));
                else display.appendText(Double.toString(mentettErtek));
            }else {
                if (mentettErtek % 1 == 0) display.setText(Integer.toString((int) mentettErtek));
                else display.setText(Double.toString(mentettErtek));
            }
        }
    }

    public void mStore() {
        result();
        ci.setMentettErtek(Double.parseDouble(display.getText()));
        controller.setInfo(ci);
    }
}