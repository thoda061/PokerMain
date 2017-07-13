
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *
 * @author thoda061
 */
public class PokerMain {
	
	private static final String PATTERN ="((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC])";
	
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String input = sc.nextLine();
			boolean match = Pattern.matches(PATTERN, input);
			if(match) {
				Collection sepInput = seperate(input);
				String output = sort(sepInput);
				System.out.println(output);
			} else {
				System.out.println("Invalid: " + input);
			}
		}
	}
	
	public static Collection seperate(String input) {
		char seperator;
		int lastSep = 0,numCards = 0;
		Collection hand = new ArrayList();
		if(input.contains("/")) {
			seperator = '/';
		} else if (input.contains("-")) {
			seperator = '-';
		} else {
			seperator = ' ';
		}
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == seperator) {
				hand.add(input.substring(lastSep, i));
				numCards++;
				lastSep = i+1;
				if(numCards == 4) {
					hand.add(input.substring(lastSep));
					numCards++;
				}
			}
		}
		return hand;
	}
	
	public static String sort (Collection hand) {
		String min
	}
		
}
