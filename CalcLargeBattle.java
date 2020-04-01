import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalcLargeBattle {
	private static final int ATTACKER_INITIAL_ARMIES = 40;
	private static final int DEFENDER_INITIAL_ARMIES = 40;
	private static final int STOP_ATTACKING_AT = 3;
	private static final int TRIALS = 10000000;

	public static void main(String[] args) {
		List<Battle> battleTrials = new ArrayList<>();
		for (int trial = 1; trial <= TRIALS; trial++) {
			battleTrials.add(new Battle(ATTACKER_INITIAL_ARMIES, DEFENDER_INITIAL_ARMIES).run(STOP_ATTACKING_AT));
		}
		final List<Integer> attackerLosses = battleTrials.stream().map(Battle::getAttackerLosses)
				.collect(Collectors.toList());
		final List<Integer> defenderLosses = battleTrials.stream().map(Battle::getDefenderLosses)
				.collect(Collectors.toList());
		final double averageAttackerLosses = Utils.calculateAverage(attackerLosses);
		final double averageDefenderLosses = Utils.calculateAverage(defenderLosses);
		displayResult(averageAttackerLosses, averageDefenderLosses);
	}

	private static void displayResult(double averageAttackerLosses, double averageDefenderLosses) {
		System.out.println("Avg Attacker Losses: " + averageAttackerLosses);
		System.out.println("Avg Defender Losses: " + averageDefenderLosses);
	}

}
