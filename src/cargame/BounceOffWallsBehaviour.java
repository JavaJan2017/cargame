package cargame;

import java.awt.geom.Point2D;

public class BounceOffWallsBehaviour implements Behaviour {
	protected float x1;
	protected float y1;
	protected float x2;
	protected float y2;

	public BounceOffWallsBehaviour(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void update(Vehicle v, float dt) {
		Point2D.Float p = v.getPosition();
		Point2D.Float vel = v.getVelocity();
		if (p.x < x1) {
			p.x = x1;
			vel.x = -vel.x;
		}
		if (p.x > x2) {
			p.x = x2;
			vel.x = -vel.x;
		}
		if (p.y < y1) {
			p.y = y1;
			vel.y = -vel.y;
		}
		if (p.y > y2) {
			p.y = y2;
			vel.y = -vel.y;
		}
	}
}