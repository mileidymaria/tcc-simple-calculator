package dev.sevora.simplecalculator.domain.factory;

import dev.sevora.simplecalculator.domain.NumberFormatter;
import dev.sevora.simplecalculator.domain.operation.*;

import java.util.Map;

public class OperationFactory {
    private static OperationFactory soleInstance;

    private OperationFactory(){
    }

    public static OperationFactory getSoleInstance(){
        if(soleInstance == null){
            soleInstance = new OperationFactory();
        }
        return soleInstance;
    }

    private static final Map<String, Operation> operationMap = Map.of(
            "+", new Addition(),
            "-", new Subtraction(),
            "x", new Multiplication(),
            "÷", new Division()
    );

    public Operation getOperation(String operator) {
        Operation operation = operationMap.get(operator);
        if (operation == null) {
            throw new IllegalArgumentException("Operação desconhecida: " + operator);
        }
        return operation;
    }
}