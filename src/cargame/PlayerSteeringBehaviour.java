package cargame;

import java.awt.event.*;
import java.awt.geom.Point2D;

public class PlayerSteeringBehaviour implements Behaviour, KeyListener {
	protected boolean steering = false;
	protected float direction = 1.0f;

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) { // Cursor left
			steering = true;
			direction = -1.0f;
		}
		if (e.getKeyCode() == 39) { // Cursor right
			steering = true;
			direction = 1.0f;
		}
	}

	public void keyReleased(KeyEvent e) {
		steering = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void update(Vehicle v, float dt) {
		if (steering) {
			Point2D.Float side = v.getSideVector();
			Point2D.Float desired_velocity = new Point2D.Float(side.x * direction, side.y * direction);
			Vehicle.scale(desired_velocity, v.getMaxSteering());
			v.updateSteering(desired_velocity.x, desired_velocity.y);
		} else {
			v.updateSteering(v.getVelocity());
		}
	}
}