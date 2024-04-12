package com.example.sick.service;

import com.example.sick.api.model.request.CalculatorRequest;
import com.example.sick.api.model.response.CalculatorResponse;
import com.example.sick.domain.CalculatorDAOResponse;
import com.example.sick.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;

    @Autowired
    public CalculatorService(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    public CalculatorResponse calculateMonthlyPayment(CalculatorRequest calculatorRequest) {

        CalculatorDAOResponse calculatorDAOResponse = calculatorRepository.selectAllInterestRate();

        BigDecimal interestRate = BigDecimal.valueOf(calculatorRequest.isEcoFriendly() ?
                calculatorDAOResponse.eco() :
                calculatorDAOResponse.regular());

  //      BigDecimal interestRate = BigDecimal.valueOf(5);

        BigDecimal rate = interestRate.divide(BigDecimal.valueOf(100));

        BigDecimal totalPayableInterest = (calculatorRequest.carValue().subtract(calculatorRequest.downPayment())
                .subtract(calculateResidualValue(calculatorRequest.carValue(), calculatorRequest.residualValuePercentage())))
                .multiply(rate.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP)) // Assuming 2 decimal places for monthly rate
                .multiply(BigDecimal.valueOf(calculatorRequest.period()).divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP));

        BigDecimal monthlyInterest = totalPayableInterest.divide(BigDecimal.valueOf(calculatorRequest.period()), 2, RoundingMode.HALF_UP);

        BigDecimal monthlyLoanAmount = (calculatorRequest.carValue().subtract(calculatorRequest.downPayment())
                .subtract(calculateResidualValue(calculatorRequest.carValue(), calculatorRequest.residualValuePercentage())))
                .divide(BigDecimal.valueOf(calculatorRequest.period()), 2, RoundingMode.HALF_UP);

        return new CalculatorResponse(monthlyLoanAmount.add(monthlyInterest));
    }

    private BigDecimal calculateResidualValue(BigDecimal carValue, int residualValuePercentage) {
        BigDecimal percentage = BigDecimal.valueOf(residualValuePercentage).divide(BigDecimal.valueOf(100));
        return carValue.multiply(percentage);
    }

}
