/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description A class for utility functions
*/
package pcmgen;

import java.util.*;

public class Utilities {

	public static char[] getUniqueElements(char[] ch)
	{
		Set<Character> setUniqueNumbers = new LinkedHashSet<Character>();
		for(char x : ch) {
		    setUniqueNumbers.add(x);
		}
		char[] och = new char[setUniqueNumbers.size()];
		int i=0;
		for(Character x : setUniqueNumbers) {
			och[i]=x.charValue();
			i++;
		}
		return och;
	}
	public static long[] getUniqueElements(long[] ch)
	{
		Set<Long> setUniqueNumbers = new LinkedHashSet<Long>();
		for(long x : ch) {
		    setUniqueNumbers.add(x);
		}
		long[] och = new long[setUniqueNumbers.size()];
		int i=0;
		for(Long x : setUniqueNumbers) {
			och[i]=x.longValue();
			i++;
		}
		return och;
	}
}
