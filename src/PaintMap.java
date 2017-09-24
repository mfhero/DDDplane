//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class PaintMap
{
	Map mp = null;
	Toolkit too = Toolkit.getDefaultToolkit();
	java.util.Map<String, Image> imgs = new HashMap<String, Image>(); // 
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
			jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null;
	Image screenImage = null;
	Image[] tankImags = null, wallImags = null, homeImags = null, metalImags = null; // 
	Image[] bulletImages = null, riverImags = null, treeImags = null, bombImags = null, bloodImags = null;

	boolean stoped = false;
	Font font = new Font("TimesRoman", Font.BOLD, 15);
	
	int xwidth = 2, ywidth = 2;
	int drawx, drawy;
	
	int Realpx(double x)
	{
		return (int)(x * xwidth + 20 + 0.5);
	}
	
	double Soupx(int x)
	{
		return ((double)(x - 20)) / xwidth;
	}

	PaintMap(Map map) {
		mp = map;
		drawx = mp.sizex * xwidth + 50;
		drawy = mp.sizey * ywidth + 50;
		
		initFrame();
//		initTank();

//		mp.getGraphics().fillRect(0, 0, mp.sizex * xwidth, mp.sizey * ywidth);
//		mp.getGraphics().drawImage(tankImags[0], 35, 35, null);
	}
	
	public void draw() {
				mp.getGraphics().setColor(Color.BLUE);
				mp.getGraphics().fillRect(0, 0, drawx, drawy);
				drawOffer();
				drawPlane();
		//mp.getGraphics().fillRect(0,  0,  40,  40);
	//	mp.getGraphics().drawImage(tankImags[0], 20, 20, 40, 40, null);
	}
	
	public void drawPlane() {
		Graphics g = mp.getGraphics();
		int tot = 0;
		for (int i = 0; i < mp.plane.length; i++) {
			Plane t = mp.plane[i];
			if (t.state == 1 && t.O.state == 1)
			{
				((Graphics2D)g).setStroke(new BasicStroke(4f));
				g.setColor(Color.BLUE);
				g.drawLine(Realpx(t.O.S.X), Realpx(t.O.S.Y), Realpx(t.pos.X), Realpx(t.pos.Y));
			}
		}
		for(int i = 0; i < mp.plane.length; i++) {
			Plane t = mp.plane[i];
			
			if (t.state == 1) {
				tot++;
				if (t.ID == 0) g.setColor(Color.PINK);
				else g.setColor(Color.RED);
				g.fillOval(Realpx(t.pos.X) - 7, Realpx(t.pos.Y) - 7, 15, 15);
			} else {
				if (t.state == 0)
					if (t.ID == 0) g.setColor(Color.DARK_GRAY);
					else g.setColor(Color.GREEN);
				else 
					if (t.ID == 0) g.setColor(Color.MAGENTA);
					else g.setColor(Color.GRAY);
				g.fillOval(Realpx(t.pos.X) - 7, Realpx(t.pos.Y) - 7, 15, 15);
			}
		}
		g.setColor(Color.WHITE);
		g.drawString("workload : "+Double.toString(((double)tot) / mp.pnum * 100)+"%", 50, 260 * xwidth);
	}
	
	public void drawOffer() {
		Graphics2D g = (Graphics2D)mp.getGraphics();

		g.setStroke(new BasicStroke(3f));
//		g.setColor(Color.RED);
	//	g.drawLine(0 * xwidth, 0 * ywidth, mp.sizex * xwidth, mp.sizey * ywidth);
//		g.setColor(Color.BLACK);
		for (int i = 0; i < mp.offer.length; i++) 
			if (!(mp.offer[i] == null || mp.offer[i].state == 2)) {
				Offer t = mp.offer[i];
				g.setColor(Color.YELLOW);
				g.drawLine(Realpx(t.S.X), Realpx(t.S.Y), Realpx(t.T.X), Realpx(t.T.Y));
				g.setColor(Color.BLACK);
			}
	}
	
	/*void initTank() {
		tankImags = new Image[] {
				too.getImage("Images/plane.jpg"),
				too.getImage("Images/plane_busy.jpg"),
				too.getImage("Images/plane.jpg"),
				too.getImage("Images/tankR.gif"), };	
	}*/
	
	void initFrame() {
		mp.setSize(drawx, drawy); // set size
		mp.setLocation(280, 50); // set pos
		mp.setTitle("DDD");

		mp.addWindowListener(new WindowAdapter() { // set windows listen close
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				}); 
		mp.setResizable(false);
		mp.setBackground(Color.RED);
		mp.setVisible(true);
	}
	}
