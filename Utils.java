import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Utils {

	public static double calculateAverage(List<Integer> intList) {
		Integer sum = 0;
		if (!intList.isEmpty()) {
			for (Integer mark : intList) {
				sum += mark;
			}
			return sum.doubleValue() / intList.size();
		}
		return sum;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
