
public class Map_Native {
	functions func;
	Map map;
	
	Map_Native(Map mp)
	{
		map = mp;
		func = new functions();
	}
	
	double evalue()
	{
		double e = 0;
		for (int i = 0; i < map.sizex; i++)
			for (int j = 0; j < map.sizey; j++)
			{
				Pos p = new Pos(i, j);
				Plane t = map.near_plane(p);
				if (t != null)
					e += func.dist(t.pos, p);
			}
		return e;
	}
	
	void calc()
	{
		for (int i = 0; i < map.pnum; i++)
		{
			int mind = 0;
			double mine = 1e10;
			Plane t = map.plane[i];
			for (int d = 0; d < 4; d++)
			{
				t.move(d);
				double ev = evalue();
				if (ev < mine)
				{
					mine = ev;
					mind = d;
				}
				t.move(d ^ 2);
			}
			map.bestd[i] = new Pos(t.wy[mind][0], t.wy[mind][1]);
		}
	}
}
