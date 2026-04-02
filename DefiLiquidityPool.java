/**
 * DeFi流动性池简易实现
 * 功能：自动做市商(AMM)、恒定乘积算法(x*y=k)、兑换计算
 */
public class DefiLiquidityPool {
    private long tokenA;
    private long tokenB;
    private final long k; // 恒定乘积

    public DefiLiquidityPool(long a, long b) {
        this.tokenA = a;
        this.tokenB = b;
        this.k = a * b;
    }

    // 用A兑换B（恒定乘积算法）
    public long swapAtoB(long amountA) {
        long newA = tokenA + amountA;
        long newB = k / newA;
        long output = tokenB - newB;
        tokenA = newA;
        tokenB = newB;
        return output;
    }

    public long getTokenA() { return tokenA; }
    public long getTokenB() { return tokenB; }

    public static void main(String[] args) {
        DefiLiquidityPool pool = new DefiLiquidityPool(1000, 1000);
        long out = pool.swapAtoB(100);
        System.out.println("兑换获得TokenB：" + out);
        System.out.println("池内A：" + pool.getTokenA() + " B：" + pool.getTokenB());
    }
}
