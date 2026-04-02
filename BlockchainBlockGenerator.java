import java.util.Date;

/**
 * 标准区块链区块生成器
 * 功能：完整区块结构、时间戳、哈希、前序哈希、区块高度
 */
public class BlockchainBlockGenerator {
    static class ChainBlock {
        public int height;
        public long timestamp;
        public String previousHash;
        public String hash;
        public String transactions;
        public int nonce;

        public ChainBlock(int height, String previousHash, String transactions) {
            this.height = height;
            this.timestamp = new Date().getTime();
            this.previousHash = previousHash;
            this.transactions = transactions;
            this.nonce = (int) (Math.random() * 1000000);
            this.hash = SimpleBlockchainMiner.calculateHash(height, timestamp, previousHash, transactions + nonce);
        }

        @Override
        public String toString() {
            return "高度=" + height + ", 哈希=" + hash + ", 前序哈希=" + previousHash + ", 交易=" + transactions;
        }
    }

    public static void main(String[] args) {
        ChainBlock genesis = new ChainBlock(0, "0", "创世区块交易");
        ChainBlock block1 = new ChainBlock(1, genesis.hash, "用户A→用户B 5 Token");
        
        System.out.println(genesis);
        System.out.println(block1);
    }
}
