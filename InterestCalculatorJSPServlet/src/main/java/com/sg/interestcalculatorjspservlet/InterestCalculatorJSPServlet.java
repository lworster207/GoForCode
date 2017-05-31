/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
@WebServlet(name = "InterestCalculatorJSPServlet", urlPatterns = {"/InterestCalculatorJSPServlet"})
public class InterestCalculatorJSPServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        request.setAttribute("annualList", annualList);

        // Get the Request Dispatcher for result.jsp and forward the
        // request to result.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
