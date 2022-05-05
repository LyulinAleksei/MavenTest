package com.geekbrains.lesson3.lesson4;

public class TriangleArea {
    public static double calculateArea(double sideA, double sideB, double sideC) throws Exception {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new Exception("Некорректный треугольник!!");
        }
        double halfP = (sideA + sideB + sideC) / 2;
        double square = (halfP * (halfP - sideA) * (halfP - sideB) * (halfP - sideC));
        return Math.sqrt(square);
    }
}

