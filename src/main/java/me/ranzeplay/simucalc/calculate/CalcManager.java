package me.ranzeplay.simucalc.calculate;

import me.ranzeplay.simucalc.Constants;
import me.ranzeplay.simucalc.InternalInstance;
import me.ranzeplay.simucalc.calculate.level.LevelOne;
import me.ranzeplay.simucalc.calculate.level.LevelTwo;
import me.ranzeplay.simucalc.models.Term;

public class CalcManager {
	public static void Do() {
		int currentLevel = 2;
		while (InternalInstance.Terms.size() > 1) {
			if (IsAvailableToDowngrade(currentLevel)) currentLevel--;
			for (int i = 0; i < InternalInstance.Terms.size() - 1; i++) {
				Term a = InternalInstance.Terms.get(i), b = InternalInstance.Terms.get(i + 1);

				Term result;
				if (Constants.Operators.get(b.getOperator()) == currentLevel) {
					InternalInstance.Terms.remove(a);
					InternalInstance.Terms.remove(b);
					switch (currentLevel) {
						case 2:
							result = LevelTwo.Controller(a, b);
							InternalInstance.Terms.add(i, result);
							i = -1;
							break;
						case 1:
							result = LevelOne.Controller(a, b);
							InternalInstance.Terms.add(i, result);
							i = -1;
							break;
						default:
							break;
					}
				}
			}
		}
	}

	private static boolean IsAvailableToDowngrade(int currentLevel) {
		for (var term : InternalInstance.Terms) {
			if (Constants.Operators.get(term.getOperator()) >= currentLevel) {
				return false;
			}
		}

		return true;
	}
}
