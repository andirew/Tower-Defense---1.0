import java.awt.event.*;
import java.awt.*;
public class KeyHandel implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}


	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Screen.store.click(e.getButton());
		System.out.println("ln");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Screen.mse = new Point((e.getX()) + ((Frame.size.width - Screen.myWidth)/2) ,(e.getY()) + ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth)/2));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Screen.mse = new Point((e.getX()) - ((Frame.size.width - Screen.myWidth)/2) ,(e.getY()) - ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth)/2));
		
	}
	
}
