package com.company;

public interface MathProcessing {
    String SEPERATOR = " ";
    String getKeyword();
    char getSymbol();
    double doCalculation(double leftVal, double rightVal);
}
