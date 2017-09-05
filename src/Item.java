class Pos
{
	double X, Y;
/*	Pos(int _X, int _Y)
	{
		X = _X; Y = _Y;
	} */
	Pos(double _X, double _Y)
	{
		X = _X; Y = _Y;
	}
	boolean equal(Pos B)
	{
//		System.out.println((X - B.X) * (X - B.X) + (Y - B.Y) * (Y - B.Y));
		return ((X - B.X) * (X - B.X) + (Y - B.Y) * (Y - B.Y) < 1);
	}
	void add(Pos B)
	{
/*		System.out.println("---------------------------------");
		System.out.println(X);
		System.out.println(Y);
		System.out.println(B.X);
		System.out.println(B.Y);*/
		X += B.X; Y += B.Y;
/*		System.out.println(X);
		System.out.println(Y); */
	
	}
}

public class Item{
	int ID;
	Pos pos;
	Item(int _ID, double _X, double _Y) 
	{
		pos = new Pos(_X, _Y);
		ID = _ID;
	}
}
