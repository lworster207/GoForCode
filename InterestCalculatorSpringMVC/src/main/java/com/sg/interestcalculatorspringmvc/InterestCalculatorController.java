/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorspringmvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ward
 */
@Controller
public class InterestCalculatorController {

    @RequestMapping(value = "/calculateInterest", method = RequestMethod.POST)
    public String calculateInterest(HttpServletRequest request, Map<String, Object> model) {
        String inYears = request.getParameter("years");
        BigDecimal years = new BigDecimal(inYears);
        String inRate = request.getParameter("rate");
        BigDecimal rate = new BigDecimal(inRate);
        String inPrincipal = request.getParameter("principal");
        BigDecimal principal = new BigDecimal(inPrincipal);

        // A List to hold our factors
        List<String> annualList = new ArrayList<>();
        // The number of years calculate interest
        int numYears;

        // the number of time periods - based on the user inputted period
        // Daily = 365, Monthly = 12, or Quartely = 4
        BigDecimal numberOfPeriods;

        // Daily, Monthly, or Quarterly
        String compoundPeriod = "";

        /*
        double investment;
        double currentBalance;
        double yearlyRate;
        double calcRate;
         */
        BigDecimal investment;
        BigDecimal currentBalance;
        BigDecimal yearlyRate;
        BigDecimal calcRate;

        BigDecimal one = new BigDecimal("1");
        BigDecimal oneHundred = new BigDecimal("100");

        Scanner userInput = new Scanner(System.in);

        numYears = Integer.parseInt(request.getParameter("years"));
        compoundPeriod = "Quarterly";
        yearlyRate = new BigDecimal(Double.parseDouble(request.getParameter("rate")));
        investment = new BigDecimal(Double.parseDouble(request.getParameter("principal")));

        switch (compoundPeriod) {
            case "Monthly":
                numberOfPeriods = new BigDecimal("12");
                break;
            case "Daily":
                numberOfPeriods = new BigDecimal("365");
                break;
            default:
                numberOfPeriods = new BigDecimal("4");
                break;
        }

        // calculate the period rate
        calcRate = yearlyRate.divide(numberOfPeriods, 2, RoundingMode.HALF_UP);
        currentBalance = investment;

        for (int yearNo = 1; yearNo <= numYears; yearNo++) {
            for (int periodCtr = 1; periodCtr <= numberOfPeriods.intValue(); periodCtr++) {
                currentBalance = currentBalance.multiply(one.add(calcRate.divide(oneHundred)));
            }
            annualList.add(
                    "Year " + yearNo + " Beginning Balance: "
                    + investment.multiply(oneHundred).divide(oneHundred, 2, RoundingMode.HALF_UP)
                    + " Interest earned: " + currentBalance.subtract(investment).multiply(oneHundred).divide(oneHundred, 2, RoundingMode.HALF_UP)
                    + " Ending Balance: " + currentBalance.multiply(oneHundred).divide(oneHundred, 2, RoundingMode.HALF_UP));

            investment = currentBalance;
        }

        // Set all the results in the model Map so they
        // are available to result.jsp
        model.put("annualList", annualList);
        model.put("rate", inRate);
        model.put("years", inYears);
        model.put("principal", inPrincipal);

        return "result";

    }

}
