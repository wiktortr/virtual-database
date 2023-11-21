package com.example.virtualdatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "virtual_record")
@Table(name = "virtual_record")
public class VirtualRecord {

    @Id
    @SequenceGenerator(name = "virtual_record_id_seq",
            sequenceName = "virtual_record_id_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "virtual_record_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private VirtualTable table;

    @OneToMany(mappedBy = "record",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<VirtualValue> values = new ArrayList<>();

    public void addValue(VirtualValue val) {
        values.add(val);
    }

}
