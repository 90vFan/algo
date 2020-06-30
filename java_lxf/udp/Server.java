import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.IOException;


public class Server {
    public static void main(String[] args) throws IOException, SocketException {
        // Construct a DatagramSocket binds with port
        DatagramSocket ds = new DatagramSocket(6666);
        System.out.println("Server is running on 6666 ...");

        for (;;) {
            // 接受；
            // 数据缓冲区
            byte[] buffer = new byte[1024];
            // Construct a DatagramPacket for reiceiving Packets of length
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            // 收到的数据存储在buffer 中　　　　　　　　　指定起始位置　　　　　　指定长度
            String cmd = new String(packet.getData(), packet.getOffset(), packet.getLength(),
                StandardCharsets.UTF_8);
            // System.out.println("receive: " + cmd);

            // 回复UDP包
            String resp = "bad command";
            switch (cmd) {
                case "date":
                    resp = LocalDate.now().toString();
                    break;
                case "time":
                    resp = LocalTime.now().withNano(0).toString();
                    break;
                case "datetime":
                    resp = LocalDateTime.now().withNano(0).toString();
                    break;
                case "weather":
                    resp = "sunny, 10~15 C.";
                    break;
                case "hello":
                    resp = "hi";
                    break;
            }
            System.out.println(cmd + " >>> " + resp);
            byte[] data = resp.getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            ds.send(packet);
        }
    }
}