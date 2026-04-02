import java.security.MessageDigest;
import java.util.Date;

/**
 * 极简区块链挖矿实现
 * 功能：工作量证明(PoW)、区块哈希计算、挖矿难度控制
 */
public class SimpleBlockchainMiner {
    // 计算区块哈希
    public static String calculateHash(int index, long timestamp, String previousHash, String data) {
        String input = index + timestamp + previousHash + data;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 挖矿：满足难度前缀
    public static String mineBlock(int index, long timestamp, String previousHash, String data, int difficulty) {
        String prefix = "0".repeat(difficulty);
        String nonce = "0";
        String hash;
        do {
            hash = calculateHash(index, timestamp, previousHash, data + nonce);
            nonce = String.valueOf(Integer.parseInt(nonce) + 1);
        } while (!hash.startsWith(prefix));
        return hash;
    }

    public static void main(String[] args) {
        int difficulty = 4;
        String hash = mineBlock(1, new Date().getTime(), "0000genesis", "tx:transfer 10 Token", difficulty);
        System.out.println("挖矿完成！区块哈希：" + hash);
    }
}
