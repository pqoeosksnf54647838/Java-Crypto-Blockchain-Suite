import java.security.*;
import java.util.Base64;

/**
 * 区块链非对称加密钱包生成器
 * 功能：生成区块链地址、公钥、私钥（ETH/BNB链通用格式）
 */
public class BlockchainWalletGenerator {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    // 初始化钱包密钥对
    public BlockchainWalletGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    // 获取公钥（钱包地址基础）
    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // 获取私钥（核心资产凭证）
    public String getPrivateKey() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        BlockchainWalletGenerator wallet = new BlockchainWalletGenerator();
        System.out.println("=== 区块链钱包生成 ===");
        System.out.println("公钥: " + wallet.getPublicKey());
        System.out.println("私钥: " + wallet.getPrivateKey());
    }
}
