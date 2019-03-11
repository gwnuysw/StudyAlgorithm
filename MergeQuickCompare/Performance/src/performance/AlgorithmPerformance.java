package performance;

import java.util.Random;
import java.util.Scanner;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class AlgorithmPerformance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestTwoAlgorithmWithSameThreshold t = new TestTwoAlgorithmWithSameThreshold();
		Scanner sc = new Scanner(System.in);
		String endFlag;
		do {
			t.ThresholdCompare();
			System.out.println("Program continue if you enter 'y', the athers will terminate program");
			endFlag = sc.next();
		}while(endFlag.equals("y"));
	}
}