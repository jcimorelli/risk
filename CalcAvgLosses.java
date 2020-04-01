import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalcAvgLosses {
	private static final int ATTACKER_INITIAL_ARMIES = 10000;
	private static final int TRIALS = 10000000;

	public static void main(String[] args) {
		for (int initialDefenderArmies = 1; initialDefenderArmies <= 20; initialDefenderArmies++) {
			double avgLosses = calculateAverageLossesForAttacker(ATTACKER_INITIAL_ARMIES, initialDefenderArmies);
			double pctOfInitial = (avgLosses / Double.valueOf(initialDefenderArmies)) * 100d;
			displayResult(initialDefenderArmies, avgLosses, pctOfInitial);
		}
	}

	private static double calculateAverageLossesForAttacker(int initialAttackerArmies, int initialDefenderArmies) {
		final List<Battle> battleTrials = new ArrayList<>();
		for (int trial = 1; trial <= TRIALS; trial++) {
			battleTrials.add(new Battle(initialAttackerArmies, initialDefenderArmies).run(1));
		}
		final List<Integer> attackerLosses = battleTrials.stream().map(Battle::getAttackerLosses)
				.collect(Collectors.toList());
		return Utils.calculateAverage(attackerLosses);
	}

	private static void displayResult(int initialDefenderArmies, double avgLosses, double pctOfInitial) {
		StringBuilder sb = new StringBuilder();
		sb.append(initialDefenderArmies).append(" Def Army -> ");
		sb.append(Utils.round(avgLosses, 4)).append(" avg loss -> ");
		sb.append(Utils.round(pctOfInitial, 2)).append("%");
		System.out.println(sb.toString());
	}

}
