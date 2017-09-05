
public class LinkwithCustom {
	Modify m;
	LinkwithCustom(Modify n)
	{
		m = n;
		double x1 = 0.0, y1 = 0.0 ,x2 = 0.0, y2 = 0.0;
		n.customApply(x1, y1, x2, y2);
	}
}
