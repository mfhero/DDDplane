import java.util.concurrent.*;
import java.nio.ByteBuffer;

public class LinkwithPlane {
	public static byte[] toByteArray(double value) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(value);
		return bytes;
	}

	public static double toDouble(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getDouble();
	}
	void sendOrder(Integer ID, double x, double y)
	{
		if (hashMap.containsKey(ID))
		{
			TCPClientConnection client = hashMap.get(ID);
			if (!client.closed())
			{
				byte tmp[] = new byte[1 + 8 + 8];
				tmp[0] = 0;
				System.arraycopy(toByteArray(x), 0, tmp, 1, 8);
				System.arraycopy(toByteArray(y), 0, tmp, 1 + 8, 8);
				client.send(tmp);
			}
		}
	}
	Modify m;
	TCPServerConnection server;
	ConcurrentHashMap<Integer, TCPClientConnection> hashMap;
	LinkwithPlane(Modify n)
	{
		m = n;
		server = new TCPServerConnection(22222);
		hashMap = new ConcurrentHashMap<Integer, TCPClientConnection>();
		new Thread(new Runnable() {
			public void run() {
				while (!server.closed())
				{
					TCPClientConnection client = server.accept();
					Integer ID = 0;
					synchronized(this) {
						while (hashMap.containsKey(ID))
							ID = (int)(Math.random() * 23);
						hashMap.put(ID, client);
					}
					final Integer MyID = ID;
					new Thread(new Runnable() {
						public void run() {
							while (!client.closed())
							{
								byte[] mes = client.receive();
								if (mes != null)
								{
									if (mes.length > 0 && mes[0] == 0)
									{
										byte x0[] = new byte[8];
										byte y0[] = new byte[8];
										byte battery0[] = new byte[8];
										System.arraycopy(mes, 1, x0, 0, 8);
										System.arraycopy(mes, 1 + 8, y0, 0, 8);
										System.arraycopy(mes, 1 + 16, battery0, 0, 8);
										double x, y, battery;
										x = toDouble(x0);
										y = toDouble(y0);
										battery = toDouble(battery0);
										synchronized(this) {
											n.recmessage(MyID, x, y, battery);
										}
									}
								}
							}
							synchronized(this) {
								n.recmessage(MyID, -1, -1, 0);
							}
						}
					}).start();
				}
			}
		}).start();
		/*
		int ID = 0, x = 0 , y = 0;
		double battery = 1.0;
		n.recmessage(ID, x, y, battery);
		*/
	}
}
