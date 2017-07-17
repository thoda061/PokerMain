
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *
 * @author thoda061
 */
public class PokerMain {
	
    private static final String PATTERN ="((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])((([1]?[0-9])|[KQJAkqja])[sdhcSDHC])";
    private static String sep;

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            boolean match = Pattern.matches(PATTERN, input);
            if(match) {
                ArrayList<Card> sepInput = seperate(input);
                sepInput = sort(sepInput);
                if(!(validate(sepInput))) {
                    System.out.println("Invalid: " + input);
                }else{
						combine(sepInput);
					 }
            } else {
                System.out.println("Invalid: " + input);
            }
        }
    }
	
    public static ArrayList<Card> seperate(String input) {
        char seperator;
        int lastSep = 0,numCards = 0;
        ArrayList<Card> hand = new ArrayList();
        if(input.contains("/")) {
                seperator = '/';
                sep = "/";
        } else if (input.contains("-")) {
                seperator = '-';
                sep = "-";
        } else {
                seperator = ' ';
                sep = " ";
        }
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == seperator) {
                hand.add(new Card(input.substring(lastSep, i)));
                numCards++;
                lastSep = i+1;
                if(numCards == 4) {
                    hand.add(new Card(input.substring(lastSep)));
                    numCards++;
                }
            }
        }
        return hand;
    }

    public static ArrayList<Card> sort (ArrayList<Card> hand) {
        int pos;
        int i;
        int key;
        Card cKey;

        for(pos = 1; pos < hand.size(); pos++) {
            cKey = hand.get(pos);
            key = cKey.getValueNum();
            for(i = (pos-1); i >= 0; i--) {
                Card currCard = hand.get(i);
                if(currCard.getValueNum() > key || (currCard.getValueNum() == key && currCard.getSuitVal() > cKey.getSuitVal())) {
                    hand.set(i+1, currCard);
                    hand.set(i, cKey);
                } else {
                    i = -1;
                }
            }
        }
        return hand;
    }
    
    public static boolean validate (ArrayList<Card> hand) {
        for(int i = 0; i < hand.size(); i ++) {
            for(int j = i+1; j < hand.size(); j++) {
                if(hand.get(i).equals(hand.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
        
    public static void combine (ArrayList<Card> hand) {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < hand.size(); i++) {
            output.append(hand.get(i).toString());
            if(i < hand.size() - 1) {
                output.append(sep);
            }
        }
        System.out.println(output.toString());
    }
		
}
