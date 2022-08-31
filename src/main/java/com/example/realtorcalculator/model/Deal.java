package com.example.realtorcalculator.model;

import com.example.realtorcalculator.util.ResultStringFormatter;

public class Deal {
    private boolean showThousands = false;
    private Double realSum;
    private Double percent;
    private Double initialFee;
    private InitialFeeStatus initialFeeStatus = InitialFeeStatus.NO_INITIAL_FEE;
    private RoundingResult roundingResult = RoundingResult.NO_ROUND;

    public boolean isShowThousands() {
        return showThousands;
    }

    public void setShowThousands(boolean showThousands) {
        this.showThousands = showThousands;
    }

    public Double getRealSum() {
        return realSum;
    }

    public void setRealSum(Double realSum) {
        this.realSum = realSum;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getInitialFee() {
        return initialFee;
    }

    public void setInitialFee(Double initialFee) {
        this.initialFee = initialFee;
    }

    public InitialFeeStatus getInitialFeeStatus() {
        return initialFeeStatus;
    }

    public void setInitialFeeStatus(InitialFeeStatus initialFeeStatus) {
        this.initialFeeStatus = initialFeeStatus;
    }

    public RoundingResult getRoundingResult() {
        return roundingResult;
    }

    public void setRoundingResult(RoundingResult roundingResult) {
        this.roundingResult = roundingResult;
    }

    public String getResult() {
        Double creditSum = initialFeeStatus.equals(InitialFeeStatus.NEED_CASH) ? realSum + initialFee : realSum - initialFee;

        Double resultSum = creditSum / (1.0 - (percent / 100));
        Double resultInitialFee = resultSum - creditSum;

        String sum = getFormattedStringForResult(resultSum);
        String initialFee = getFormattedStringForResult(resultInitialFee);
        String credit = roundingResult.equals(RoundingResult.NO_ROUND)
                ? ResultStringFormatter.addSpacesToResultString(String.format("%.0f", creditSum), this)
                : ResultStringFormatter.addSpacesToResultString(String.valueOf(reformatResultString(sum) - reformatResultString(initialFee)), this);

        return ("Cумма сделки: " + sum + "\n"
                + "Из которых:" + "\n"
                + initialFee + " - первоначальный взнос" + "\n"
                + credit + " - сумма кредита");

    }

    private String getFormattedStringForResult(Double value) {
        String result = String.format("%.0f", value);
        result = roundingResult.equals(RoundingResult.NO_ROUND) ? result : ResultStringFormatter.roundUp(result, this);
        return ResultStringFormatter.addSpacesToResultString(result, this);
    }

    private int reformatResultString(String string) {
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }
}
