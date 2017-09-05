import java.io.FileInputStream;
import java.util.Scanner;

public class Modify
{
	Map map;
	public int bestd[];
//	public Offer poffer[];
	functions func;
	public LinkwithPlane LWK;
	public LinkwithCustom LWC;
	Addoffer AO;
	int delay[];
	PaintMap pm;
	boolean visable = true;
	String filename;
	int step, totaldelay;
	
	Modify(String args[])
	{
		if (visable == false)
			filename = args[0];
	}
	
	void planemove()
	{
		if (visable && Math.random() > 0.98)
		{
			int ox = (int)(Math.random() * map.sizex), oy = (int)(Math.random() * map.sizey);
			int ox1 = (int)(Math.random() * map.sizex), oy1 = (int)(Math.random() * map.sizey);
			Offer of = new Offer(ox, oy, ox1, oy1);
			Plane t = map.near_plane(of.S);
	//		poffer[t.ID] = of;
			if (t != null)
				t.Applyoffer(of);
	//		t.state = 1;
			
			for (int i = 0; i < map.cnum; i++)
			{
				if (map.offer[i] == null || map.offer[i].state == 2)
				{
					map.offer[i] = of;
					break;
				}
			} 
		}
		
		for (int i = 0; i < map.cnum; i++)
		{
			Offer g = map.offer[i];
			if (g != null && g.state == 2)
				map.offer[i] = null;
			if (map.offer[i] != null && (g.produce == null || g.produce.state != 1))
			{
				Plane t = map.near_plane(map.offer[i].S);
				if (t != null)
					t.Applyoffer(map.offer[i]);
			}
		}

		map.evalue();
		for (int i = 0; i < map.plane.length; i++)
		{
//			map.plane[i].move((int)(Math.random() * 4));
			Plane t = map.plane[i];
			if (t.state == 0)
			{
				double bestv = 0;
				/*int mind = 0;
				for (int d = 0; d < 4; d++)
				{
					t.move(d);
					double v = map.evalue();
					if (v < bestv)
					{
						bestv = v;
						mind = d;
					}
					t.move(d ^ 2); 
					if (map.for_bd[i][d] > bestv)
					{
						bestv = map.for_bd[i][d];
						mind = d;
					}
				}*/
				bestd[i] = map.mind[i];
			} else if (t.state == 1)
			{
				if (t.O == null)
					map.plane[i].state = 0;
				else {
					Offer g = t.O;
					if (g.state == 0)
					{	
						if (t.pos.equal(g.S))
						{
							g.state = 1;
							if (visable == false)
								totaldelay += step - g.offertime;
						} else {
							Pos dir = func.dir(t.pos, g.S);
							t.pos.add(dir);
							if (visable)
								LWK.sendOrder(i, dir.X, dir.Y);
							bestd[i] = -1;
						}
					}
					if (g.state == 1)
					{
						if (t.pos.equal(g.T))
						{
							t.O = null;
							g.state = 2;
							t.state = 0;
							bestd[i] = 0;
						}
						else {
							bestd[i] = -1;
							Pos dir = func.dir(t.pos, g.T);
							t.pos.add(dir);
							if (visable)
								LWK.sendOrder(i, dir.X, dir.Y);
						}
					}
				}
					
			} else bestd[i] = -1;
		}
		for (int i = 0; i < map.plane.length; i++)
			if (bestd[i] > -1)
			{
				map.plane[i].move(bestd[i]);
				if (visable)
					LWK.sendOrder(i, map.plane[i].wy[bestd[i]][0], map.plane[i].wy[bestd[i]][1]);
			}
	/*		if (Math.random() > 0.99)
				map.plane[i].state ^= 1; */
	}
	
	void recmessage(int ID, double x, double y, double battery)
	{
/*		System.out.println(ID);
		System.out.println(x);
		System.out.println(y);
		System.out.println(battery); */ 
		if (x == -1.0 && y == -1.0 && battery == 0.0)
			map.plane[ID].state = 2;
		else
			map.plane[ID].Renew(x, y, battery);
	}
	
	void customApply(double x1, double y1, double x2, double y2)
	{
		
	}
	
	void Applyoffer(int ox, int oy, int ox1, int oy1)
	{
		dealoffer(pm.Soupx(ox), pm.Soupx(oy), pm.Soupx(ox1), pm.Soupx(oy1));
	}
	
	void dealoffer(double ox, double oy, double ox1, double oy1)
	{
		Offer of = new Offer(ox, oy, ox1, oy1);
		Plane t = map.near_plane(of.S);
//		poffer[t.ID] = of;
		if (t != null)
			t.Applyoffer(of);
//		t.state = 1;:
		
		for (int i = 0; i < map.cnum; i++)
		{
			if (map.offer[i] == null || map.offer[i].state == 2)
			{
				map.offer[i] = of;
				break;
			}
		} 
		
		if (visable == false)
			of.settime(step);
	}
	
	public void start() throws InterruptedException
	{
		map = new Map();

		if (visable) {
			for (int i = 0; i < map.pnum; i++) 
				map.plane[i] = new Plane(i, (int)(Math.random() * map.sizex), (int)(Math.random() * map.sizey));
		} else {
			for (int i = 0; i < map.pnum; i++)
				map.plane[i] = new Plane(i, 0, 0);
		}
		bestd = new int[map.plane.length];
		func = new functions();
		
		if (visable)
		{
			pm = new PaintMap(map);
			delay = new int[map.plane.length];
//		poffer = new Offer[map.plane.length];
			LWK = new LinkwithPlane(this);
			LWC = new LinkwithCustom(this);
			AO = new Addoffer(this);
			map.addMouseListener(AO);
			map.addMouseMotionListener(AO);
		}
		if (visable)
		{
		new Thread(new Runnable() {
			public void run() {
				while (true)
				{
					for (int i = 0; i < 3; i++)
						planemove();
					//(new Print()).print(map);
					pm.draw();

		//			if (true) break;
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  
				}
			}
		}).start();
		} else {
			FileInputStream inS = null;
			Scanner sc = null;
			for (int al = 0; al < 2; al++)
			{
			try {
				map.set_algorithm(al);
				inS = new FileInputStream(filename);
				sc = new Scanner(inS);
				step = 0;
				totaldelay = 0;
				while (sc.hasNextInt())
				{
					int a, b, c, d;
					a = sc.nextInt();
					b = sc.nextInt();
					c = sc.nextInt();
					d = sc.nextInt();
					dealoffer(a, b, c, d);
					for (int i = 0; i < 30; i++)
					{
						planemove();
						step++;
					}
				}
				while (true)
				{
					boolean flag = false;
					for (int i = 0; i < map.pnum; i++)
						if (map.plane[i].state == 1)
						{
							flag = true;
							break;
						}
					if (flag == false)
						break;
					planemove();
					step++;
				}
				System.out.println(totaldelay);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	}
}
