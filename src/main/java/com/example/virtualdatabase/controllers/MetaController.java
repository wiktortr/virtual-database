package com.example.virtualdatabase.controllers;

import com.example.virtualdatabase.dto.VirtualColumnRequest;
import com.example.virtualdatabase.models.VirtualColumn;
import com.example.virtualdatabase.models.VirtualTable;
import com.example.virtualdatabase.repositories.VirtualTableRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class MetaController {

    private final VirtualTableRepository virtualTableRepository;

    @GetMapping("/api/table/{tableId}")
    VirtualTable getTable(@PathVariable Long tableId) {
        return virtualTableRepository.findById(tableId).orElseThrow();
    }

    @Transactional
    @PostMapping("/api/table/{tableId}")
    public VirtualTable addColumn(@PathVariable Long tableId, @RequestBody List<VirtualColumnRequest> columns) {
        var table = virtualTableRepository.findById(tableId).orElseThrow();

        columns.stream().map(virtualColumn -> VirtualColumn.builder()
                        .name(virtualColumn.name())
                        .dataType(virtualColumn.dataType())
                        .primaryKey(virtualColumn.primaryKey())
                        .mandatory(virtualColumn.mandatory())
                        .build())
                .forEach(table::addColumn);

        table.setVersion(table.getVersion() + 1);
        virtualTableRepository.saveAndFlush(table);

        return table;
    }

}
