import java.util.Comparator;
/**
 * This comparator sorts Avenger objects by decreasing frequency mentioned (freqMentioned).
 * If object a1 and a2 have the same frequency mentioned, the objects are sorted by ascending alphabetic order.
 * @author DeerByte
 *
 */
public class CompareFrequency implements Comparator<Avenger> {

	@Override
	public int compare(Avenger a1, Avenger a2) {
		int frequency = a2.getFreqMentioned() - a1.getFreqMentioned();
		
		if (frequency != 0) {
			return frequency;
			
		} else {
			return a1.getAlias().compareTo(a2.getAlias());
		}
	}

}
