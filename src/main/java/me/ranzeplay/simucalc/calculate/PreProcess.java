package me.ranzeplay.simucalc.calculate;

import me.ranzeplay.simucalc.Constants;
import me.ranzeplay.simucalc.InternalInstance;
import me.ranzeplay.simucalc.models.Term;

public class PreProcess {
    public static void SplitEquation() {
        InternalInstance.Equation = InternalInstance.Equation.replaceAll(" ", "");

        StringBuilder tempRawTerm = new StringBuilder();

        if (Constants.Operators.get(InternalInstance.Equation.charAt(0)) == null) {
            StringBuilder equation = new StringBuilder(InternalInstance.Equation).insert(0, '+');
            InternalInstance.Equation = equation.toString();
        }

        boolean firstTermFlag = false;
        for (char c : InternalInstance.Equation.toCharArray()) {
            if (Constants.Operators.get(c) != null && firstTermFlag) {
                if (!tempRawTerm.toString().isEmpty()) {
                    InternalInstance.Terms.add(new Term(tempRawTerm.toString()));
                }
                tempRawTerm = new StringBuilder();
            }
            tempRawTerm.append(c);
            firstTermFlag = true;
        }
        InternalInstance.Terms.add(new Term(tempRawTerm.toString()));
    }
}
