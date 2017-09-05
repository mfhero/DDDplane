
public class Map_Gravity {
	Map map;
	functions func;
	
	Map_Gravity(Map mp)
	{
		map = mp;
		func = new functions();
	}
	
	void calc()
	{
		for (int i = 0; i < map.plane.length; i++)
		{
			Plane t = map.plane[i];
			Pos G = new Pos(0, 0);
			for (int j = 0; j < map.plane.length; j++)
			{
				Plane g = map.plane[j];
				double d = func.dist(t.pos, g.pos);
				if (d > 1 && g.state == 0)
				{
					d = d * Math.sqrt(d);
					G.add(new Pos((t.pos.X - g.pos.X) / d, (t.pos.Y - g.pos.Y) / d));
				}
			}
			G.add(new Pos(1 / (t.pos.X + 0.1) / (t.pos.X + 0.1), 1 / (t.pos.Y + 0.1) / (t.pos.Y + 0.1)));
			G.add(new Pos(1 / (map.sizex - t.pos.X + 0.1) / (t.pos.X - map.sizex + 0.1), 
					1 / (map.sizey - t.pos.Y + 0.1) / (t.pos.Y - map.sizey + 0.1)));
			map.bestd[i] = G; 
		}
	}
}
