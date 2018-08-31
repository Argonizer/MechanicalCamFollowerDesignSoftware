package edu.METutor.TheoryOfMachines.Cams;

public class SimpleHarmonicData {

	public double getDisplacement(double totalLift, double ascentAngle, double currentAngle)
	{
		double beta = Math.PI * currentAngle / ascentAngle;
		
		double rise = (totalLift/2) * (1 - Math.cos(beta));
		return rise;
		
	}
	public double getVelocity(double totalLift, double ascentAngle, double currentAngle, double rpm)
	{
		double angVel = 2 * Math.PI * rpm / 60;
		double beta = Math.PI * currentAngle / ascentAngle;
		
		double vel = (totalLift/2) * (Math.PI * angVel / ascentAngle) * Math.sin(beta);
		return vel;
	
	}
	public double getAcceleration(double totalLift, double ascentAngle, double currentAngle, double rpm)
	{
		double angVel = 2 * Math.PI * rpm / 60;
		double beta = Math.PI * currentAngle / ascentAngle;
		
		double acc = (totalLift/2) * Math.pow((Math.PI * angVel * ascentAngle), 2) * Math.cos((beta));
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
