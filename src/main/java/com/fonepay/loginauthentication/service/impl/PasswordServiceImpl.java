package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.constants.MetaTableConstants;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.repository.MetaTableRepo;
import com.fonepay.loginauthentication.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private MetaTableRepo metaTableRepo;

    @Override
    public ResponseDTO passwordMatch(String password) {
        final int PASSWORD_TOTAL_COUNT = Integer.parseInt(metaTableRepo.findByName(MetaTableConstants.PASSWORD_TOTAL_COUNT.getName()).getValue());
        final int TOTAL_SPECIAL_CHARACTERS = Integer.parseInt(metaTableRepo.findByName(MetaTableConstants.TOTAL_SPECIAL_CHARACTERS.getName()).getValue());
        final int TOTAL_CAPITAL_LETTERS = Integer.parseInt(metaTableRepo.findByName(MetaTableConstants.TOTAL_CAPITAL_LETTERS.getName()).getValue());
        final int TOTAL_SMALL_LETTERS = Integer.parseInt(metaTableRepo.findByName(MetaTableConstants.TOTAL_SMALL_LETTERS.getName()).getValue());
        final int TOTAL_NUMERIC_VALUES = Integer.parseInt(metaTableRepo.findByName(MetaTableConstants.TOTAL_NUMERIC_VALUES.getName()).getValue());

        ResponseDTO responseDTO = new ResponseDTO();

        if (password.length() < PASSWORD_TOTAL_COUNT) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Please enter a password length more or equal to length " + PASSWORD_TOTAL_COUNT);
            return responseDTO;
        }
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(password);
        if (hasSpecial.results().count() < TOTAL_SPECIAL_CHARACTERS) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Please enter special characters more or equal to length " + TOTAL_SPECIAL_CHARACTERS);
            return responseDTO;
        }
        Pattern smallLetter = Pattern.compile("[a-z]");
        Matcher hasSmallLetter = smallLetter.matcher(password);
        if (hasSmallLetter.results().count() < TOTAL_SMALL_LETTERS) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Please enter small letters more or equal to length " + TOTAL_SMALL_LETTERS);
            return responseDTO;
        }
        Pattern capitalLetter = Pattern.compile("[A-Z]");
        Matcher hasCapitalLetter = capitalLetter.matcher(password);
        if (hasCapitalLetter.results().count() < TOTAL_CAPITAL_LETTERS) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Please enter capital letters more or equal to length " + TOTAL_CAPITAL_LETTERS);
            return responseDTO;
        }
        Pattern numericDigit = Pattern.compile("[0-9]");
        Matcher hasNumericDigit = numericDigit.matcher(password);
        if (hasNumericDigit.results().count() < TOTAL_NUMERIC_VALUES) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Please enter numeric digit more or equal to length " + TOTAL_NUMERIC_VALUES);
            return responseDTO;
        }
        responseDTO.setResponseStatus(true);
        return responseDTO;
    }
}
