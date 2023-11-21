package com.example.virtualdatabase.models;

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
@Entity(name = "virtual_value")
@Table(name = "virtual_value")
public class VirtualValue {

    @Id
    @SequenceGenerator(name = "virtual_value_id_seq",
            sequenceName = "virtual_value_id_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "virtual_value_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private VirtualRecord record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private VirtualColumn column;

    @Column(name = "value")
    private String value;

}
