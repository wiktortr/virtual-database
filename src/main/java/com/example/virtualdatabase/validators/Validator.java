package com.example.virtualdatabase.validators;

import com.example.virtualdatabase.dto.DataRecordOperation;

import java.util.List;

public abstract class Validator {

    private Validator next;

    public static Validator link(Validator first, Validator... chain) {
        Validator head = first;
        for (Validator nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(List<DataRecordOperation> operation);

    protected boolean checkNext(List<DataRecordOperation> operation) {
        if (next == null) {
            return true;
        }
        return next.check(operation);
    }

}
