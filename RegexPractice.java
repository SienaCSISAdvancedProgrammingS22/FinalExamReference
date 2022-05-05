import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
   Testbed for regex practice, adapted from Lab 9.
   
   @author Jim Teresco
   @version Spring 2020
*/

public class RegexPractice {

    public static void main(String[] args) {

	if (args.length != 2) {
	    System.err.println("Usage: java RegexPractice regex text");
	    System.exit(1);
	}
	
        Pattern p = Pattern.compile(args[0]);
        Matcher m = p.matcher(args[1]);
        while (m.find()) {
	    System.out.println(m.group());
        }
    }
}
