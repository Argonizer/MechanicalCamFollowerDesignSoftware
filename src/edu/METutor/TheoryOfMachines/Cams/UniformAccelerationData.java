package edu.METutor.TheoryOfMachines.Cams;

public class UniformAccelerationData {
	
	public double getDisplacement(double totalLift, double ascentAngle, double currentAngle, double rpm)
	{
		double acc = getAcceleration(totalLift, ascentAngle, rpm);
		double angVel = 2 * Math.PI * rpm / 60;
		double time = currentAngle/angVel;
		
		double rise = 0.25 * acc * Math.pow(time, 2);
		
		return rise;
		
	}
	public double getVelocity(double totalLift, double ascentAngle, double currentAngle, double rpm)
	{
		double angVel = 2 * Math.PI * rpm / 60;
		double vel = 4 * totalLift * angVel * currentAngle / Math.pow(ascentAngle, 2);
		
		return vel;
	
	}
	public double getAcceleration(double totalLift, double ascentAngle, double rpm)
	{
		double angVel = 2 * Math.PI * rpm / 60;
		double acc = 4 * totalLift * Math.pow((angVel / ascentAngle) , 2);
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
