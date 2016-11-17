import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Chat implements Runnable {
	
	private ArrayList<Socket> lst = new ArrayList<Socket>();
	private ArrayList<PrintStream> messageLst = new ArrayList<PrintStream>();
	private ArrayList<Scanner> scannerLst = new ArrayList<Scanner>();
	private ServerSocket serverSocket;
	
	public Chat(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			for(Scanner scanner : scannerLst){
				if(scanner.hasNext()){
					message(scanner.next());
				}
			}
		}
	}
	
	public void message(String message){
		if(message.equals("exit()"))
			this.finalize();
		for (PrintStream printStream : messageLst ){
			printStream.println(message);
		}
	}
	
	public void addClient(Socket socket){
		this.lst.add(socket);
		try {
			this.messageLst.add(new PrintStream(socket.getOutputStream()));
			this.scannerLst.add(new Scanner(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void finalizeScanner(){
		for (Scanner scanner : scannerLst){
			scanner.close();
		}
	}
	
	public void finalizeClients(){
		for(Socket socket : lst){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void finalize(){
		this.finalizeScanner();
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.finalizeClients();
	}

}
