package edu.METutor.TheoryOfMachines.Cams;

public class MilliMetrePixelConversion {

	double Conversion;
	public double MilliToPixels(double mm)
	{
		double pixels = (Conversion * mm) ;
		return pixels;
	}
	public double PixelsToMilli(double pix)
	{
		double mm = pix / Conversion ;
		return mm;
	}
	public void setConversion(double Conversion)
	{
		this.Conversion = Conversion;
	}
	public double getConversion()
	{
		return Conversion;
	}
}

