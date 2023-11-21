package com.example.virtualdatabase.controllers;

import com.example.virtualdatabase.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class DataController {

    private final DataService dataService;

    @GetMapping("/api/table/{tableId}/data")
    public List<?> getRecords(@PathVariable Long tableId) {
        return dataService.getAllRecords(tableId);
    }


    @PostMapping("/api/table/{tableId}/data")
    public List<?> insertRecords(@PathVariable Long tableId, @RequestBody List<Map<String, String>> records) {
        return dataService.insertRecords(tableId, records);
    }

}
