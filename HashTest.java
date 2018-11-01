import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * The driver class for program and tests the output of the Hash Table
 * @author Dominik Huffield
 *
 */
public class HashTest {

	private static int tableSize;
	private static double loadFactor;
	private static HashTable linearHashTable;
	private static HashTable doubleHashTable;
	private static HashObject object;
	private static int inputType;
	private static int debugLevel = 0;
	private static Integer intKey;
	private static long longKey;
	private static Random rand;
	private static int keyQuant = 0;
	private static Scanner scan;
	private static Scanner scanDoub;
	private static int intervalStart = 95500;
	private static int intervalEnd = 96000;
	private static int linTotalDupl = 0;
	private static double linAvgProbes = 0;
	private static int doubTotalDupl = 0;
	private static double doubAvgProbes = 0;
	private static int inputQuant = 0;
	
	public static void main(String[] args) {
		if(args.length > 3 || args.length < 2) {
			printUsage();
		} 
		
		try {
			inputType = Integer.parseInt(args[0]);
			if(inputType != 1) {
				if(inputType != 2) {
					if(inputType != 3) {
						printUsage();
					}
				}
			}
			loadFactor = Double.parseDouble(args[1]);
			if(loadFactor < 0 || loadFactor > 1) {
				printUsage();
			}
			if(args.length == 3) {
				debugLevel = Integer.parseInt(args[2]);
				if(debugLevel != 0) {
					if(debugLevel != 1) {
						printUsage();
					}
				}
			}
		} catch(NullPointerException n) {
			
		} catch(NumberFormatException f) {
			
		}
		
		
		
		TwinPrimer primer = new TwinPrimer(intervalStart, intervalEnd);
		tableSize = primer.getPrimePair();
		if (tableSize == -1) {
			System.out.println("There were no twin primes over the given interval");
		}
		
		
		
		
		if (inputType == 1) {
			intObject();
		} else if (inputType == 2) {
			longObject();
		} else if (inputType == 3) {
			stringObject();
		}
		
		double linTProbes = linearHashTable.getTotalLinProbes();
		linAvgProbes = linTProbes/tableSize;
		linTotalDupl = linearHashTable.getTotalDuplicates();
		
		double doubTProbes = doubleHashTable.getTotalDoubProbes();
		double doubAvgprobes = doubTProbes/tableSize;
		doubAvgProbes = doubAvgprobes;
		int doubTotaldupl = doubleHashTable.getTotalDuplicates();
		doubTotalDupl = doubTotaldupl;
		int inputquant = doubleHashTable.getTotalKeys() + doubleHashTable.getTotalDuplicates();
		inputQuant =inputquant;
		
		if (doubTotalDupl == doubTotaldupl) {
			System.out.println("Dupl count was corret");
		}
		
		
		if (debugLevel == 0) {
			printSumm();
		} else if(debugLevel == 1) {
			doubleDump();
		}
	}
	
	/**
	 * Inserts objects into the hash table using an integer as the key value(hashTable[index] != null)
	 */
	private static void intObject() {
		rand = new Random();
		linearHashTable = new HashTable<>(tableSize, "linear");
		doubleHashTable = new HashTable<>(tableSize, "double");
		while(!loaded(linearHashTable)) {
			intKey = rand.nextInt(tableSize);
			object = new HashObject(intKey);
			linearHashTable.insert(object);
			doubleHashTable.insert(object);
			keyQuant++;
		}
	}
	
	/**
	 * Inserts objects into the hash table using a long as the key value 
	 */
	private static void longObject() {
		linearHashTable = new HashTable<>(tableSize, "linear");
		doubleHashTable = new HashTable<>(tableSize, "double");
		while(!loaded(linearHashTable)) {
			longKey = System.currentTimeMillis();
			object = new HashObject<>(longKey);
			linearHashTable.insert(object);
			doubleHashTable.insert(object);
			keyQuant++;
		}
	}
	
	/**
	 *  Inserts objects into the hash trand = new Random();able using a string as the key value
	 */
	private static void stringObject() {
		linearHashTable = new HashTable<>(tableSize, "linear");
		doubleHashTable = new HashTable<>(tableSize, "double");
		String word = "";
		File file = new File("word-list");
		try {
			scan = new Scanner(file);
			scanDoub = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(!loaded(linearHashTable)) {
		word = scan.nextLine();
			linearHashTable.insert(word);
		}
		while(!loaded(doubleHashTable)) {
			word = scanDoub.nextLine();
			doubleHashTable.insert(word);
		}	
		scan.close();	
			if (doubTotalDupl == doubleHashTable.getTotalDuplicates()) {
				keyQuant++;
			} else {
				doubTotalDupl++;
			}
		
	}
	/** Returns the value of the load factor (alpha)
	 * @return
	 */
	private static boolean loaded(HashTable table) {
		int quant = table.getTotalKeys();
		boolean full = quant >= loadFactor*tableSize;
		return full;
	}
	/**
	 * Prints the usage for the program
	 */
	private static void printUsage() {
		System.out.println("use better");
		
	}
	/**
	 * Prints a basic summary for the program, when the debug level is 0 or unspecified
	 */
	private static void printSumm() {
		System.out.println("A good table size is found: " + tableSize);
		System.out.println("Data source type: " +inputType);
		System.out.println();
		System.out.println();
		System.out.println("Using Linear Hashing....");
		System.out.println("Input " + keyQuant + " elements, of which " + linTotalDupl + " duplicates");
		System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + linAvgProbes);
		
		System.out.println();
		System.out.println("Using Double Hashing....");
		System.out.println("Input " + inputQuant + " elements, of which " + doubTotalDupl + " duplicates");
		System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + doubAvgProbes);
		
		System.out.println(doubleHashTable.vstr());
	}
	
	private static void doubleDump() {
		try {
			
			
			System.out.println("Here we go ");
			File doubD = new File("double-dump");
            doubD.createNewFile();
            FileWriter doubWriter = new FileWriter(doubD);
            doubWriter.write(doubleHashTable.toString());
            doubWriter.close();
            System.out.println("Success");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
