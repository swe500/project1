package ipc.udp;

import java.io.IOException;
import java.net.*;
import java.util.Objects;

/**
 * Created by Sparta on 5/18/2017.
 */
public class Server {
    public static void main(String[] args){
        DatagramSocket sc = null;
        try{
            sc = new DatagramSocket(1234);

            while (true){
                byte[] data = new byte[100];
                DatagramPacket dp = new DatagramPacket(data, data.length);

                /*We don't need IP address for server program just to receive any message,
                but while sending response
                 */
                sc.receive(dp);
                String clientdata =  new String(dp.getData()).trim();

                //System.out.print(Objects.equals(clientdata,"quit                if (clientdata.equals("quit")) break;
                System.out.println("Message Client: " + clientdata);

                /*Send back data to client.
                Get client details from packet received previously
                 */
                data = "Hey".getBytes();
                sc.send(new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort()));
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sc.close();
        }
    }
}
