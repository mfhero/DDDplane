
public class Offer {
	Pos S, T;
	int state;
	int offertime;
	Plane produce;
	Offer(double d, double e, double f, double g)
	{
		S = new Pos(d, e);
		T = new Pos(f, g);
		state = 0;
	}
	
	void settime(int t)
	{
		offertime = t;
	}
	
	void accept(Plane p)
	{
		produce = p;
	}
}
