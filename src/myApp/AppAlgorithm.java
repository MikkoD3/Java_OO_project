package myApp;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@SuppressWarnings("restriction")
public class AppAlgorithm {
	
//AppGUI appGUI;






	//Define the algorithm used for encryption
	public static String algo = "AES";
	public static byte[] keyValue;
	

	public AppAlgorithm(String key) throws NoSuchAlgorithmException {
		keyValue = key.getBytes();
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		keyValue = sha.digest(keyValue);
		keyValue = Arrays.copyOf(keyValue, 16);
	}
	
	//Generate the key for the algorithm
	public Key generateKey() throws Exception {
		
		Key key = new SecretKeySpec(keyValue, algo);
		return key;
	}
	

	//Encrypt the text user has submitted
	public String encrypt (String msg) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(msg.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
		
	
	}
	
	//Decrypt the text the user has submitted
	public String decrypt (String msg) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(msg);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
		
	}
	
}
