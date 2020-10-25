/**
 * Avenger object. Used to record the avenger's alias, last name, and frequency mentioned.
 * 
 * @author DeerByte
 * @date Fall 2020 
 */

public class Avenger implements Comparable<Avenger>{
	
	private String heroAlias;
	private String lastName;
	private int freqMentioned;

	/**
	 * Class Constructor. Instantiates an Avenger object of the specified alias and lastName, with a frequency mentioned of 0.
	 * @param alias - String representation of the Avenger alias.
	 * @param lastName - String representation of the Avenger last name.
	 */
	public Avenger(String alias, String lastName) {
		heroAlias = alias;
		this.lastName = lastName;
		freqMentioned = 0;
	}
	
	/**
	 * Class Constructor. Instantiates an Avenger object of the specified alias, lastName, and frequency mentioned (freqMentioned). 
	 * Used to keep track of times an Avenger is mentioned.
	 * @param alias - String representing the Avenger alias.
	 * @param lastName - String representing the Avenger last name.
	 * @param freqMentioned - int representing the amount of times an Avenger is mentioned.
	 */
	public Avenger(String alias, String lastName, int freqMentioned) {
		heroAlias = alias;
		this.lastName = lastName;
		this.freqMentioned = freqMentioned;
	}
	
	/**
	 * Sets the heroAlias for the Avenger.
	 * @param alias
	 */
	public void setAlias(String alias) {
		heroAlias = alias;
	}
	
	/**
	 * Sets the lastName for the Avenger.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Sets the frequency mentioned for the Avenger.
	 * @param frequency
	 */
	public void setFreqMentioned(int frequency) {
		freqMentioned = frequency;
	}
	
	/**
	 * Returns the heroAlias of the Avenger.
	 * @return heroAlias
	 */
	public String getAlias() {
		return heroAlias;
	}
	
	/**
	 * Returns the Avenger's last name.
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns the number of times the Avenger was mentioned.
	 * @return
	 */
	public int getFreqMentioned() {
		return freqMentioned;
	}
	
	/**
	 * Increments the frequency mentioned by 1.
	 */
	public void mentioned() {
		freqMentioned++;
	}
	
	/**
	 * Comparable implementation. Used to order Avenger objects by increasing alphabetic order of heroAlias.
	 */
	public int compareTo(Avenger other) {
		return heroAlias.compareTo(other.getAlias());
	}
	
	/**
	 * Returns a String representation of the Avenger object. Overrides Object.toString().
	 */
	@Override
	public String toString() {
		return heroAlias + " aka " + lastName + " mentioned " + freqMentioned + " time(s)";
	}
	
	/**
	 * Overrides Object.equals(Object o). 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
			
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Avenger other = (Avenger) obj;
		
		if (heroAlias == null) {
			if (other.heroAlias != null) {
				return false;
			}
			
		} else if (!heroAlias.equals(other.heroAlias)) {
			return false;
		}
		
		return true;
	}
}
