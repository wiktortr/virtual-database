package com.example.virtualdatabase.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Entity(name = "virtual_table")
@Data
public class VirtualTable {

    @Id
    @SequenceGenerator(name = "virtual_table_id_seq",
            sequenceName = "virtual_table_id_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "virtual_table_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "version_num")
    private Long version;

    @OneToMany(mappedBy = "table",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<VirtualColumn> columns;

    @OneToMany(mappedBy = "table",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<VirtualRecord> records;

    public void addColumn(VirtualColumn column) {
        Optional.ofNullable(column).ifPresent(col -> {
            col.setTable(this);
            columns.add(col);
        });
    }

    public void addRecord(VirtualRecord record) {
        Optional.ofNullable(record).ifPresent(val -> {
            val.setTable(this);
            records.add(val);
        });
    }

}
