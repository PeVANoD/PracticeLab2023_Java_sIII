package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;
import java.io.*;


public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
                double[] xValues = {1.0, 2.0, 3.0, 4.0};
                double[] yValues = {1.0, 4.0, 9.0, 16.0};

                TabulatedDifferentialOperator differentOperator = new TabulatedDifferentialOperator();

                TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
                TabulatedFunction firstDifferentArrayFunction = differentOperator.derive(arrayFunction);
                TabulatedFunction secondDifferentArrayFunction = differentOperator.derive(firstDifferentArrayFunction);

                FunctionsIO.serialize(bufferedOutputStream, arrayFunction);
                FunctionsIO.serialize(bufferedOutputStream, firstDifferentArrayFunction);
                FunctionsIO.serialize(bufferedOutputStream, secondDifferentArrayFunction);
            }
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}