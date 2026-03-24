package com.mycompany.bai1_lambda;

public class Main {
    public static void main(String[] args) {
        // Lambda cho phép cộng
        MathOperation addition = (a, b) -> a + b;
        
        // Lambda cho phép trừ
        MathOperation subtraction = (a, b) -> a - b;
        
        // Lambda cho phép nhân
        MathOperation multiplication = (a, b) -> a * b;
        
        // Lambda cho phép chia
        MathOperation division = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Không thể chia cho 0");
            }
            return a / b;
        };
        
        // Test các phép tính
        int x = 20, y = 5;
        
        System.out.println("=== Demo Lambda Expression ===");
        System.out.println(x + " + " + y + " = " + addition.compute(x, y));
        System.out.println(x + " - " + y + " = " + subtraction.compute(x, y));
        System.out.println(x + " * " + y + " = " + multiplication.compute(x, y));
        System.out.println(x + " / " + y + " = " + division.compute(x, y));
        
        // Test thêm
        int a = 15, b = 3;
        System.out.println("\n=== Test thêm ===");
        System.out.println(a + " + " + b + " = " + addition.compute(a, b));
        System.out.println(a + " - " + b + " = " + subtraction.compute(a, b));
        System.out.println(a + " * " + b + " = " + multiplication.compute(a, b));
        System.out.println(a + " / " + b + " = " + division.compute(a, b));
        
        // Test chia cho 0
        try {
            System.out.println("\n=== Test chia cho 0 ===");
            System.out.println("10 / 0 = " + division.compute(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}