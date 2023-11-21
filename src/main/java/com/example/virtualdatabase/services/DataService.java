package com.example.virtualdatabase.services;

import com.example.virtualdatabase.dto.DataRecord;
import com.example.virtualdatabase.dto.DataRecordOperation;
import com.example.virtualdatabase.models.VirtualColumn;
import com.example.virtualdatabase.models.VirtualRecord;
import com.example.virtualdatabase.models.VirtualValue;
import com.example.virtualdatabase.repositories.VirtualTableRepository;
import com.example.virtualdatabase.validators.DataTypeValidator;
import com.example.virtualdatabase.validators.RequiredFieldValidator;
import com.example.virtualdatabase.validators.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DataService {

    private final VirtualTableRepository virtualTableRepository;

    public List<DataRecord> getAllRecords(Long tableId) {
        var table = virtualTableRepository.findById(tableId).orElseThrow();
        return table.getRecords().stream().map(DataRecord::new).toList();
    }

    public List<DataRecordOperation> insertRecords(Long tableId, List<Map<String, String>> records) {
        final var table = virtualTableRepository.findById(tableId).orElseThrow();

        var operations = records.stream().map(DataRecordOperation::new).toList();

        var insertValidators = Validator.link(
                new RequiredFieldValidator(table),
                new DataTypeValidator(table)
        );

        var valid = insertValidators.check(operations);

        if (valid) {
            var columnsMap = table.getColumns().stream().collect(Collectors.toMap(VirtualColumn::getName, e -> e));

            for (var inputRecord : records) {
                var record = new VirtualRecord();
                inputRecord.forEach((columnName, value) -> {
                    var column = columnsMap.get(columnName);
                    var virtualValue = VirtualValue.builder()
                            .record(record)
                            .column(column)
                            .value(value)
                            .build();
                    record.addValue(virtualValue);
                });
                table.addRecord(record);
            }

            virtualTableRepository.saveAndFlush(table);
        }

        return operations;
    }

}
