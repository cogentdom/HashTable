/**
 * A class that builds a hash table using an array filled with HashObjects
 * @author Dominik Huffield
 *
 * @param <T>
 */
public class HashTable<T> {

	private HashObject[] hashTable;
	private int size;
	private String probingType;
	private int duplicates;
	private int linProbes;
	private int doubProbes;
	private int totalKeys;
	private int cacheProbes;
	
	/**
	 * The constructor for the class
	 * @param size
	 * @param probingType
	 */
	public HashTable(int size, String probingType) {
		hashTable = new HashObject[size];
		this.size = size;
		this.probingType = probingType;
		duplicates = 0;
		linProbes = 0;
		doubProbes = 0;
		cacheProbes = 0;
		totalKeys = 0;
	}
	/**
	 * Inserts the hash object into the hash table using the objects probing type
	 * @param key
	 */
	public void insert(T key) {
//		int index = hFuncOne(key);
//		if (hashTable[index] == null) {
//			probes++;
////			key.incProbes();
//			hashTable[index] = key;
//		} else if (key.equals(hashTable[index])) {
//			duplicates++;
//			cacheProbes = 0;
////			key.incDupl();
//		} else {
//			cacheProbes++;
//			key.incCacheProbes();
			if (probingType == "linear") {
				linearColl(key);
			} else if (probingType == "double") {
				doubleColl(key);
			}	
//		}  
	}
	/**
	 * A recursive method that is called when there is a collision using linear probing
	 * @param key
	 * @param index
	 */
	private void linearColl(T key) {
		HashObject<T> object = new HashObject(key);
		int code = object.getKey();
		int index = hFuncOne(code);
		
		for (int i = 0; i < size; i++) {

            int cursor = (index + i) % size;
            linProbes++;
			object.incProbes();
            if (cursor < 0) {
				cursor += size;
			}

            if (hashTable[cursor] == null) {
                hashTable[cursor] = object;
                object.addIndex(cursor);
//                linProbes = i+1;
//                object.addProbes(i+1);
                totalKeys++;
                break;
            } else if (hashTable[cursor].equals(object)) {
                object.incDupl();
                duplicates++;
                break;
            }
        }
	}
	/**
	 * A method called when there is a collision using double hash probing
	 * @param key
	 */
	private void doubleColl(T key) {
		HashObject<T> object = new HashObject(key);
		int code = object.getKey();
		int hOne = hFuncOne(code);
		int hTwo = hFuncTwo(code);
		for(int i = 0; i < size; i++) {
			int cursor = (hOne + (i*hTwo)) % size;
//			doubProbes++;
//			object.incProbes();
			if (cursor < 0) {
				cursor += size;
			}
			
			if (hashTable[cursor] ==  null) {
				hashTable[cursor] = object;
				object.addIndex(cursor);
				doubProbes += (i+1);
                object.addProbes(i+1);
				totalKeys++;
				break;
			} else if(hashTable[cursor].getObject().equals(key)) {
				object.incDupl();
				duplicates++;
				break;
			}
			
		}
	}
	/**
	 * The primary hash function for finding the insert index
	 * @param k
	 * @return
	 */
	private int hFuncOne(int k) {
		int index = k % size;
		if (index < 0) {
			index += size;
		}
		return index;
	}
	/**
	 * The secondary hash function for finding the insert index with double hashing
	 * @param k
	 * @return
	 */
	private int hFuncTwo(int k) {
		int index = 1 + k % (size -2);
		if (index < 0) {
			index += size;
		}
		return index;
	}
	
	/**
	 * Returns the total number of probes
	 * @return
	 */
	public int getTotalLinProbes() {
//		int count = 0;
//		for(int i = 0; i < size; i++) {
//			if(hashTable[i] != null) {
//				count += hashTable[i].getProbeCount();
//			}
//		}
		return linProbes;
	}
	public int getTotalDoubProbes() {
//		int count = 0;
//		for(int i = 0; i < size; i++) {
//			if(hashTable[i] != null) {
//				count += hashTable[i].getProbeCount();
//			}
//		}
		return doubProbes;
	}
	/**
	 * Returns the total number of duplicates
	 * @return
	 */
	public int getTotalDuplicates() {
//		int count = 0;
//		for(int i = 0; i < size; i++) {
//			if(hashTable[i] != null) {
//				count += hashTable[i].getDuplCount();
//			}
//		}
		return duplicates;
		
	}
	public int getTotalKeys() {
		return totalKeys;
	}
	public String toString() {
		String str = "";
		for (int i = 0; i < size; i++) {
			if (hashTable[i] != null) {
				str = str + "Table[" + hashTable[i].getIndex() + "]: " + hashTable[i].toString() + "\n";
			}
		}
		return str;
	}
	public String vstr() {
		String str = hashTable[12] + "  " + hashTable[20] + "  " + hashTable[45];
		return str;
	}
}
