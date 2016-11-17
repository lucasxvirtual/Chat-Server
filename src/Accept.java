import java.io.IOException;
import java.net.*;

public class Accept implements Runnable {

	private ServerSocket serverSocket;
	private Chat chat;
	
	public Accept(ServerSocket serverSocket, Chat chat){
		this.serverSocket = serverSocket;
		this.chat = chat;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Socket socket = serverSocket.accept();
				System.out.println("nova conexao com cliente");
				chat.addClient(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	

}
