package edu.METutor.TheoryOfMachines.Cams;

public class UniformVelocityData {

	public double getDisplacement(double totalLift, double ascentAngle, double currentAngle) {

		double rise = totalLift * currentAngle / ascentAngle;
		return rise;

	}

	public double getVelocity(double totalLift, double ascentAngle, double rpm) {
		double angVel = 2 * Math.PI * rpm / 60;
		
		double vel = totalLift * angVel / ascentAngle;
		return vel;

	}
	public double getAcceleration(double totalLift, double ascentAngle, double currentAngle, double rpm) {
		
		return 0.0;
	}

	public double toDegrees(double rad) {
		double deg = rad / Math.PI * 180;
		return deg;
	}

	public double toRadians(double deg) {
		double rad = deg * Math.PI / 180;
		return rad;
	}

}
