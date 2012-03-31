import java.net.*;
import java.io.*;
import java.util.*;


public class MyClient{
	static int port_no = 4321;
	static String port_id = null; //"127.0.0.1";
	static Socket socket=null;
	
	public static void SendMessage(String msg)
	{
		try{
			socket= new Socket(port_id, port_no);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(os);
			oos.writeObject(msg);

		}
		catch(Exception e)
		{}
	}
	
	public static String show()
	{
		String msg="";
		try{
			
			//System.out.println("=======Enter message=====");
			Scanner scn=new Scanner(System.in);
			msg=scn.nextLine();
			
		}
		catch(Exception e)
		{}
		return msg;
	}
	
	public static void recieveMsg(final Socket server)
	{
		Thread t = new Thread(){
			public void run()
			{
				try
				{
					InputStream is=server.getInputStream();
					ObjectInputStream ois=new ObjectInputStream(is);
					Object msg = ois.readObject();
					System.out.println("====Server Say's====");
					System.out.println(msg);
					System.out.println("====You Type========");
				}
				catch(Exception e){
				e.printStackTrace();
				}
			}			
		};
		t.start();
		//t.stop();
	}
	
	public static void main(String...args)
	{
		String message="";
		System.out.println("Enter the Server's IP address: ");
		Scanner scn = new Scanner(System.in);
		String ServerIP = scn.nextLine();
		port_id = ServerIP;	
		System.out.println("This Client is ready now. You may type your message below.");
	
		while(true)
		{			
			message =show();
			if(message.equalsIgnoreCase("quit"))
			{
				System.exit(0);
			}
			else
			{
				SendMessage(message);
				recieveMsg(socket);
			}
		}
	}
}
