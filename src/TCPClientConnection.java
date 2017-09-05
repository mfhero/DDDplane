
import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClientConnection {
	private Socket client;
	private OutputStream tcp_out;
	private InputStream tcp_in;
	private boolean closed;
	public boolean closed() { return closed; }
	public void send(byte[] st)
	{
		try {
			st = Arrays.copyOf(st, st.length + 8);
			st[st.length - 1] = (byte)0xff;
			st[st.length - 2] = (byte)0xff;
			st[st.length - 3] = (byte)0xff;
			st[st.length - 4] = (byte)0xff;
			st[st.length - 5] = (byte)0xff;
			st[st.length - 6] = (byte)0xff;
			st[st.length - 7] = (byte)0xff;
			st[st.length - 8] = (byte)0xff;
			tcp_out.write(st);
		}
		catch (IOException e)
		{
			closed = true;
		}
	}
	public byte[] receive()
	{
		try
		{
			ArrayList<Byte> res = new ArrayList<Byte>();
			int tmp;
			int len = 0;
			while (len < 8)
			{
				tmp = tcp_in.read();
				if (tmp == -1) return null;
				res.add((byte)tmp);
				if (tmp == 255) ++len;
				else len = 0;
			}
			Byte[] lst = new Byte[res.size()];
			lst = res.toArray(lst);
			byte[] lst2 = new byte[res.size() - 8];
			for (int i = 0; i < res.size() - 8; ++i)
				lst2[i] = lst[i];
			return lst2;
		}
		catch (IOException e)
		{
			closed = true;
		}
		return null;
	}
	public TCPClientConnection(Socket cl)
	{
		closed = false;
		try
		{
			client = cl;
			tcp_out = client.getOutputStream();
			tcp_in = client.getInputStream();
		}
		catch (IOException e) { closed = true; }
	}
	public TCPClientConnection(String IP_addr, int port)
	{
		closed = false;
		try
		{
			client = new Socket(IP_addr, port);
			tcp_out = client.getOutputStream();
			tcp_in = client.getInputStream();
		}
		catch (IOException e) { closed = true; }
	}
	public void close()
	{
		if (!closed)
		{
			try { client.close(); }
			catch (IOException e) { }
			finally { closed = true; }
		}
	}
}
