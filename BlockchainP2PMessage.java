import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 区块链P2P网络消息广播
 * 功能：节点间区块/交易广播（去中心化通信基础）
 */
public class BlockchainP2PMessage {
    private static final int PORT = 8888;

    // 广播消息到P2P网络
    public static void broadcastMessage(String message) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, InetAddress.getByName("255.255.255.255"), PORT
            );
            socket.send(packet);
            System.out.println("P2P广播完成：" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听P2P网络消息
    public static void startListener() {
        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(PORT)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("收到P2P消息：" + msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        startListener();
        broadcastMessage("NEW_BLOCK:height=100 hash=0xabc123");
    }
}
