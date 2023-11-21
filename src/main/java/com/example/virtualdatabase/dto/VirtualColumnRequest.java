package com.example.virtualdatabase.dto;

public record VirtualColumnRequest(String name, DataType dataType, Boolean primaryKey, Boolean mandatory) {
}
