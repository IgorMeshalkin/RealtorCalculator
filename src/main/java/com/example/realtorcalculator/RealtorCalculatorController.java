package com.example.realtorcalculator;

import com.example.realtorcalculator.model.Deal;
import com.example.realtorcalculator.model.InitialFeeStatus;
import com.example.realtorcalculator.model.RoundingResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RealtorCalculatorController implements Initializable {

    @FXML
    private Button gradeButton;

    @FXML
    private RadioButton needCashRadioButton;

    @FXML
    private RadioButton noPvRadioButton;

    @FXML
    private RadioButton partPvRadioButton;

    @FXML
    private TextField percentField;

    @FXML
    private TextField pvField;

    @FXML
    private Label pvLabel;

    @FXML
    private Label pvLabelShadow;

    @FXML
    private TextField realSumField;

    @FXML
    private Button resultButton;

    @FXML
    private Label resultLabel;

    @FXML
    private RadioButton notRoundUpRadioButton;

    @FXML
    private RadioButton roundUpTo10RadioButton;

    @FXML
    private RadioButton roundUpTo50RadioButton;

    @FXML
    private Button rubButton;

    @FXML
    private Button tRubButton;

    private final ToggleGroup roundButtonsGroup = new ToggleGroup();

    private final ToggleGroup availabilityInitialFeeButtonsGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Deal deal = new Deal();
        pvField.setText("0");

        //Группа Radiobutton работающих с округлением результата:
        notRoundUpRadioButton.setToggleGroup(roundButtonsGroup);
        notRoundUpRadioButton.setSelected(true);
        roundUpTo10RadioButton.setToggleGroup(roundButtonsGroup);
        roundUpTo50RadioButton.setToggleGroup(roundButtonsGroup);

        notRoundUpRadioButton.setOnAction(event -> {
            deal.setRoundingResult(RoundingResult.NO_ROUND);
        });

        roundUpTo10RadioButton.setOnAction(event -> {
            deal.setRoundingResult(RoundingResult.ROUND_TO_10);
        });

        roundUpTo50RadioButton.setOnAction(event -> {
            deal.setRoundingResult(RoundingResult.ROUND_TO_50);
        });

        //Группа Radiobutton работающих со статусом первоначального взноса:
        noPvRadioButton.setToggleGroup(availabilityInitialFeeButtonsGroup);
        noPvRadioButton.setSelected(true);
        partPvRadioButton.setToggleGroup(availabilityInitialFeeButtonsGroup);
        needCashRadioButton.setToggleGroup(availabilityInitialFeeButtonsGroup);

        noPvRadioButton.setOnAction(event -> {
            deal.setInitialFeeStatus(InitialFeeStatus.NO_INITIAL_FEE);
            pvField.setText("0");
            pvLabel.setText("Первоначальный взнос:");
            pvLabelShadow.setText("Первоначальный взнос:");
        });

        partPvRadioButton.setOnAction(event -> {
            deal.setInitialFeeStatus(InitialFeeStatus.PARTIALLY_INITIAL_FEE);
            pvLabel.setText("Первоначальный взнос:");
            pvLabelShadow.setText("Первоначальный взнос:");
        });

        needCashRadioButton.setOnAction(event -> {
            deal.setInitialFeeStatus(InitialFeeStatus.NEED_CASH);
            pvLabel.setText("Сколько нужно получить?");
            pvLabelShadow.setText("Сколько нужно получить?");
        });

        //Кнопки выбора единицы измерения суммы (руб. или тыс.руб.):
        String defaultButtonStyle = tRubButton.getStyle();
        tRubButton.setStyle("-fx-background-color: silver");

        rubButton.setOnAction(event -> {
            deal.setShowThousands(true);
            rubButton.setStyle("-fx-background-color: silver");
            tRubButton.setStyle(defaultButtonStyle);
        });

        tRubButton.setOnAction(event -> {
            deal.setShowThousands(false);
            tRubButton.setStyle("-fx-background-color: silver");
            rubButton.setStyle(defaultButtonStyle);
        });


        //Кнопка "Получить результат"
        resultButton.setOnAction(event -> {
            deal.setRealSum(Double.parseDouble(realSumField.getText()));
            deal.setPercent(Double.parseDouble(percentField.getText()));
            deal.setInitialFee(Double.parseDouble(pvField.getText()));

            resultLabel.setText(deal.getResult());
        });
    }
}
