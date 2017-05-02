package cargame;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameSurface extends JComponent implements MouseListener {
	protected ArrayList vehicles;

	public GameSurface() {
		vehicles = new ArrayList(10);
		addMouseListener(this); // For requesting input focus
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Dimension d = getSize();
		g2.setPaint(new Color(91, 91, 91));
		g2.fillRect(0, 0, d.width, d.height);
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle v = (Vehicle) vehicles.get(i);
			v.draw(g2);
		}
	}

	ArrayList getVehicles() {
		return vehicles;
	}

	public void mouseClicked(MouseEvent e) {
		// Custom components need to request the
		// input focus when they are clicked,
		// otherwise they won't receive keyboard events
		requestFocusInWindow();
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}
}