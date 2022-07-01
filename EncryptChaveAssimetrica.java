import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptChaveAssimetrica {

	public static byte[] encrypt(byte[] plainBytes, byte[] key, byte[] nonceBytes, int counter) {
		byte[] cipherBytes = null;
		Cipher cipher;

		try {
			cipher = Cipher.getInstance("ChaCha20");
			ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);
			SecretKeySpec keySpec = new SecretKeySpec(key, "ChaCha20");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
			cipherBytes = cipher.doFinal(plainBytes);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return cipherBytes;
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		byte[] nonce = new byte[12];

		new SecureRandom().nextBytes(nonce);

		int counter = 5;
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
		
		keyGenerator.init(256);
		
		SecretKey key = keyGenerator.generateKey();
		
		byte[] keyBytes = key.getEncoded();

		String plainText = "qualquer coisa";
		
		byte[] cipherBytes = encrypt(plainText.getBytes(), keyBytes, nonce, counter);
		
		String cipherText = Base64.getEncoder().encodeToString(cipherBytes);
		
		String cipherKey = Base64.getEncoder().encodeToString(keyBytes);
		
		String cipherNonce = Base64.getEncoder().encodeToString(nonce);
		
		System.out.println(cipherText);
		
		System.out.println(cipherKey);
		
		System.out.println(cipherNonce);
		
	}

}
