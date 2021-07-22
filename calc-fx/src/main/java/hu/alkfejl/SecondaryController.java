package hu.alkfejl;

import hu.alkfejl.controller.CalcInfoController;
import hu.alkfejl.controller.CalcInfoControllerImp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {
    private CalcInfoController controller = new CalcInfoControllerImp();
    private double arfolyam = controller.getArfolyam();

    @FXML
    public ComboBox<String> tipusCB, mitCB, mireCB;

    @FXML
    public TextField mitTF, mireTF;

    public void vissza() {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTipusCB();

        setBasicCB(mitCB);
        setBasicCB(mireCB);

        setMitTF();
    }

    private void setTipusCB(){
        tipusCB.getItems().setAll("Számrendszer","Pénznem","Súly","Hőmérséklet");
        tipusCB.setValue("Számrendszer");

        tipusCB.valueProperty().addListener((obs,oldValue,newValue) -> {
            switch (newValue) {
                case "Számrendszer":
                    mitCB.getItems().setAll("decimális", "bináris", "oktális", "hexadecimális");
                    mitCB.setValue("decimális");
                    mireCB.getItems().setAll("decimális", "bináris", "oktális", "hexadecimális");
                    mireCB.setValue("decimális");
                    break;
                case "Pénznem":
                    mitCB.getItems().setAll("euró", "magyar forint");
                    mitCB.setValue("euró");
                    mireCB.getItems().setAll("euró", "magyar forint");
                    mireCB.setValue("euró");
                    break;
                case "Súly":
                    mitCB.getItems().setAll("milligramm", "gramm","dekagramm","kilogramm","tonna");
                    mitCB.setValue("milligramm");
                    mireCB.getItems().setAll("milligramm", "gramm","dekagramm","kilogramm","tonna");
                    mireCB.setValue("milligramm");
                    break;
                case "Hőmérséklet":
                    mitCB.getItems().setAll("Celsius-fok", "Fahrenheit-fok");
                    mitCB.setValue("Celsius-fok");
                    mireCB.getItems().setAll("Celsius-fok", "Fahrenheit-fok");
                    mireCB.setValue("Celsius-fok");
                    break;
            }

            mitTF.setText("");
            mireTF.setText("");
        });
    }

    private void setBasicCB(ComboBox<String> cb){
        cb.getItems().setAll("decimális", "bináris", "oktális", "hexadecimális");
        cb.setValue("decimális");

        cb.valueProperty().addListener((obs, newValue, oldValue) -> {
            mitTF.setText("");
            mireTF.setText("");
            mitTF.requestFocus();
        });
    }

    private void setMitTF(){
        mitTF.textProperty().addListener((obs,oldValue,newValue) -> {
            try {
                switch (mitCB.getValue()) {
                    case "decimális":
                        switch (mireCB.getValue()) {
                            case "decimális": mireTF.setText(Long.toString(Long.parseLong(newValue))); break;
                            case "bináris": mireTF.setText(Integer.toBinaryString(Integer.parseInt(newValue))); break;
                            case "oktális": mireTF.setText(String.format("%o", Long.parseLong(newValue))); break;
                            case "hexadecimális": mireTF.setText(String.format("%x", Long.parseLong(newValue)).toUpperCase()); break;
                        }
                        break;
                    case "bináris":
                        switch (mireCB.getValue()) {
                            case "decimális": mireTF.setText(Integer.toString((int) Long.parseLong(newValue, 2))); break;
                            case "bináris": mireTF.setText(Integer.toBinaryString(Integer.parseInt(newValue))); break;
                            case "oktális": mireTF.setText(String.format("%o", Long.parseLong(newValue, 2))); break;
                            case "hexadecimális": mireTF.setText(String.format("%x", Long.parseLong(newValue, 2)).toUpperCase()); break;
                        }
                        break;
                    case "oktális":
                        switch (mireCB.getValue()) {
                            case "decimális": mireTF.setText(Long.toString(Long.parseLong(newValue, 8))); break;
                            case "bináris": mireTF.setText(Integer.toBinaryString(Integer.parseInt(newValue, 8))); break;
                            case "oktális": mireTF.setText(String.format("%o", Long.parseLong(newValue))); break;
                            case "hexadecimális": mireTF.setText(String.format("%x", Long.parseLong(newValue, 8)).toUpperCase()); break;
                        }
                        break;
                    case "hexadecimális":
                        switch (mireCB.getValue()) {
                            case "decimális": mireTF.setText(Long.toString(Long.parseLong(newValue, 16))); break;
                            case "bináris": mireTF.setText(Integer.toBinaryString(Integer.parseInt(newValue, 16))); break;
                            case "oktális": mireTF.setText(String.format("%o", Long.parseLong(newValue, 16))); break;
                            case "hexadecimális": mireTF.setText(newValue.toUpperCase()); break;
                        }
                        break;

                    case "euró":
                        switch (mireCB.getValue()) {
                            case "euró": mireTF.setText(Double.toString(Math.abs(Double.parseDouble(newValue)))); break;
                            case "magyar forint": mireTF.setText(Double.toString(Math.abs(Double.parseDouble(newValue) * arfolyam))); break;
                        }
                        break;
                    case "magyar forint":
                        switch (mireCB.getValue()) {
                            case "euró": mireTF.setText(String.format("%.3f",Math.abs(Double.parseDouble(newValue) / arfolyam))); break;
                            case "magyar forint": mireTF.setText(Double.toString(Math.abs(Double.parseDouble(newValue)))); break;
                        }
                        break;

                    case "milligramm":
                        switch (mireCB.getValue()) {
                            case "milligramm": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                            case "gramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 1000)); break;
                            case "dekagramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 10000)); break;
                            case "kilogramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 1000000)); break;
                            case "tonna": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 1000000000)); break;
                        }
                        break;
                    case "gramm":
                        switch (mireCB.getValue()) {
                            case "milligramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 1000)); break;
                            case "gramm": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                            case "dekagramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 10)); break;
                            case "kilogramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 1000)); break;
                            case "tonna": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 1000000)); break;
                        }
                        break;
                    case "dekagramm":
                        switch (mireCB.getValue()) {
                            case "milligramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 10000)); break;
                            case "gramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 10)); break;
                            case "dekagramm": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                            case "kilogramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 100)); break;
                            case "tonna": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 100000)); break;
                        }
                        break;
                    case "kilogramm":
                        switch (mireCB.getValue()) {
                            case "milligramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 1000000)); break;
                            case "gramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 1000)); break;
                            case "dekagramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 100)); break;
                            case "kilogramm": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                            case "tonna": mireTF.setText(Double.toString(Double.parseDouble(newValue) / 1000)); break;
                        }
                        break;
                    case "tonna":
                        switch (mireCB.getValue()) {
                            case "milligramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 1000000000)); break;
                            case "gramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 1000000)); break;
                            case "dekagramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 100000)); break;
                            case "kilogramm": mireTF.setText(Double.toString(Double.parseDouble(newValue) * 1000)); break;
                            case "tonna": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                        }
                        break;

                    case "Celsius-fok":
                        switch (mireCB.getValue()) {
                            case "Celsius-fok": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                            case "Fahrenheit-fok": mireTF.setText(String.format("%.2f", Double.parseDouble(newValue)*1.8 + 32)); break;
                        }
                        break;
                    case "Fahrenheit-fok":
                        switch (mireCB.getValue()) {
                            case "Celsius-fok": mireTF.setText(String.format("%.2f", (Double.parseDouble(newValue)- 32)/1.8 )); break;
                            case "Fahrenheit-fok": mireTF.setText(Double.toString(Double.parseDouble(newValue))); break;
                        }
                        break;
                }
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
                mireTF.setText("Túlcsordulás");
                if(newValue.isEmpty() || newValue.equals("-")) mireTF.setText(newValue);
            }

        });
    }
}