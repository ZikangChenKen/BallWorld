package hw06.model.interact.strategy;

import java.awt.Point;
import java.awt.geom.Point2D;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interact strategy for elastic collision balls.
 *
 */
public class ExactBounceInteractStrategy implements IInteractStrategy {

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBallCmd interactWithThen(IBall source, IBall target, IDispatcher<IBallCmd> dispatcher) {

		double distance = source.getLocation().distance(target.getLocation());
		double minNonCollideDist = source.getRadius() + target.getRadius() + 1;
		double reducedMass = reducedMass(source.getMass(), target.getMass());

		Point2D.Double unitVector = calcUnitVec(source.getLocation(), target.getLocation(), distance);
		Point2D.Double impul = impulse(unitVector, source.getVelocity(), target.getVelocity(), reducedMass);
		Point nudgeVector = calcNudgeVec(unitVector, minNonCollideDist, distance);

		return new IBallCmd() {
			@Override
			public void apply(IBall other, IDispatcher<IBallCmd> disp) {
				updateVelocity(source, source.getMass(), impul);
				Point newSourceLoc = new Point(source.getLocation().x + nudgeVector.x,
						source.getLocation().y + nudgeVector.y);
				source.setLocation(newSourceLoc);
			}
		};

	}

	/**
	 * Returns the reduced mass of the two balls (m1*m2)/(m1+m2) Gives correct
	 * result if one of the balls has infinite mass.
	 * 
	 * @param mSource
	 *            Mass of the source ball
	 * @param mTarget
	 *            Mass of the target ball
	 * @return The reduced mass of the two balls
	 */
	protected double reducedMass(double mSource, double mTarget) {
		if (mSource == Double.POSITIVE_INFINITY)
			return mTarget;
		if (mTarget == Double.POSITIVE_INFINITY)
			return mSource;
		else
			return (mSource * mTarget) / (mSource + mTarget);
	}

	/**
	 * Calculate the unit vector (normalized vector) from the location of the source ball to the location of the target ball.
	 * @param lSource Location of the source ball
	 * @param lTarget Location of the target ball
	 * @param distance Distance from the source ball to the target ball
	 * @return A double-precision vector (point)
	 */
	Point2D.Double calcUnitVec(Point lSource, Point lTarget, double distance) {
		// Get the normalized vector, from source to target
		double nx = ((double) (lTarget.x - lSource.x)) / distance;
		double ny = ((double) (lTarget.y - lSource.y)) / distance;

		return new Point2D.Double(nx, ny);
	}

	/**
	 * Calculates the impulse (change in momentum) of the collision in the
	 * direction from the source to the target. This method calculates the
	 * impulse on the source ball. The impulse on the target ball is the
	 * negative of the result. The change in velocity of the source ball is the
	 * impulse divided by the source's mass The change in velocity of the target
	 * ball is the negative of the impulse divided by the target's mass.
	 * 
	 * @param normalVec 
	 *            The unit vector (normalized vector) from the location of the source ball to the location of the target ball.
	 * @param vSource
	 *            Velocity of the source ball
	 * @param vTarget
	 *            Velocity of the target ball
	 * @param reducedMass
	 *            Reduced mass of the two balls
	 * @return The value of the collision's impulse
	 */
	protected Point2D.Double impulse(Point2D.Double normalVec, Point vSource, Point vTarget, double reducedMass) {
		// Get the coordinates of the unit vector from source to target
		double nx = normalVec.getX(); //((double) (lTarget.x - lSource.x)) / distance;
		double ny = normalVec.getY(); //((double) (lTarget.y - lSource.y)) / distance;

		// delta velocity (speed, actually) in normal direction (perpendicular to the plane of interaction, 
		// i.e. in the direction from the source location to the target location
		double dvn = (vTarget.x - vSource.x) * nx + (vTarget.y - vSource.y) * ny;

		// Impulse is the change in speed times twice the reduced mass in the normal direction
		return new Point2D.Double(2.0 * reducedMass * dvn * nx, 2.0 * reducedMass * dvn * ny);

	}

	/**
	 * The multiplicative factor to increase the separation distance to insure that the two balls
	 * are beyond collision distance
	 */
	private static final double NudgeFactor = 1.1;

	/**
	 * Calculate the vector to add to the source ball's location to "nudge" it out of the way of the target ball.
	 * @param normalVec  The unit vector (normalized vector) from the location of the source ball to the location of the target ball.
	 * @param minSeparation The minimum allowed non-colliding separation between the centers of the balls = maximum allowed colliding separation.
	 * @param distance The actual distance between the centers of the balls.
	 * @return A Point object which is the amount to "nudge" the source ball away from the target ball.
	 */
	Point calcNudgeVec(Point2D.Double normalVec, double minSeparation, double distance) {
		// The minimum allowed separation(sum of the ball radii) minus the actual separation(distance between ball centers). Should be a
		// positive value.  This is the amount of overlap of the balls as measured along the line between their centers.
		double deltaR = minSeparation - distance;

		// Calc the amount to move the source ball beyond collision range of the target ball, along
		// the normal direction.
		return new Point((int) Math.ceil(-normalVec.getX() * deltaR * NudgeFactor),
				(int) Math.ceil(-normalVec.getY() * deltaR * NudgeFactor));

	}

	/**
	 * Updates velocity of a ball, given an impulse vector. The change in velocity is the 
	 * impulse divided by the ball's mass. 
	 * 
	 * @param aBall
	 *            The ball whose velocity needs to be modified by the impulse
	 * @param mass 
	 *            The "mass" of the ball
	 * @param impulseVec 
	 *            The impulse vector for the ball
	 */
	protected void updateVelocity(IBall aBall, double mass, Point2D.Double impulseVec) {
		aBall.getVelocity().translate((int) Math.round(impulseVec.getX() / mass),
				(int) Math.round(impulseVec.getY() / mass));
	}

}
