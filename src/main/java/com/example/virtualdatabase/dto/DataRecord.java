package com.example.virtualdatabase.dto;

import com.example.virtualdatabase.models.VirtualRecord;
import com.example.virtualdatabase.models.VirtualValue;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class DataRecord {
    private Long id;
    private Map<String, String> values;

    public DataRecord(VirtualRecord record) {
        this.id = record.getId();
        this.values = record.getValues().stream().collect(Collectors.toMap(e -> e.getColumn().getName(), VirtualValue::getValue));
    }

}
