import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * 区块链敏感数据AES加密器
 * 功能：交易/私钥加密存储（链下安全）
 */
public class BlockchainDataEncryptor {
    // 生成AES密钥
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    // 加密
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // 解密
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        SecretKey key = generateKey();
        String raw = "privateKey:0xABCDEFG123456789";
        String enc = encrypt(raw, key);
        String dec = decrypt(enc, key);
        
        System.out.println("原始数据：" + raw);
        System.out.println("加密后：" + enc);
        System.out.println("解密后：" + dec);
    }
}
