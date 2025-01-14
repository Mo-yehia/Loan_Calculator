package com.finbi.loanCalculator.service;

import com.finbi.loanCalculator.dto.LoanRequestDTO;
import com.finbi.loanCalculator.model.RepaymentScheduleEntry;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    public List<RepaymentScheduleEntry> generateSchedule(LoanRequestDTO loanRequest) {
        List<RepaymentScheduleEntry> schedule = new ArrayList<>();

        BigDecimal loanAmount = loanRequest.getLoanAmount();
        BigDecimal annualInterestRate = loanRequest.getInterestRate().divide(BigDecimal.valueOf(100),10,RoundingMode.HALF_UP);
        int installmentsPerYear = calculateInstallmentsPerYear(loanRequest.getInstallmentType());
        double installmentsGracePeriod = calculateInstallmentsGracePeriod(loanRequest.getGracePeriodMonths(),installmentsPerYear);
        int loanInstallments = calculateLoanInstallments(loanRequest.getRepaymentDurationYears(), installmentsPerYear,(int) installmentsGracePeriod);
        int numberOfTotalInstallments = (int) installmentsGracePeriod + loanInstallments;
        LocalDate installmentDate = loanRequest.getLoanStartDate();

        BigDecimal interestAmount;
        BigDecimal installmentAmount = BigDecimal.ZERO;
        BigDecimal totalPayment;
        BigDecimal closingBalance = loanAmount;
        BigDecimal fixedInstallmentAmount = loanAmount.divide(BigDecimal.valueOf(loanInstallments), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= numberOfTotalInstallments; i++) {
            interestAmount = loanAmount
                    .multiply(annualInterestRate)
                    .divide(BigDecimal.valueOf(installmentsPerYear), 2, RoundingMode.HALF_UP);

            if (i > installmentsGracePeriod) {
                installmentAmount = fixedInstallmentAmount;
                totalPayment = installmentAmount.add(interestAmount);
                closingBalance = loanAmount.subtract(installmentAmount);
            } else {
                installmentAmount = BigDecimal.ZERO;
                totalPayment = interestAmount;
                closingBalance = loanAmount;
            }

            RepaymentScheduleEntry entry = new RepaymentScheduleEntry();
            entry.setSerialNumber(i);
            entry.setInstallmentDate(installmentDate);
            entry.setOpeningBalance(loanAmount);
            entry.setInterestAmount(interestAmount);
            entry.setInstallmentAmount(installmentAmount);
            entry.setTotalPayment(totalPayment);
            entry.setClosingBalance(closingBalance);

            loanAmount = closingBalance;
            schedule.add(entry);

            installmentDate = getNextInstallmentDate(installmentDate, loanRequest.getInstallmentType());
        }
        return schedule;

    }

    public double calculateInstallmentsGracePeriod(double monthsGracePeriod, double installmentsPerYear) {
        return monthsGracePeriod/12*installmentsPerYear;
    }

    public int calculateLoanInstallments(int durationYears,int installmentsPerYear, int installmentsGracePeriod) {
        return (durationYears*installmentsPerYear)-installmentsGracePeriod;
    }

    public int calculateInstallmentsPerYear(String installmentType) {
        return switch (installmentType.toLowerCase()) {
            case "annual" -> 1;
            case "semi-annual" -> 2;
            case "quarterly" -> 4;
            case "monthly" -> 12;
            default -> throw new IllegalArgumentException("Invalid installment type");
        };
    }

    public LocalDate getNextInstallmentDate(LocalDate currentDate, String installmentType) {
        return switch (installmentType.toLowerCase()) {
            case "annual" -> currentDate.plusYears(1);
            case "semi-annual" -> currentDate.plusMonths(6);
            case "quarterly" -> currentDate.plusMonths(3);
            case "monthly" -> currentDate.plusMonths(1);
            default -> throw new IllegalArgumentException("Invalid installment type");
        };
    }

}
