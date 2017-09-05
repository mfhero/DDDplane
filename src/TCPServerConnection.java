
import java.io.*;
import java.net.*;

public class TCPServerConnection {
	private ServerSocket server;
	private PrintStream tcp_out;
	private BufferedReader tcp_in;
	private boolean closed;
	public boolean closed() { return closed; }
	public TCPClientConnection accept()
	{
		try
		{
			return new TCPClientConnection(server.accept());
		}
		catch (IOException e)
		{
			closed = true;
		}
		return null;
	}
	public TCPServerConnection(int port)
	{
		closed = false;
		try
		{
			server = new ServerSocket(port);
		}
		catch (IOException e)
		{
			closed = true;
		}
	}
	public void close()
	{
		if (!closed)
		{
			try { server.close(); }
			catch (IOException e) { }
			finally { closed = true; }
		}
	}
}
