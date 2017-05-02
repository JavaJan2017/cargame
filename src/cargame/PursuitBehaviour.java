package cargame;
import java.awt.geom.Point2D;
public class PursuitBehaviour implements Behaviour {
protected Vehicle target;
PursuitBehaviour(Vehicle v) {
target = v;
}
public void update(Vehicle v, float dt) {
// Steer vehicle towards target
Point2D.Float p = v.getPosition();
Point2D.Float tp = target.getPosition();
Point2D.Float desired_velocity = new Point2D.Float(tp.x - p.x , tp.y - p.y);
Vehicle.scale(desired_velocity, v.getMaxSpeed());
v.updateSteering(desired_velocity.x, desired_velocity.y);
}
}