package cargame;

import java.awt.geom.Point2D;

public class RoamBehaviour implements Behaviour {
	protected long lastTargetUpdate;
	protected Point2D.Float target = new Point2D.Float();
	protected float width;
	protected float height;
	protected float x;
	protected float y;

	public RoamBehaviour(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update(Vehicle v, float dt) {
		// Update target if necessary
		long time = System.currentTimeMillis();
		if (time - lastTargetUpdate > 5000) {
			target.x = x + (float) Math.random() * width;
			target.y = y + (float) Math.random() * height;
			lastTargetUpdate = time;
		}
		// Steer vehicle towards target
		Point2D.Float p = v.getPosition();
		Point2D.Float desired_velocity = new Point2D.Float(target.x - p.x, target.y - p.y);
		Vehicle.scale(desired_velocity, v.getMaxSpeed());
		v.updateSteering(desired_velocity.x, desired_velocity.y);
	}
}