/*
 * Wraps two variables into one type using a Key,Value combination
 * Used for the MinPQ
 * Makes the code efficient
 */
public class Wrapper_DataKey<Data,Key> implements Comparable<Wrapper_DataKey<Data,Key>> {
	
	private Data data;
	private Key key;
	
	public Wrapper_DataKey(Data player, Key athleticism) {
		this.data = player;
		this.key = athleticism;
	}

	public Data getData() {
		return this.data;
	}

	@Override
	public int compareTo(Wrapper_DataKey<Data, Key> o) {
		return ((Integer) this.getKey()).compareTo((Integer) o.getKey());
	}
	
	public Key getKey() {
		return this.key;
	}

}
