package socket;

import java.io.DataInputStream;  
import java.io.DataOutputStream;    
import java.io.FileInputStream;    
import java.io.IOException;    
import java.net.InetSocketAddress;    
import java.net.ServerSocket;    
import java.net.Socket;    
import java.util.ArrayList;
  
public class Server {    
	//定义一个线性表，用来装新建好的socket
    public static ArrayList<Socket> socketList = new ArrayList<Socket>();
     
    //定义一个线性表，用来装新建好的文件名
    public static ArrayList<String> fileList = new ArrayList<String>();
     
    //文件名序号
    public static int no=0;
     
    public static void main(String[] args) throws IOException {
         
        ServerSocket ss = new ServerSocket(40000);
         
        while(true){
            Socket s = ss.accept();
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }    
}    