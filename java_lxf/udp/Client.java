import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Construc a DatagramSocket
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(1000);
        // connect to server binds with port
        ds.connect(InetAddress.getByName("localhost"), 6666);

        // send
        String cmd = "Hello";
        if (args.length > 0) {
            cmd = args[0];
        }
        byte[] data = cmd.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        ds.send(packet);
        System.out.println("Send: " + cmd);

        // receive
        byte[] buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        ds.receive(packet);

        String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println(">>> " + resp);

        ds.disconnect();
        System.out.println("disconnected.");
    }
}