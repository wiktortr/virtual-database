package com.example.virtualdatabase.validators;

import com.example.virtualdatabase.dto.DataRecordOperation;
import com.example.virtualdatabase.models.VirtualTable;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UniquenessValidator extends Validator {

    VirtualTable table;

    @Override
    public boolean check(List<DataRecordOperation> operations) {


        return checkNext(operations);
    }

}
