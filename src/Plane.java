
public class Plane extends Item 
{
	int wy[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	double battery; //0.0-1.0
	int state; //0: free, 1 : busy, 2 : dead
	Offer O;
	Plane(int _ID, int _X, int _Y)
	{
		super(_ID, _X, _Y);
		state = 0;
		battery = 1.0;
	}
	
	void move(int d)
	{
		pos.X += wy[d][0];
		pos.Y += wy[d][1];
		if (pos.X < 0 || pos.Y < 0 || pos.X >= 300 || pos.Y >= 300)
		{
			pos.X -= wy[d][0];
			pos.Y -= wy[d][1];
		}
	}
	
	void Applyoffer(Offer O_)
	{
		O = O_;
		state = 1;
		O_.accept(this);
	}
	
	void Renew(double x, double y, double _battery)
	{
		pos.X = x; pos.Y = y; battery = _battery;
	}
}
