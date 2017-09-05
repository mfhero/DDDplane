import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;

import java.awt.*;

public class Addoffer implements MouseListener, MouseMotionListener,  MouseWheelListener {
	Modify m;
	int mousex, mousey;

	Addoffer(Modify m_)
	{
		m = m_;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("_________");
		Graphics2D g = (Graphics2D)m.map.getGraphics();
		int x = e.getX();
		int y = e.getY();
		g.setColor(Color.YELLOW);
		g.setStroke(new BasicStroke(3f));
		g.drawLine(mousex, mousey, x, y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("sdasdadad");
		m.Applyoffer(mousex, mousey, e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	 
	@Override
    public void mouseWheelMoved(MouseWheelEvent e) {
	/*	int maxsize = 128, minsize = 1;
        if (e.getWheelRotation() == 1 && pensize < maxsize) {
			pensize *= 1.3;
        }
        if (e.getWheelRotation() == -1 && pensize > minsize) {
			pensize /= 1.3;
        } */
    }
}
