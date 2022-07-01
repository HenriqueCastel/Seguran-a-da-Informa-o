import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptChaveAssimetrica {

	public static byte[] decrypt(byte[] cipherBytes, byte[] key, byte[] nonce, int counter) {
		byte[] decryptedBytes = null;
		Cipher cipher;

		try {
			cipher = Cipher.getInstance("ChaCha20");
			ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonce, counter);
			SecretKeySpec keySpec = new SecretKeySpec(key, "ChaCha20");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
			decryptedBytes = cipher.doFinal(cipherBytes);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			((Throwable) e).printStackTrace();
		}
		return decryptedBytes;
	}

	public static void main(String[] args) throws IOException {

		String cipherText = "SWyA69cO1MW5qrdAYOzPbbQrMDZ0ZS7cAq8Amiaw";

		String keyText = "fbqvaDNNV37cylyhU4nGFAMXzmvREZoTV9bfCyY4N20=";

		String nonceText = "YKXchda//DVHDM1X";

		int conter = 5;

		byte[] cipherBytes = Base64.getDecoder().decode(cipherText);

		byte[] keyBytes = Base64.getDecoder().decode(keyText);

		byte[] nonceBytes = Base64.getDecoder().decode(nonceText);

		byte[] decryptedBytes = decrypt(cipherBytes, keyBytes, nonceBytes, conter);

		String decryptedText = new String(decryptedBytes);

		System.out.println(decryptedText);

	}
}