package edu.METutor.TheoryOfMachines.Cams;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class DispAngleDiagram {
	
	
	public static double AngleMark1 = 0.0;
	public static double AngleMark2 = 0.0;
	public static double AngleMark3 = 0.0;
	public double stroke = 0.0;
	public int FolButtonX, FolButtonY, FolButtonW, FolButtonH;
	private int panelXCentre = (MainClass.width - 300)/2;
	private int panelYCentre = (MainClass.height)/2;
	private int[] XPoints = new int[722];
	private int[] YPoints = new int[722];
	
	public DispAngleDiagram(double stroke)
	{
		this.stroke = stroke;
		AngleMark1 = 90;
		AngleMark2 = 180;
		AngleMark3 = 270;
	}
	
	public void render(Graphics g, double[] len, double maxLift)
	{
		
		for(int i = 1; i <= len.length; i+=2)
		{
			XPoints[i] = 50 + i;
			XPoints[i+1] = 50 + i;
			YPoints[i] = (int)(10 + (stroke - (len[i-1] / maxLift * stroke)));
			YPoints[i+1] = (int)(10 + (stroke - (len[i-1] / maxLift * stroke)));
		}
		XPoints[0] = 50;
		XPoints[len.length+1] = 50 + 720;
		YPoints[0] = YPoints[len.length+1] = (int)(10 + stroke);
		
		g.setColor(new Color(170, 216, 46));
		g.fillPolygon(XPoints, YPoints, len.length + 2);
		
		g.setColor(Color.BLACK);
		g.drawRect(50, 10, 720, (int)stroke);
		g.drawRect(49, 9, 722, (int)stroke + 2);
		g.drawRect(48, 8, 724, (int)stroke + 4);

		g.setColor(Color.BLUE);
		g.fillRect(50 + 2 * (int)AngleMark1 - 1, 10,  3, (int)stroke);
		g.fillRect(50 + 2 * (int)AngleMark2 - 1, 10,  3, (int)stroke);
		g.fillRect(50 + 2 * (int)AngleMark3 - 1, 10,  3, (int)stroke);
		
		g.fillRect(50 + 2 * (int)AngleMark1 - 5, 10 + (int)stroke,  11, 20);
		g.fillRect(50 + 2 * (int)AngleMark2 - 5, 10 + (int)stroke,  11, 20);
		g.fillRect(50 + 2 * (int)AngleMark3 - 5, 10 + (int)stroke,  11, 20);
		
		g.setFont(new Font("Verdana", 1, 14));
		g.drawString(Integer.toString((int)AngleMark1), 50 + 2 * (int)AngleMark1 - 5, 50 + (int)stroke);
		g.drawString(Integer.toString((int)AngleMark2), 50 + 2 * (int)AngleMark2 - 5, 50 + (int)stroke);
		g.drawString(Integer.toString((int)AngleMark3), 50 + 2 * (int)AngleMark3 - 5, 50 + (int)stroke);
		
		
		g.setColor(Color.BLACK);
		//g.fillRect(800, 0, 5, MainClass.height);
		
		
	}
	public Rectangle AngleMark1ButtonBounds()
	{
		return new Rectangle(50 + 2 * (int)AngleMark1 - 5, 10 + (int)stroke,  11, 20);
	}
	public Rectangle AngleMark2ButtonBounds()
	{
		return new Rectangle(50 + 2 * (int)AngleMark2 - 5, 10 + (int)stroke,  11, 20);
	}
	public Rectangle AngleMark3ButtonBounds()
	{
		return new Rectangle(50 + 2 * (int)AngleMark3 - 5, 10 + (int)stroke,  11, 20);
	}
	public Rectangle getFollowerBounds()
	{
		return new Rectangle(FolButtonX, FolButtonY, FolButtonW, FolButtonH);
	}
	public void setFollowerBounds(int x, int y, int w, int h)
	{
		FolButtonX = x;
		FolButtonY = y;
		FolButtonW = w;
		FolButtonH = h;
	}

}
