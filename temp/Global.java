import java.lang.Math;
import java.util.HashMap;
import java.util.HashSet;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Global
{
	static final int FACTORS = 10;
	static final char[] SYMBOLS = ("_#").toCharArray();

	static HashMap<String, Owner> AllOwners = new HashMap<>();
	static HashMap<Long, Student> AllStudents = new HashMap<>();
	static HashMap<Integer, Flat> AllFlats = new HashMap<>();
	static HashMap<Integer, Room> AllRooms = new HashMap<>();

	static int random(int min, int max)
	{
		max++;
		return (int)Math.floor(Math.random()*(max-min)+min);
	}

	static String hash(String text) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(hash);
	}

	static String bytesToHex(byte[] bytes)
    {
        final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        
        char[] hexChars = new char[bytes.length*2];
        for(int j=0; j<bytes.length; j++)
        {
            int v = bytes[j]&0xFF;
            hexChars[j*2] = HEX_ARRAY[v>>>4];
            hexChars[j*2+1] = HEX_ARRAY[v&0x0F];
        }

        return new String(hexChars);
    }

	static boolean checkIdentifier(String str, String xtra_sym)
	{
		if(str.length() == 0) return false;

		HashSet<Character> symbols = new HashSet<>();
		for(char c: SYMBOLS) symbols.add(c);

		char[] ch = xtra_sym.toCharArray();
		for(char c: ch) symbols.add(c);

		ch = str.toCharArray();
		for(char c: ch)
		{
			if(c >= 'a' && c <= 'z');
			else if(c >= 'A' && c <= 'Z');
			else if(c >= '0' && c <= '9');
			else if(symbols.contains(c));
			else return false;
		}

		return true;
	}

	static boolean checkIdentifier(String str)
	{
		return checkIdentifier(str, "");
	}

	static void notify(String msg)
	{
		System.out.println(msg+'\n');
	}
}