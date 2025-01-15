package com.junit.firstjunit;

public class MathUtils {

    public int add(int a, int b){
        return a+b;
    }


    public int subtract(int a, int b) {
        return a - b;
    }

    // Method to multiply two numbers
    public int multiply(int a, int b) {
        return a * b;
    }

    // Method to divide two numbers
    public int divide(int a, int b) {
        return a / b;
    }

    public double computeCircleArea(double radius){
        return Math.PI*radius*radius;
    }


}
