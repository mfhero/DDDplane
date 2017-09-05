
public class Map_Flow {
	functions func;
	Map map;
	
	Map_Flow(Map mp)
	{
		map = mp;
		func = new functions();
	}
	
	void calc()
	{
		for (int i = 0; i < map.pnum; i++)
			map.bestd[i] = new Pos(0.0, 0.0);
		for (int i = 0; i < map.sizex; i++)
			for (int j = 0; j < map.sizey; j++)
			{
				Pos p = new Pos(i, j);
				Plane t = map.near_plane(p);

				if (t != null)
				{
					if (i > t.pos.X) 
						map.bestd[t.ID].X++;
					else if (i < t.pos.X)
						map.bestd[t.ID].X--;
					if (j > t.pos.Y)
						map.bestd[t.ID].Y++;
					else if (j < t.pos.Y)
						map.bestd[t.ID].Y--;
				}
			}
	}
}
