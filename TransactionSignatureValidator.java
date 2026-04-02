import java.security.*;
import java.util.Base64;

/**
 * 区块链交易签名验证器
 * 功能：交易数据签名、验签（防止篡改）
 */
public class TransactionSignatureValidator {
    // 用私钥签名交易
    public static String signTransaction(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // 用公钥验签
    public static boolean verifyTransaction(String data, String signatureStr, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureStr));
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        KeyPair keyPair = keyGen.generateKeyPair();
        String txData = "from:0x123 to:0x456 amount:5.5";
        
        String sign = signTransaction(txData, keyPair.getPrivate());
        boolean isValid = verifyTransaction(txData, sign, keyPair.getPublic());
        
        System.out.println("交易数据：" + txData);
        System.out.println("签名：" + sign);
        System.out.println("验签结果：" + isValid);
    }
}
