import java.util.Scanner;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CriptografiaSimetrica {
	public static byte[] decrypt(byte[] cipherBytes,
			byte[] key, byte[] nonce, int counter) {
			byte[] decryptedBytes = null;
			Cipher cipher;
		
		try {
			cipher = Cipher.getInstance("ChaCha20");
			ChaCha20ParameterSpec paramSpec = new
			ChaCha20ParameterSpec(nonce, counter);
			SecretKeySpec keySpec = new SecretKeySpec(key,"ChaCha20");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
			decryptedBytes = cipher.doFinal(cipherBytes);
		}
	
		catch (NoSuchAlgorithmException |
		NoSuchPaddingException | InvalidKeyException |
		InvalidAlgorithmParameterException |
		IllegalBlockSizeException | BadPaddingException
		e) {
			e.printStackTrace();
		}
			return decryptedBytes;
		}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
        Scanner teclado = new Scanner(System.in);
        System.out.println("Frase criptografada: ");
        String cipherText = teclado.nextLine();
        
        System.out.println("Chave: ");
        String keyText = teclado.nextLine();
        
        System.out.println("Nonce: ");
        String nonceText = teclado.nextLine();
        
        int conter = 1;
      
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
		byte[] keyBytes = Base64.getDecoder().decode(keyText);
		byte[] nonceBytes = Base64.getDecoder().decode(nonceText);
		
		byte[] decryptedBytes = decrypt(cipherBytes, keyBytes, nonceBytes, conter);
		String decryptedText = new String(decryptedBytes);
		System.out.println(decryptedText);
	}
}