package com.example.realtorcalculator.controller;

import com.example.realtorcalculator.RealtorCalculatorController;
import com.example.realtorcalculator.model.Deal;
import com.example.realtorcalculator.util.ResultStringFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IncorrectUnitsController implements Initializable {

    private RealtorCalculatorController mainController = new RealtorCalculatorController();

    @FXML
    private Button okButton;

    @FXML
    private Label textLabel;

    @FXML
    private Label textLabelShadow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Deal deal = mainController.getDeal();
        String text = "Сумма "
                + ResultStringFormatter.addSpacesToResultString(String.format("%.0f", deal.getRealSum()), deal) + "\n"
                + " слишком " + (deal.isShowThousands() ? "мала" : "велика") + "." + "\n"
                + "Проверьте единицы измерения." + "\n"
                + "(руб. | тыс.руб.)";

        textLabel.setText(text);
        textLabelShadow.setText(text);
    }

    @FXML
    public void closeCurrentWindow(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
