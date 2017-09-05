
public class functions {
	double dist(Pos x, Pos y)
	{
		return (x.X - y.X) * (x.X - y.X) + (x.Y - y.Y) * (x.Y - y.Y);
	}
	
	Pos dir(Pos x, Pos y)
	{
		double rd = Math.sqrt(dist(x, y));
//		System.out.println(y.X - x.X);
//		System.out.println(y.Y - x.Y);
		if (rd >= 1)
			return new Pos((y.X - x.X) / rd, (y.Y - x.Y) / rd);
		else
			return new Pos(0.0, 0.0);
	}
	
	Pos add(Pos A, Pos B)
	{
		return new Pos(A.X + B.X, A.Y + B.Y);
	}
}
