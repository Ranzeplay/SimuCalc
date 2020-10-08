package me.ranzeplay.simucalc;

import me.ranzeplay.simucalc.calculate.CalcManager;
import me.ranzeplay.simucalc.utils.Equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Equation: ");
        // Read equation from keyboard
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        InternalInstance.Equation = bufferedReader.readLine();

        // System.out.println("Equation: " + InternalInstance.Equation);

        Equation.SplitEquation();

        CalcManager.Do();

        System.out.println(InternalInstance.Terms.get(0).toString());
    }
}
