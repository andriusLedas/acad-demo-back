package com.example.sick.api.model.request;

import java.math.BigDecimal;
import java.util.Date;

public record PersonalInformationDAORequest(

    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    long pid,
    Date dateOfBirth,
    String maritalStatus,
    int numberOfChildren,
    String citizenship,
    BigDecimal monthlyIncome

) {
}
