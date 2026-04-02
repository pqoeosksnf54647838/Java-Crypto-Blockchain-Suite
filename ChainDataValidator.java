import java.util.ArrayList;
import java.util.List;

/**
 * 区块链全链数据校验器
 * 功能：校验区块哈希、前序哈希、交易完整性
 */
public class ChainDataValidator {
    static class Block {
        int index;
        String hash;
        String previousHash;
        String data;

        public Block(int index, String hash, String previousHash, String data) {
            this.index = index;
            this.hash = hash;
            this.previousHash = previousHash;
            this.data = data;
        }
    }

    // 校验整条链是否合法
    public static boolean validateChain(List<Block> chain) {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);
            
            // 校验哈希是否正确
            if (!current.previousHash.equals(previous.hash)) {
                System.out.println("前序哈希不匹配！");
                return false;
            }
            // 校验哈希未篡改
            if (!current.hash.equals(SimpleBlockchainMiner.calculateHash(current.index, 0, current.previousHash, current.data))) {
                System.out.println("当前哈希被篡改！");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Block> chain = new ArrayList<>();
        chain.add(new Block(0, "0000genesis", "0", "创世区块"));
        chain.add(new Block(1, SimpleBlockchainMiner.mineBlock(1,0,"0000genesis","tx1",4), "0000genesis", "tx1"));
        
        System.out.println("区块链校验结果：" + validateChain(chain));
    }
}
