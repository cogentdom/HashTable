/**
 * A class used to store the data for each hash object
 * @author Dominik Huffield
 *
 * @param <T>
 */
public class HashObject<T> {

	private T object = null;
	private int duplicates;
	private int probes;
	private int cacheProbes;
	private int storedIndex;
	
	/**
	 * The constructor for the class
	 * @param key
	 */
	public HashObject(T object) {
		this.object = object;
		duplicates = 0;
		probes = 0;
		cacheProbes = 0;
	}
	/**
	 * Returns the key value for the class
	 * @return 
	 */
	public int getKey() {
		return Math.abs(object.hashCode());
	}
	public T getObject() {
		return object;
	}
	public void addIndex(int i) {
		storedIndex = i;
	}
	public int getIndex() {
		return storedIndex;
	}
	/**
	 * Returns the duplicate count 
	 */
	public void incDupl() {
		duplicates++;
		cacheProbes = 0;
	}
	/**
	 * Increases the probe count by one
	 */
	public void incProbes() {
		probes++;
	}
	public void addProbes(int prob) {
		probes += prob;
	}
	/**
	 * Increments the temporary probe count for the object 
	 */
	public void incCacheProbes() {
		cacheProbes++;
	}
	/**object
	 * Adds the temporary probe count (cache probes) to the actual probe 
	 * count and resets the cache count
	 */
	public void cashIn() {
		probes += cacheProbes;
		cacheProbes = 0;
	}
	/**
	 * @return the probes count
	 */
	public int getProbeCount() {
		return probes;
	}
	/**
	 * @return the duplicate count
	 */
	public int getDuplCount() {
		return duplicates;
	}
	/**
	 * Compares the current hash object with the presented object for equivalence 
	 * @param object
	 * @return
	 */
	public boolean equals(HashObject object) {
		
		return this.object.equals(object);	
	}
	public String toString() {
		String str = "";
		if (object != null) {
			str = object + " " + duplicates + " " + probes;
		}
		return str;
	}
}

