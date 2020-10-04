package me.ranzeplay.simucalc.calculate;

import me.ranzeplay.simucalc.InternalInstance;
import me.ranzeplay.simucalc.calculate.grade.LevelOne;
import me.ranzeplay.simucalc.models.Term;

public class CalcManager {
    public static void Do() {
        while (InternalInstance.Terms.size() > 1) {
            for (int i = 0; i < InternalInstance.Terms.size() - 1; i++) {
                Term a = InternalInstance.Terms.get(i), b = InternalInstance.Terms.get(i + 1);
                Term result = LevelOne.Add(a, b);

                InternalInstance.Terms.remove(a);
                InternalInstance.Terms.remove(b);
                InternalInstance.Terms.add(i, result);

                break;
            }
        }
    }
}
