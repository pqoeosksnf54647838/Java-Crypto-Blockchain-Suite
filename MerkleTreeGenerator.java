import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * 区块链默克尔树生成器
 * 功能：批量交易哈希构建、根哈希计算（区块交易校验核心）
 */
public class MerkleTreeGenerator {
    // 计算SHA256哈希
    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 生成默克尔根
    public static String generateMerkleRoot(List<String> transactions) {
        List<String> layer = new ArrayList<>();
        for (String tx : transactions) layer.add(sha256(tx));
        
        while (layer.size() > 1) {
            List<String> newLayer = new ArrayList<>();
            for (int i = 0; i < layer.size(); i += 2) {
                String left = layer.get(i);
                String right = (i + 1 < layer.size()) ? layer.get(i + 1) : left;
                newLayer.add(sha256(left + right));
            }
            layer = newLayer;
        }
        return layer.get(0);
    }

    public static void main(String[] args) {
        List<String> txs = List.of("tx1:send 1", "tx2:send 2", "tx3:send 3");
        System.out.println("默克尔根：" + generateMerkleRoot(txs));
    }
}
