package com.example.virtualdatabase.models;

import com.example.virtualdatabase.dto.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "virtual_column")
@Table(name = "virtual_column")
public class VirtualColumn {

    @Id
    @SequenceGenerator(name = "virtual_column_id_seq",
            sequenceName = "virtual_column_id_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "virtual_column_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private VirtualTable table;

    @Column(name = "name")
    private String name;

    @Column(name = "data_type")
    @Enumerated(EnumType.STRING)
    private DataType dataType;

    @Column(name = "primary_key_ind")
    private Boolean primaryKey;

    @Column(name = "mandatory_key_ind")
    private Boolean mandatory;

}
