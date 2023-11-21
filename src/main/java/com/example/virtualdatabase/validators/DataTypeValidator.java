package com.example.virtualdatabase.validators;

import com.example.virtualdatabase.dto.DataRecordOperation;
import com.example.virtualdatabase.models.VirtualColumn;
import com.example.virtualdatabase.models.VirtualTable;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
public class DataTypeValidator extends Validator {

    private VirtualTable table;

    @Override
    public boolean check(List<DataRecordOperation> operations) {

        AtomicBoolean valid = new AtomicBoolean(true);

        table.getColumns().stream()
                .filter(VirtualColumn::getMandatory)
                .forEach(column -> {
                    operations.forEach(op -> {
                        if (!validate(column, op)) {
                            valid.set(false);
                        }
                    });
                });

        if (valid.get()) {
            return checkNext(operations);
        } else {
            return false;
        }

    }

    private boolean validate(VirtualColumn column, DataRecordOperation operation) {
        if (!operation.getValues().containsKey(column.getName())) {
            operation.addValidationMessage(String.format("Column %s don't exists", column.getName()));
            return false;
        }
        return true;
    }

}
