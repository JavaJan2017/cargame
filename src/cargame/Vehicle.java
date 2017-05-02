package cargame;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public abstract class Vehicle {
	// Member variables
	protected Point2D.Float position = new Point2D.Float();
	protected Point2D.Float orientation = new Point2D.Float();
	protected Point2D.Float side = new Point2D.Float();
	protected Point2D.Float velocity = new Point2D.Float();
	protected Point2D.Float steering = new Point2D.Float();
	protected float mass;
	protected float maxSpeed;
	protected float maxSteering;
	// List of behaviours
	protected ArrayList behaviours = new ArrayList(10);

	// Getters and setters
	public Point2D.Float getPosition() {
		return position;
	}

	public void updatePosition(Point2D.Float p) {
		position.x = p.x;
		position.y = p.y;
	}

	public void updatePosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public Point2D.Float getOrientation() {
		return orientation;
	}

	public void updateOrientation(Point2D.Float o) {
		orientation.x = o.x;
		orientation.y = o.y;
	}

	public void updateOrientation(float x, float y) {
		orientation.x = x;
		orientation.y = y;
	}

	public Point2D.Float getSideVector() {
		return side;
	}

	public Point2D.Float getVelocity() {
		return velocity;
	}

	public void updateVelocity(Point2D.Float v) {
		velocity.x = v.x;
		velocity.y = v.y;
	}

	public void updateVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}

	public Point2D.Float getSteering() {
		return steering;
	}

	public void updateSteering(Point2D.Float s) {
		steering.x = s.x;
		steering.y = s.y;
	}

	public void updateSteering(float x, float y) {
		steering.x = x;
		steering.y = y;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float m) {
		mass = m;
	}

	public void setMaxSpeed(float m) {
		maxSpeed = m;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSteering(float f) {
		maxSteering = f;
	}

	public float getMaxSteering() {
		return maxSteering;
	}

	public void addBehaviour(Behaviour b) {
		behaviours.add(b);
	}

	// A few utility methods for working with vectors
	static float length(Point2D.Float v) {
		return (float) Math.sqrt((v.x * v.x) + (v.y * v.y));
	}

	static public void scale(Point2D.Float v, float newLength) {
		float l = length(v);
		v.x *= newLength / l;
		v.y *= newLength / l;
	}

	// Update this vehicle
	public void update(float dt) {
		for (int i = 0; i < behaviours.size(); i++) {
			((Behaviour) behaviours.get(i)).update(this, dt);
		}
		// Truncate the length of the desired steering force vector
		Point2D.Float force = new Point2D.Float(steering.x, steering.y);
		float l = length(force);
		if (l > maxSteering) {
			force.x *= maxSteering / l;
			force.y *= maxSteering / l;
		}
		// Newton's second law: steering force = mass * accelerataion
		Point2D.Float acc = new Point2D.Float(force.x / mass, force.y / mass);
		// Update velocity vector using Euler's method
		// and truncate its length to the maximum allowed
		velocity.x += dt * acc.x;
		velocity.y += dt * acc.y;
		l = length(velocity);
		if (l > maxSpeed) {
			velocity.x *= maxSpeed / l;
			velocity.y *= maxSpeed / l;
		}
		// Update position using Euler's method
		position.x += dt * velocity.x;
		position.y += dt * velocity.y;
		// Set orientation to equal the velocity vector
		// and set the side vector accordingly
		l = length(velocity);
		if (l > 0.0f) {
			orientation.x = velocity.x / l;
			orientation.y = velocity.y / l;
			side.x = -orientation.y;
			side.y = orientation.x;
		}
}

// Abstract methods for drawing and intersection testing
public abstract void draw(Graphics2D g2);

public abstract boolean intersects(Vehicle v);
}