package com.example.virtualdatabase.validators;

import com.example.virtualdatabase.dto.DataRecordOperation;
import com.example.virtualdatabase.dto.DataType;
import com.example.virtualdatabase.models.VirtualColumn;
import com.example.virtualdatabase.models.VirtualTable;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

@AllArgsConstructor
public class RequiredFieldValidator extends Validator {

    static Map<DataType, Pattern> DATA_TYPE_TO_PATTERN = Map.of(
            DataType.String, Pattern.compile(".+"),
            DataType.Number, Pattern.compile("[0-9]+")
    );
    private VirtualTable table;

    @Override
    public boolean check(List<DataRecordOperation> operations) {


        AtomicBoolean valid = new AtomicBoolean(true);

        table.getColumns().stream()
                .filter(VirtualColumn::getMandatory)
                .forEach(column -> {
                    final var dataType = column.getDataType();
                    final var pattern = DATA_TYPE_TO_PATTERN.get(dataType);
                    operations.forEach(op -> {
                        final var value = op.getValues().getOrDefault(column.getName(), "");
                        if (!pattern.matcher(value).matches()) {
                            op.addValidationMessage(String.format("Value dont match data type %s", dataType));
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

}
