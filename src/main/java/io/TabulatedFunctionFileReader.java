package io;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try {
            try (BufferedReader arrayReader = new BufferedReader(new FileReader("input/function.txt"));
                 BufferedReader linkedListReader = new BufferedReader(new FileReader("input/function.txt"))) {
                TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
                TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(linkedListReader, new LinkedListTabulatedFunctionFactory());

                System.out.println("Array function:");
                System.out.println(arrayFunction);
                System.out.println("Linked list function:");
                System.out.println(linkedListFunction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}