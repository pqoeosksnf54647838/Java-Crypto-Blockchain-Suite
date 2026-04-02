import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * NFT元数据生成器
 * 功能：生成ERC721标准NFT元数据（图片/属性/ID）
 */
public class NFTMetadataGenerator {
    // 生成NFT标准元数据
    public static String generateNFTMetadata(String name, String imageUrl, String description) {
        try {
            Map<String, Object> meta = new HashMap<>();
            meta.put("name", name + " #" + UUID.randomUUID().toString().substring(0, 8));
            meta.put("description", description);
            meta.put("image", imageUrl);
            meta.put("tokenId", Math.abs(UUID.randomUUID().getMostSignificantBits()));
            
            Map<String, String> attributes = new HashMap<>();
            attributes.put("rarity", "LEGENDARY");
            attributes.put("chain", "Ethereum");
            meta.put("attributes", attributes);
            
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(meta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String meta = generateNFTMetadata(
            "JavaBlockchainNFT",
            "ipfs://QmXYZ123",
            "Java开发者专属数字藏品"
        );
        System.out.println("=== NFT元数据 ===");
        System.out.println(meta);
    }
}
