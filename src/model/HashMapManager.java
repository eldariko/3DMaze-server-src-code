package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * The Class HashMapManager.
 *
 * @param <Key> the generic type
 * @param <Value> the generic type
 */
public class HashMapManager<Key ,Value> {
	
	/** The hash map. */
	private HashMap<Key, Value> hashMap;
	
	/** The file. */
	private File file;

	/**
	 * Instantiates a new hash map manager.
	 *
	 * @param fileName the file name
	 * @param hashMap the hash map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HashMapManager(String fileName, HashMap<Key, Value> hashMap)throws IOException {
		this.setHashMap(hashMap);
		file = new File(fileName+".txt");
		file.createNewFile();
		
	}

	/**
	 * Save hash map.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveHashMap() throws IOException {
		ObjectOutputStream s = null;
		FileOutputStream f = null;
		GZIPOutputStream gzos = null;
		try {
			f = new FileOutputStream(file);
			gzos = new GZIPOutputStream(f);
			s = new ObjectOutputStream(gzos);
			s.writeObject(getHashMap());
			s.close();
			gzos.close();
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	
			f.close();	
			gzos.close();
			s.close();
				
				
		}

	}

	/**
	 * Load hash map from file.
	 *
	 * @return the hash map
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Key ,Value> loadHashMapFromFile() {
		ObjectInputStream s = null;
		FileInputStream f = null;
		GZIPInputStream gzis = null;
		try {
			f = new FileInputStream(file);
			if (f.available() > 0) {
				gzis = new GZIPInputStream(f);
				s = new ObjectInputStream(gzis);
				hashMap = (HashMap<Key ,Value>) s.readObject();
				s.close();
				f.close();
				gzis.close();
			} else {
				f.close();
				return new HashMap<Key ,Value>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;
	}

	/**
	 * Gets the hash map.
	 *
	 * @return the hash map
	 */
	public HashMap<Key, Value> getHashMap() {
		return hashMap;
	}

	/**
	 * Sets the hash map.
	 *
	 * @param hashMap the hash map
	 */
	public void setHashMap(HashMap<Key, Value> hashMap) {
		this.hashMap = hashMap;
	}


}
