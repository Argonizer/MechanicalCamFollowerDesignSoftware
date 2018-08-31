package edu.METutor.TheoryOfMachines.Cams;

public class CycloidalData {
	
	public double getDisplacement(double totalLift, double ascentAngle, double currentAngle)
	{
		double beta = Math.PI * currentAngle / ascentAngle;
		
		double rise = (totalLift / Math.PI) * (beta - (0.5 * Math.sin(2 * beta)));
		return rise;
		
	}
	public double getVelocity(double totalLift, double ascentAngle, double currentAngle, double rpm)
	{
		double angVel = 2 * Math.PI * rpm / 60;
		double beta = Math.PI * currentAngle / ascentAngle;
		
		double vel = (totalLift * angVel / ascentAngle) * (1 - Math.cos(2 * beta));
		return vel;
	
	}
	public double getAcceleration(double totalLift, double ascentAngle, double currentAngle, double rpm)
	{
		double angVel = 2 * Math.PI * rpm / 60;
		double beta = Math.PI * currentAngle / ascentAngle;
		
		double acc = 2 * totalLift * Math.PI * Math.pow(angVel / ascentAngle, 2) * Math.sin(2 * beta);
		return acc;
	}
	
	public double toDegrees(double rad)
	{
		double deg = rad / Math.PI * 180;
		return deg;
	}
	public double toRadians(double deg)
	{
		double rad = deg * Math.PI / 180;
		return rad;
	}

}
