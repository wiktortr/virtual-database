package com.example.virtualdatabase.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class DataRecordOperation {

    private Map<String, String> values;

    private List<String> validationMessages;

    public DataRecordOperation(Map<String, String> values) {
        this.values = values;
        validationMessages = new ArrayList<>();
    }

    public void addValidationMessage(String msg) {
        validationMessages.add(msg);
    }

}
