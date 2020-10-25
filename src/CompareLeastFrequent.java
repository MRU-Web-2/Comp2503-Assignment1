import java.util.Comparator;
/**
 * Compares Avenger objects by ascending value of frequency mentioned. 
 * If both objects are mentioned the same number of times, they will be compared by ascending order of lastName.length().
 * If both objects have the same length, they will be sorted by ascending alphabetic order of their lastName.
 * @author DeerByte
 *
 */
public class CompareLeastFrequent implements Comparator<Avenger> {
	
	@Override
	public int compare(Avenger a1, Avenger a2) {
		int frequency = a1.getFreqMentioned() - a2.getFreqMentioned();
		int lastNameSize = a1.getLastName().length() - a2.getLastName().length();
		
		if (frequency != 0) {
			return frequency;
			
		} else if (lastNameSize != 0){
			return lastNameSize;
			
		} else {
			return a1.getLastName().compareTo(a2.getLastName());
		}
	}

}
