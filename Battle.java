
@SuppressWarnings("unused")
public class Battle {

	private static final double ODDS_3v2_Win2 = 2611d / 7776d;// 37.17%
	private static final double ODDS_3v2_Split = 2611d / 7776d;// 33.58%
	private static final double ODDS_3v2_Lose2 = 2275d / 7776d;// 29.26%

	private static final double ODDS_3v1_Win = 855d / 1296d;// 65.97%
	private static final double ODDS_3v1_Lose = 441d / 1296d;// 34.03%

	private static final double ODDS_2v1_Win = 125d / 216d;// 57.87%
	private static final double ODDS_2v1_Lose = 91d / 216d;// 42.13%

	private static final double ODDS_2v2_Win2 = 295d / 1296d;// 22.76%
	private static final double ODDS_2v2_Split = 420d / 1296d;// 32.41%
	private static final double ODDS_2v2_Lose2 = 581d / 1296d;// 44.83%

	private static final double ODDS_1v2_Win = 55d / 216d;// 25.46%
	private static final double ODDS_1v2_Lose = 161d / 216d;// 74.54%

	private static final double ODDS_1v1_Win = 15d / 36d;// 41.67%
	private static final double ODDS_1v1_Loss = 21d / 36d;// 58.33%

	private int initialAttackerArmies;
	private int initialDefenderArmies;
	private int finalAttackerArmies;
	private int finalDefenderArmies;

	public Battle(int initialAttackerArmies, int initialDefenderArmies) {
		this.initialAttackerArmies = initialAttackerArmies;
		this.initialDefenderArmies = initialDefenderArmies;
	}

	public Battle run(int stopAttackingAt) {
		int attackerArmies = initialAttackerArmies;
		int defenderArmies = initialDefenderArmies;
		while (defenderArmies > 0 && attackerArmies > stopAttackingAt) {
			int attackerDice = attackerArmies > 3 ? 3 : attackerArmies == 3 ? 2 : 1;
			int defenderDice = defenderArmies == 1 ? 1 : 2;

			int[] rollResult = diceRoll(attackerDice, defenderDice);
			attackerArmies -= rollResult[0];
			defenderArmies -= rollResult[1];
		}
		finalAttackerArmies = attackerArmies;
		finalDefenderArmies = defenderArmies;
		return this;
	}

	// Returns [attacker losses, defender losses]
	private int[] diceRoll(int attackerDice, int defenderDice) {
		if (attackerDice == 3 && defenderDice == 2)
			return diceRoll3v2();
		else if (attackerDice == 3 && defenderDice == 1)
			return diceRoll3v1();
		else if (attackerDice == 2 && defenderDice == 2)
			return diceRoll2v2();
		else if (attackerDice == 2 && defenderDice == 1)
			return diceRoll2v1();
		else if (attackerDice == 1 && defenderDice == 2)
			return diceRoll1v2();
		else if (attackerDice == 1 && defenderDice == 1)
			return diceRoll1v1();
		throw new RuntimeException("Unknown number of dice: " + attackerDice + "," + defenderDice);
	}

	private static int[] diceRoll3v1() {
		double rand = Math.random();
		if (rand < ODDS_3v1_Win) {
			return new int[] { 0, 1 };
		}
		return new int[] { 1, 0 };
	}

	private static int[] diceRoll3v2() {
		double rand = Math.random();
		if (rand < ODDS_3v2_Win2) {
			return new int[] { 0, 2 };
		} else if (rand < ODDS_3v2_Win2 + ODDS_3v2_Split) {
			return new int[] { 1, 1 };
		}
		return new int[] { 2, 0 };
	}

	private static int[] diceRoll2v2() {
		double rand = Math.random();
		if (rand < ODDS_2v2_Win2) {
			return new int[] { 0, 2 };
		} else if (rand < ODDS_2v2_Win2 + ODDS_2v2_Split) {
			return new int[] { 1, 1 };
		}
		return new int[] { 2, 0 };
	}

	private static int[] diceRoll2v1() {
		double rand = Math.random();
		if (rand < ODDS_2v1_Win) {
			return new int[] { 0, 1 };
		}
		return new int[] { 1, 0 };
	}

	private static int[] diceRoll1v2() {
		double rand = Math.random();
		if (rand < ODDS_1v2_Win) {
			return new int[] { 0, 1 };
		}
		return new int[] { 1, 0 };
	}

	private static int[] diceRoll1v1() {
		double rand = Math.random();
		if (rand < ODDS_1v1_Win) {
			return new int[] { 0, 1 };
		}
		return new int[] { 1, 0 };
	}

	public int getAttackerLosses() {
		return initialAttackerArmies - finalAttackerArmies;
	}

	public int getDefenderLosses() {
		return initialDefenderArmies - finalDefenderArmies;
	}

	public int getInitialAttackerArmies() {
		return initialAttackerArmies;
	}

	public void setInitialAttackerArmies(int initialAttackerArmies) {
		this.initialAttackerArmies = initialAttackerArmies;
	}

	public int getInitialDefenderArmies() {
		return initialDefenderArmies;
	}

	public void setInitialDefenderArmies(int initialDefenderArmies) {
		this.initialDefenderArmies = initialDefenderArmies;
	}

	public int getFinalAttackerArmies() {
		return finalAttackerArmies;
	}

	public void setFinalAttackerArmies(int finalAttackerArmies) {
		this.finalAttackerArmies = finalAttackerArmies;
	}

	public int getFinalDefenderArmies() {
		return finalDefenderArmies;
	}

	public void setFinalDefenderArmies(int finalDefenderArmies) {
		this.finalDefenderArmies = finalDefenderArmies;
	}
}
