import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	
	private static Chat chat;
	private static Accept accept;
	private static ServerSocket serverSocket;
	
	public static void main(String args[]){
		try {
			serverSocket = new ServerSocket(12345);
			System.out.println("porta 12345 aberta");
			chat = new Chat(serverSocket);
			accept = new Accept(serverSocket, chat);
			
			Thread tChat = new Thread(chat);
			Thread tAccept = new Thread(accept);
			
			tAccept.start();
			tChat.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	int x;

}
