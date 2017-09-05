import javax.swing.JFrame;

public class Map extends JFrame{
	public int M[][];
	int sizex = 250, sizey = 250;
	int pnum = 23;
	int cnum = 500;
	Plane plane[];
	Custom custom[];
	Offer offer[];
	int atype = 0;
	
	functions func = new functions();
//	public int for_bd[][];
	public Pos bestd[];
	public int mind[];
	
	Map_Gravity MG;
	Map_Flow MF;
	Map_Native MN;

	Map()
	{
		M = new int[sizex][sizey];
//		for_bd = new int[pnum][4];
		mind = new int[pnum];
		bestd = new Pos[pnum];
		for (int i = 0; i < sizex; i++)
			for (int j = 0; j < sizey; j++)
				M[i][j] = 1;
		plane = new Plane[pnum];
		//custom = new Custom[cnum];
		offer = new Offer[cnum];
		//atype = 0;
		
		MG = new Map_Gravity(this);
		MF = new Map_Flow(this);
		MN = new Map_Native(this);
/*		for (int i = 0; i < cnum; i++)
			custom[i] = new Custom(i, 0, 0); */
	}

	
	Plane near_plane(Pos s)
	{
		double mind = 100000000;
		int mini = -1;
		for (int i = 0; i < plane.length; i++)
			if (func.dist(plane[i].pos, s) < mind && plane[i].state == 0)
			{
				mind = func.dist(plane[i].pos, s);
				mini = i;
			}
		if (mini == -1) return null;
		return plane[mini];
	}	
	
	int translate(Pos X)
	{
		int d = 0;
		if (X.Y < 0) d = 2;
		if (Math.abs(X.X) > Math.abs(X.Y))
			if (X.X > 0)
				d = 1;
			else 
				d = 3;
		return d;
	}
	
	void set_algorithm(int x)
	{
		atype = x;
	}
	
	double evalue()
	{
		if (atype == 0)
			MG.calc();
		if (atype == 1)
			MF.calc();
		if (atype == 2)
			MN.calc();
		for (int i = 0; i < pnum; i++)
			mind[i] = translate(bestd[i]);
	/*	functions func = new functions();
		for (int i = 0; i < pnum; i++)
			for (int j = 0; j < 4; j++)
				for_bd[i][j] = 0;
		double e = 0;
		for (int i = 0; i < sizex; i++)
			for (int j = 0; j < sizey; j++)
			{
				Pos p = new Pos(i, j);
				Plane t = near_plane(p);

				if (t != null)
				{
					e += func.dist(t.pos, p);
					if (i > t.pos.X) 
						for_bd[t.ID][1]++;
					else if (i < t.pos.Y)
						for_bd[t.ID][3]++;
					if (j > t.pos.Y)
						for_bd[t.ID][0]++;
					else if (j < t.pos.Y)
						for_bd[t.ID][2]++;
				}
			} */
		return 0.0;
	}
}
