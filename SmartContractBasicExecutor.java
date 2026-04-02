import java.util.HashMap;
import java.util.Map;

/**
 * 极简智能合约执行器
 * 功能：链上状态存储、合约调用、余额变更
 */
public class SmartContractBasicExecutor {
    // 链上状态存储
    private final Map<String, Integer> state = new HashMap<>();

    // 合约：转账
    public void transfer(String from, String to, int amount) {
        if (state.getOrDefault(from, 0) < amount) {
            throw new RuntimeException("余额不足");
        }
        state.put(from, state.get(from) - amount);
        state.put(to, state.getOrDefault(to, 0) + amount);
        System.out.println("合约执行成功：" + from + " → " + to + " 金额：" + amount);
    }

    // 初始化余额
    public void mint(String address, int amount) {
        state.put(address, state.getOrDefault(address, 0) + amount);
    }

    public int getBalance(String address) {
        return state.getOrDefault(address, 0);
    }

    public static void main(String[] args) {
        SmartContractBasicExecutor contract = new SmartContractBasicExecutor();
        contract.mint("0xUSER1", 100);
        contract.transfer("0xUSER1", "0xUSER2", 30);
        System.out.println("USER1余额：" + contract.getBalance("0xUSER1"));
        System.out.println("USER2余额：" + contract.getBalance("0xUSER2"));
    }
}
