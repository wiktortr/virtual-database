package com.example.virtualdatabase.repositories;

import com.example.virtualdatabase.models.VirtualTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualTableRepository extends JpaRepository<VirtualTable, Long> {

}
