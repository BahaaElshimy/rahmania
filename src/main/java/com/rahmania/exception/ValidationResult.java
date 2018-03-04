package com.rahmania.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bahaa on 26/02/18.
 */
public class ValidationResult {

    private List<FieldErrorDTO> errorList =new ArrayList<>();

    public List<FieldErrorDTO> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<FieldErrorDTO> errorList) {
        this.errorList = errorList;
    }
}
