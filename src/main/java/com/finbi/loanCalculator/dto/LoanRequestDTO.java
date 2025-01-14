package com.finbi.loanCalculator.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanRequestDTO {

    private BigDecimal loanAmount;
    private int repaymentDurationYears;
    private int gracePeriodMonths;
    private BigDecimal interestRate;
    private LocalDate loanStartDate;
    private String installmentType; // Installment type (Annual, Semi-Annual, Quarterly, Monthly)

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getRepaymentDurationYears() {
        return repaymentDurationYears;
    }

    public void setRepaymentDurationYears(int repaymentDurationYears) {
        this.repaymentDurationYears = repaymentDurationYears;
    }

    public int getGracePeriodMonths() {
        return gracePeriodMonths;
    }

    public void setGracePeriodMonths(int gracePeriodMonths) {
        this.gracePeriodMonths = gracePeriodMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public String getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(String installmentType) {
        this.installmentType = installmentType;
    }
}
