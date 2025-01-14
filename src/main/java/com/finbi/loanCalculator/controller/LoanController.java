package com.finbi.loanCalculator.controller;



import com.finbi.loanCalculator.dto.LoanRequestDTO;
import com.finbi.loanCalculator.model.RepaymentScheduleEntry;
import com.finbi.loanCalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/")
    public String showLoanForm(Model model) {
        model.addAttribute("loanRequest", new LoanRequestDTO());
        return "loanForm"; // Render loanForm.html
    }

    @PostMapping("/schedule")
    public String calculateSchedule(LoanRequestDTO loanRequest, Model model) {
        try {
            // Generate repayment schedule
            List<RepaymentScheduleEntry> schedule = loanService.generateSchedule(loanRequest);

            // Add the schedule to the model
            model.addAttribute("schedule", schedule);
            return "scheduleTable"; // Render scheduleTable.html
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();

            // Pass error message to the model
            model.addAttribute("error", "An error occurred while generating the repayment schedule. Please check your inputs and try again.");
            return "loanForm"; // Redirect back to the form
        }
    }
}
