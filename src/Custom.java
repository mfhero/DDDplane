
public class Custom extends Item{
	Offer O;
	Custom(int ID, int _X, int _Y)
	{
		super(ID, _X, _Y);
	}
	
	void ApplyOffer(Offer O_)
	{
		O = O_;
	}
}
