import java.util.Random;
import java.util.Scanner;

/**
 * A class that finds the smallest twin prime values over a presented interval 
 * @author Dominik Huffield
 *
 */
public class TwinPrimer {

	private int primeZero;
	private int primeOne;
	private Random rand;
	/**
	 * Constructor method
	 * @param start the start of the interval
	 * @param end the end of the interval
	 */
	public TwinPrimer(int start, int end) {
		if(start % 2 == 1) {
			start++;
		}
		for(int i =start; i <= end; i=i+2) {
			String binaryNum = baseTwo(i);
			int posPrime = i + 1;
			if(findMod(binaryNum, posPrime) == 1) {
				if(checkMod(binaryNum, posPrime) == 1) {
					String binaryNum2 = baseTwo(i+2);
					if((findMod(binaryNum2, posPrime+2) == 1) && (checkMod(binaryNum2, posPrime+2) == 1)) {
						primeZero = posPrime;
						primeOne = posPrime +2;
						i = end;
					}
				}
			}
		}
	}
	/**
	 * Converts a presented integer into a string that represents its binary value
	 * @param n
	 * @return
	 */
	private String baseTwo(int n) {
		String binaryString = "";
		while(n > 0) {
			int b = n % 2;
			binaryString = b + " " + binaryString;
			n = n/2;
		}
		return binaryString;
	}
	/**
	 * The square and multiply operation for when our binary string has a "1"
	 * @param value
	 * @param base
	 * @param mod
	 * @return
	 */
	private long sqrNMult(long value, long base, int mod) {
		long newVal;
		newVal = value * value;
		newVal = newVal % mod;
		newVal = newVal * base;
		newVal = newVal % mod;
		return newVal;
	}
	/**
	 * The square operation for when our binary string has a value of "0"
	 * @param value
	 * @param mod
	 * @return
	 */
	private long square(long value, int mod) {
		long newVal;
		newVal = value * value;
		newVal = newVal % mod;
		return newVal;
	}
	/**
	 * Finds the value of a randomly generated integer to the power of our 
	 * binary number after modulating by the integer p 
	 * @param binary
	 * @param p
	 * @return
	 */
	private long findMod(String binary, int p) {
		rand = new Random();
		long base = 1 + rand.nextInt(p);
		long v = base;
		Scanner scan = new Scanner(binary);
		scan.next();
		while(scan.hasNext()) {
			int bin = scan.nextInt();
			if(bin == 1) {
				v = sqrNMult(v, base, p);
			} else if(bin == 0) {
				v = square(v, p);
			}
		}
		scan.close();
		return v;
	}
	/**
	 * Finds the value of a randomly generated integer to the power of our 
	 * binary number after modulating by the integer p
	 * @param binary
	 * @param p
	 * @return
	 */
	private long checkMod(String binary, int p) {
		rand = new Random();
		int base = rand.nextInt(p);
		long v = base;
		Scanner scan = new Scanner(binary);
		scan.next();
		while(scan.hasNext()) {
			int bin = scan.nextInt();
			if(bin == 1) {
				v = sqrNMult(v, base, p);
			} else if(bin == 0) {
				v = square(v, p);
			}
		}
		scan.close();
		return v;
	}
	/** 
	 * Returns the larger of the found twin primes, if there are no 
	 * primes over the given interval the it returns -1
	 * @return
	 */
	public int getPrimePair() {
		if (primeZero+2 == primeOne) {
			return primeOne;
		} else {
			return -1;
		}
	}
}
