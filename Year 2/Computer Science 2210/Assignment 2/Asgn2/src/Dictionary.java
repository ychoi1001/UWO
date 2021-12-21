/***
 * This class implements a dictionary using a hash table in which collisions are resolved
 * using separate chaining.
 * @author Yeonsil Choi (251126837)
 *
 */
public class Dictionary implements DictionaryADT {
	
	/***
	 * 
	 * A class Node storing an object of the class Data to construct the linked lists
	 * associated to the entries of the hash table
	 *
	 */
	private class Node {
		//String key;
		Data data;
		Node next;
		
	}
	
	private Node[] table;  
	private int count;
	
	
	/***
	 * Method for getting a hash code for a key
	 * @param key
	 * @return hashValue
	 */
	private int getHashCode(String key) {
		int hashValue = 0;
		int x = 33;
		// a polynomial hash function
		for (int i = key.length()-1; i>=0; i--) {
			//System.out.println((int)(key.charAt(i)));
			hashValue = Math.abs(hashValue*x + (int)(key.charAt(i))); // to prevent negative values
		}
		return hashValue;
		
	}
	
	/***
	 * 
	 * @param hashcode
	 * @return hashcode % table.length which is the index of the hash table
	 */
	private int convertToIndex(int hashcode) {
		return hashcode % table.length;
	}


	/***
	 * initializes a dictionary with an empty hash table of the specified size
	 * @param size
	 */
	public Dictionary(int size) {
		//Hashtable<String, Data> hash_table = new Hashtable<>(size);
		this.table = new Node[size];
	}

	/***
	 * Inserts the given Data object referenced by record in the dictionary.
	 * This method must throw a DuplicatedKeyException if the string stored in the object
	 * referenced by record is already in the dictionary.
	 */
	@Override
	public int put(Data record) throws DuplicatedKeyException {
		
		//to get a hash code
		int hashcode = getHashCode(record.getKey());
		//invert a hash code to an index
		int index = convertToIndex(hashcode);
		//to traversing the linked list at table[index]
		Node list = table[index];
		
		//System.out.println(table[index] != null);
		
		//to check if there is a duplicated key in the list
		while (list != null) {
			if (list.data.getKey().equals(record.getKey())) {
				throw new DuplicatedKeyException("DuplicatedKeyException");
			}
			list = list.next;
		}
		
		//to update table[index]
		if (list != null) {
			list.data = record;
		} else {
			Node newNode = new Node(); 
			newNode.data = record; 
			newNode.next = table[index]; 
			table[index] = newNode;
			count++;
		}
		
		//to count nodes to determine if there is a collision or not
		Node temp = table[index];
		int counting = 0;
		while (temp != null) {
			counting++;
			temp = temp.next;
		}
		
		//returns the value 1 if the list in entry table[index] already stores
		//at least one element
		//returns 0 if that list was empty before the insertion
		if (counting == 1) {
			return 0;
		} else if (counting > 1) {
			return 1;
		}
		
		return -1;
    }

	/***
	 * removes the Data object containing string key from the dictionary
	 * throws a InexistentKeyException if the hash table does not store
	 * any Data object with the given key value
	 */
	@Override
	public void remove(String key) throws InexistentKeyException {
		//to get a hash code
		int hashcode = getHashCode(key);
		//invert a hash code to an index
		int index = convertToIndex(hashcode);
		
		//Node currentNode = table[index];
		
		//throws a InexistentKeyException if the hash table does not store
		//any Data object with the given key value
		if (table[index] == null) {
			throw new InexistentKeyException("InexistentkeyException");
		}
		
		//case 1) When the key is matched to the data which is stored 
		//in the first node on the list
		if (table[index].data.getKey().equals(key)) {
			table[index] = table[index].next;
			count--;
			return;
		}
		
		//case 2) When the key is matched to the data which is stored somewhere 
		//in the middle on the list
		Node head = table[index];
		Node current = head.next;
		Node temp = null;
		
		//for updating current position 
		while (head != null && ! current.data.getKey().equals(key)) {
			temp = current;
			head = temp;
			current = current.next;
			//System.out.println(table[index].data.getKey());
			//System.out.println(table[index].next.data.getKey());
			//System.out.println(table[index].next.next.data.getKey());
			}
		
		//to update hash table for deleting a data for the given key
		if (current != null) {
			head.next = current.next;
			count--; 
			//System.out.println(head.data.getKey());
			//System.out.println(table[index].data.getKey());
			}
		
		
	}

	/***
	 * A method which returns the Data object stored in the hash table
	 * containing the given key value, or null if no Data object stored in
	 * the hash table contains the given key value
	 */
	@Override
	public Data get(String key) {
		//to get a hash code
		int hashcode = getHashCode(key);
		//invert a hash code to an index
		int index = convertToIndex(hashcode);
		
		//create the node for table[index]
		Node list = table[index];
		
		//check if there is the Data object stored in the hash table
		//containing the given key value
		while (list != null) {
			// If the key is the first node in the hash table, return the data
			if (list.data.getKey().equals(key)) {
				return list.data;
			}
			//if not, update list to check next node
			list = list.next;
		}
		//returns null if there is no Data object for the given key
		return null;
	}

	/***
	 * returns the number of Data objects stored in the hash table
	 */
	@Override
	public int numDataItems() {
		return this.count;
	}
	
	public static void main(String[] args) throws DuplicatedKeyException, InexistentKeyException {
		Dictionary h = new Dictionary(33);
		
		//Dictionary h2 = new Dictionary(2);
		
		Data d1 = new Data("key1",1,1);	
		Data d2 = new Data("key2",2,2);
		Data d3 = new Data("key3",3,3);
		Data d4 = new Data("key4",4,4);
		h.put(d1);
		
		//h.get("key1");
		//System.out.println(h.get("key1").getKey());
		
		h.put(d2);
		h.put(d3);
		h.put(d4);
		
		//h.get("key2");
		//System.out.println(h.get("key2").getKey());
		//Data d5 = new Data("susan101",5,5);
		//h.put(d5);
		//h.remove("key4");
		//h.put(d4);
		
		h.remove("key2");
		

		
	}


}
