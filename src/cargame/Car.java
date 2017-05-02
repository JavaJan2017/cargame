package cargame;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

public class Car extends Vehicle implements ImageObserver {
	protected Image img;
	protected float w2;
	protected float h2;

	public Car(String imageFileName) {
		ImageIcon iic = new ImageIcon(imageFileName);
		img = Transparency.makeColorTransparent(iic.getImage(), Color.black);
	}

	public void draw(Graphics2D g2) {
		AffineTransform saveXform = g2.getTransform();
		g2.translate(position.x, position.y);
		g2.rotate(Math.atan2(orientation.y, orientation.x));
		g2.drawImage(img, AffineTransform.getTranslateInstance(-img.getWidth(this) / 2.0, -img.getHeight(this) / 2.0),
				this);
		g2.setTransform(saveXform);
		/*
		 * g2.setPaint(Color.yellow); g2.drawLine((int)Math.floor(position.x),
		 * (int)Math.floor(position.y), (int)Math.floor(position.x + 50.0f *
		 * side.x), (int)Math.floor(position.y + 50.0f * side.y));
		 * g2.setPaint(Color.blue); g2.drawLine((int)Math.floor(position.x),
		 * (int)Math.floor(position.y), (int)Math.floor(position.x +
		 * velocity.x), (int)Math.floor(position.y + velocity.y));
		 * g2.setPaint(Color.white); g2.drawLine((int)Math.floor(position.x),
		 * (int)Math.floor(position.y), (int)Math.floor(position.x +
		 * steering.x), (int)Math.floor(position.y + steering.y));
		 */
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return true;
	}

	public boolean intersects(Vehicle v) {
		if (v instanceof Car) {
			Car c = (Car) v;
			Point2D.Float d = new Point2D.Float(position.x - c.position.x, position.y - c.position.y);
			if (length(d) < 25.0f) { // Should probably compute the radius from
										// the images instead...
				return true;
			}
		}
		return false;
	}
}