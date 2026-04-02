# Java Blockchain Development Suite
基于Java语言构建的企业级区块链开发工具集，覆盖公链/联盟链/DeFi/NFT全场景核心功能，代码遵循工业级标准，可直接用于学习、二次开发与生产环境。

## 项目简介
本仓库包含12个独立、无重复的区块链核心模块，实现从底层密码学、链结构、共识机制到上层应用（智能合约、DeFi、NFT）的全栈能力，所有代码可直接编译运行，无第三方依赖冲突。

## 包含功能模块
1. **BlockchainWalletGenerator** - 区块链ECDSA非对称钱包生成器，支持公钥/私钥/地址生成
2. **SimpleBlockchainMiner** - 工作量证明(PoW)挖矿实现，支持难度动态调整与哈希计算
3. **TransactionSignatureValidator** - 交易数字签名与验签，保障链上数据不可篡改
4. **MerkleTreeGenerator** - 默克尔树生成器，用于区块交易批量校验与根哈希计算
5. **BlockchainP2PMessage** - 去中心化P2P网络广播，实现节点间区块/交易同步
6. **SmartContractBasicExecutor** - 极简智能合约执行器，支持链上状态与转账逻辑
7. **BlockchainConsensusPBFT** - PBFT拜占庭容错共识，适配联盟链多节点投票
8. **NFTMetadataGenerator** - ERC721标准NFT元数据生成，支持IPFS与属性配置
9. **BlockchainDataEncryptor** - AES-256链下数据加密，保护私钥与敏感交易信息
10. **ChainDataValidator** - 全区块链校验工具，检测哈希篡改与链结构合法性
11. **DefiLiquidityPool** - DeFi恒定乘积做市商(AMM)，实现流动性池与代币兑换
12. **BlockchainBlockGenerator** - 标准区块结构生成，支持高度、时间戳、随机数与哈希

## 技术栈
- 开发语言：Java 8+
- 核心算法：SHA-256、ECDSA、AES-256、默克尔树、PoW、PBFT
- 应用场景：公链、联盟链、钱包、挖矿、智能合约、DeFi、NFT、P2P网络

## 使用说明
所有类均包含main方法，直接运行即可查看效果，可按需集成至SpringBoot/分布式区块链系统。
