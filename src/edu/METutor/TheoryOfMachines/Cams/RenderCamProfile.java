package edu.METutor.TheoryOfMachines.Cams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * @author Akshay Rawal
 *
 */
public class RenderCamProfile {

	public static double camViewBoxSize = 200, scale;
	public static LinkedList<Point> profilePoints = new LinkedList<Point>();
	public int[] xPoints = new int[720];
	public int[] yPoints = new int[720];
	public double[] LenArray = new double[720];

	private SimpleHarmonicData ShmData = new SimpleHarmonicData();
	private UniformAccelerationData UniAccData = new UniformAccelerationData();
	private CycloidalData CyclData = new CycloidalData();
	private UniformVelocityData UniVelData = new UniformVelocityData();
	private BufferedImage spring = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

	private DispAngleDiagram DispAng;
	private ControlPanel ContPan;

	private int FollowerWidth = 300;
	private int FollowerHeight = 24;
	public double camAngle = 0.0;
	public static double Speed = -6;
	public Point profilePoint = new Point();
	public static int centreX = (MainClass.width - 300) / 2 - 150, centreY = MainClass.height / 2 + 60;

	public RenderCamProfile(DispAngleDiagram DispAng, ControlPanel ContPan) {
		this.DispAng = DispAng;
		this.ContPan = ContPan;
	
	}

	public void addPoint(Point p) {
		profilePoints.add(p);
	}

	public void removePoint(Point p) {
		profilePoints.remove(p);
	}

	public void clearProfile() {
		int size = profilePoints.size();
		for (int i = 0; i < size; i++) {
			profilePoints.remove(0);
		}
	}

	public void CamAngleTick() {
		camAngle += Speed;
		camAngle = clampAngle(camAngle);
	}

	/////////////////////////////////////////////////////////////////////////////////////
	public void renderProfile(Graphics g) {
		if (ContPan.CamProfileConstructPermit) {
			createProfile();
			DispAng.render(g, LenArray, ContPan.StrokeData);
			double offsetAngle = getTheta(ControlPanel.OffsetData, ControlPanel.BaseRadData);

			// System.out.println("Profile Creation");

			Graphics2D offScreenGraphics, finalImgGraphics; 
			
			BufferedImage offscreen = null;
			BufferedImage finalImg = null;

			Dimension d = new Dimension((int) camViewBoxSize * 2, (int) camViewBoxSize * 2);

			offscreen = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
			finalImg = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);

			offScreenGraphics = offscreen.createGraphics();
			finalImgGraphics = finalImg.createGraphics();

			offScreenGraphics.setColor(new Color(255, 201, 14));
			offScreenGraphics.fillPolygon(xPoints, yPoints, xPoints.length);
			offScreenGraphics.setColor(Color.WHITE);
			offScreenGraphics.fillRect((int)(camViewBoxSize + ContPan.ShaftDiaData/2) - 10, (int)(camViewBoxSize - ContPan.ShaftDiaData/8),
					(int)ContPan.ShaftDiaData/6 + 10, (int)ContPan.ShaftDiaData/4);
			offScreenGraphics.setColor(Color.BLACK);
			offScreenGraphics.drawRect((int)(camViewBoxSize + ContPan.ShaftDiaData/2) - 10, (int)(camViewBoxSize - ContPan.ShaftDiaData/8),
					(int)ContPan.ShaftDiaData/6 + 10, (int)ContPan.ShaftDiaData/4);
			
			//if(!ContPan.CamRevolutionPermit){
		/*	 offScreenGraphics.drawLine((int)(camViewBoxSize),(int)(camViewBoxSize), specifyCoords((int)(camViewBoxSize), DispAng.AngleMark1).x,
			  specifyCoords((int)(camViewBoxSize),
			  DispAng.AngleMark1).y); 
			 
			 offScreenGraphics.drawLine((int)(camViewBoxSize), (int)(camViewBoxSize), specifyCoords(ContPan.BaseRadData + ContPan.StrokeData,
			  DispAng.AngleMark2).x, specifyCoords(ContPan.BaseRadData +
			  ContPan.StrokeData, DispAng.AngleMark2).y); 
			 
			 offScreenGraphics.drawLine((int)(camViewBoxSize),
					 (int)(camViewBoxSize), specifyCoords(ContPan.BaseRadData + ContPan.StrokeData,
			  DispAng.AngleMark3).x, specifyCoords(ContPan.BaseRadData +
			  ContPan.StrokeData, DispAng.AngleMark3).y); 
			 
			 offScreenGraphics.drawLine((int)(camViewBoxSize),
					 (int)(camViewBoxSize), specifyCoords(ContPan.BaseRadData + ContPan.StrokeData,
			  0).x, specifyCoords(ContPan.BaseRadData + ContPan.StrokeData,
			  0).y);}*/
			
			AffineTransform at = new AffineTransform();

			at.rotate(toRadians(camAngle), (int) camViewBoxSize, (int) camViewBoxSize);
			
			finalImgGraphics.drawImage(offscreen, at, null);
			offScreenGraphics.dispose();
			finalImgGraphics.dispose();
			// flip
			g.drawImage(finalImg, (int) (centreX - camViewBoxSize), (int) (centreY - camViewBoxSize), null);

			
			
			if(ContPan.FollowerChoice == "Knife-Edge")
			{
				g.setColor(Color.gray);
				g.fillArc((int) (centreX + LenArray[(359 - (int) camAngle) * 2] + (int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData) - 24), (int)(centreY - 24 + ControlPanel.OffsetData),
						48, 48, -30, 60);
				g.setColor(Color.BLACK);
				
				g.drawRect((int) (centreX + LenArray[(359 - (int) camAngle) * 2] +(int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)) + 20 - 1,
						(int)(centreY - FollowerHeight / 2 + ControlPanel.OffsetData) - 1, FollowerWidth + 1, FollowerHeight + 1);
				
				g.setColor(Color.gray);
				g.fillRect((int) (centreX + LenArray[(359 - (int) camAngle) * 2] +(int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)) + 20,
						(int)(centreY - FollowerHeight / 2 + ControlPanel.OffsetData), FollowerWidth, FollowerHeight);

				g.fillRect((int) (centreX + LenArray[(359 - (int) camAngle) * 2] + (int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)) + 60, (int)(centreY - 20 + ControlPanel.OffsetData),
						10, 40);
				g.setColor(Color.YELLOW);
				g.fillRect((int) (centreX + ControlPanel.BaseRadData + 230), (int)(centreY + ControlPanel.OffsetData - FollowerHeight/2), 50, FollowerHeight);
				DispAng.setFollowerBounds((int) (centreX + ControlPanel.BaseRadData + 230), (int)(centreY + ControlPanel.OffsetData - FollowerHeight/2), 50, FollowerHeight);
			}
			else if(ContPan.FollowerChoice == "Roller")
			{
				g.setColor(Color.gray);
				g.fillOval((int) (centreX + LenArray[(359 - (int) camAngle) * 2] + (int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)), (int)(centreY -24 + ControlPanel.OffsetData),
						48, 48);
				g.setColor(Color.BLACK);
				g.drawRect((int) (centreX + LenArray[(359 - (int) camAngle) * 2] +(int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)) + 20 - 1,
						(int)(centreY - FollowerHeight / 2 + ControlPanel.OffsetData) - 1, FollowerWidth + 1, FollowerHeight + 1);
				g.setColor(Color.gray);
				g.fillRect((int) (centreX + LenArray[(359 - (int) camAngle) * 2] +(int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)) + 20,
						(int)(centreY - FollowerHeight / 2 + ControlPanel.OffsetData), FollowerWidth, FollowerHeight);

				g.fillRect((int) (centreX + LenArray[(359 - (int) camAngle) * 2] + (int)getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData)) + 60, (int)(centreY - 20 + ControlPanel.OffsetData),
						10, 40);
				g.setColor(Color.YELLOW);
				g.fillRect((int) (centreX + ControlPanel.BaseRadData + 230), (int)(centreY + ControlPanel.OffsetData - FollowerHeight/2), 50, FollowerHeight);
				DispAng.setFollowerBounds((int) (centreX + ControlPanel.BaseRadData + 230), (int)(centreY + ControlPanel.OffsetData - FollowerHeight/2), 50, FollowerHeight);
				
			}
			
			
			g.setColor(new Color(217, 67, 26));
			g.fillRect((int) (centreX + ControlPanel.BaseRadData + 230), 200, 50, 190 + (int)ControlPanel.OffsetData);
			g.fillRect((int) (centreX + ControlPanel.BaseRadData + 230), centreY + 20 + (int)ControlPanel.OffsetData, 50, 190 - (int)ControlPanel.OffsetData);
			
			/* g.drawImage(spring, (int) (centreX + LenArray[(359 - (int) camAngle) * 2] + ContPan.BaseRadData) + 70,
					centreY - spring.getWidth() / 2, spring.getWidth(),
					(int) (centreX + ContPan.BaseRadData + (170 - LenArray[(359 - (int) camAngle) * 2])), null); **/

			g.setColor(Color.BLACK);
			g.drawOval((int) (centreX - ControlPanel.BaseRadData), (int) (centreY - ControlPanel.BaseRadData),
					(int) (2 * ControlPanel.BaseRadData), (int) (2 * ControlPanel.BaseRadData));
			g.drawOval((int) (centreX - ControlPanel.BaseRadData - ContPan.StrokeData),
					(int) (centreY - ControlPanel.BaseRadData - ContPan.StrokeData),
					(int) (2 * (ControlPanel.BaseRadData + ContPan.StrokeData)),
					(int) (2 * (ControlPanel.BaseRadData + ContPan.StrokeData)));
			
			g.setColor(Color.WHITE);
			g.fillOval(centreX - (int)ContPan.ShaftDiaData/2, centreY - (int)ContPan.ShaftDiaData/2, (int)ContPan.ShaftDiaData, (int)ContPan.ShaftDiaData);
			g.setColor(Color.BLACK);
			g.drawOval(centreX - (int)ContPan.ShaftDiaData/2, centreY - (int)ContPan.ShaftDiaData/2, (int)ContPan.ShaftDiaData, (int)ContPan.ShaftDiaData);
			
			g.setFont(new Font("Verdana", 1, 15));
			
			scale = camViewBoxSize/(ContPan.MainBaseDiaData/2 + ContPan.MainStrokeData);
			g.drawString("SCALE: 1 cm = " + new DecimalFormat("##.##").format(scale) + " pixels", 10, 650);
			
			g.drawLine((int) (centreX + ControlPanel.BaseRadData + 230), centreY, (int) (centreX + ControlPanel.BaseRadData + 400), centreY);
			g.drawLine((int) (centreX + ControlPanel.BaseRadData + 230), (int)(centreY + ContPan.OffsetData), (int) (centreX + ControlPanel.BaseRadData + 400), (int)(centreY + ContPan.OffsetData));
			g.drawLine((int) (centreX + ControlPanel.BaseRadData + 350), (int)(centreY), (int) (centreX + ControlPanel.BaseRadData + 350), (int)(centreY + ContPan.OffsetData));
			g.drawString(Integer.toString((int)(ContPan.OffsetData / camViewBoxSize * (ContPan.MainBaseDiaData/2 + ContPan.MainStrokeData))), (int) (centreX + ControlPanel.BaseRadData + 420), (int)(centreY + ContPan.OffsetData/2));
			//g.drawLine(centreX , centreY - (int)camViewBoxSize/2, centreX , centreY + (int)camViewBoxSize/2);
			
			if(!ControlPanel.CamRevolutionPermit){
			
			g.setColor(Color.WHITE);
			g.fillOval(centreX - (int)camViewBoxSize/20, centreY - (int)camViewBoxSize/20, (int)camViewBoxSize/10, (int)camViewBoxSize/10);
			}
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public double clampAngle(double Angle) {
		if (Angle > 360)
			return Angle - 360;
		else if (Angle < 0)
			return Angle + 360;
		return Angle;
	}

	public void createProfile() {
		clearProfile();
		for (double i = 0; i < DispAngleDiagram.AngleMark1; i += 0.5) {
			plotPoint(ContPan.DirectionChoicesArray.get(0), ContPan.MotionChoicesArray.get(0), i, 0,
					DispAngleDiagram.AngleMark1);
		}
		// if(ContPan.DirectionChoicesArray.get(0) ==
		// "Return"){reverseProfileForRegion(0, (int)DispAng.AngleMark1 * 2);}

		for (double i = DispAngleDiagram.AngleMark1; i < DispAngleDiagram.AngleMark2; i += 0.5) {
			plotPoint(ContPan.DirectionChoicesArray.get(1), ContPan.MotionChoicesArray.get(1), i, DispAngleDiagram.AngleMark1,
					DispAngleDiagram.AngleMark2);
		}
		// if(ContPan.DirectionChoicesArray.get(1) ==
		// "Return"){reverseProfileForRegion((int)DispAng.AngleMark1 * 2,
		// (int)DispAng.AngleMark2 * 2);}

		for (double i = DispAngleDiagram.AngleMark2; i < DispAngleDiagram.AngleMark3; i += 0.5) {
			plotPoint(ContPan.DirectionChoicesArray.get(2), ContPan.MotionChoicesArray.get(2), i, DispAngleDiagram.AngleMark2,
					DispAngleDiagram.AngleMark3);
		}
		// if(ContPan.DirectionChoicesArray.get(2) ==
		// "Return"){reverseProfileForRegion((int)DispAng.AngleMark2 * 2,
		// (int)DispAng.AngleMark3 * 2);}

		for (double i = DispAngleDiagram.AngleMark3; i < 360; i += 0.5) {
			plotPoint(ContPan.DirectionChoicesArray.get(3), ContPan.MotionChoicesArray.get(3), i, DispAngleDiagram.AngleMark3,
					360);
		}
		// if(ContPan.DirectionChoicesArray.get(3) ==
		// "Return"){reverseProfileForRegion((int)DispAng.AngleMark3 * 2,
		// (int)360);}

		makeXandYArray();
	}

	Point specifyCoords(double length, double angle) {
		Point camPt = new Point();
		camPt.x = (int) camViewBoxSize + (int) (length * Math.cos(toRadians(angle)));
		camPt.y = (int) camViewBoxSize + (int) (length * Math.sin(toRadians(angle)));
		return camPt;
	}

	public double toDegrees(double rad) {
		double deg = rad / Math.PI * 180;
		return deg;
	}

	public double toRadians(double deg) {
		double rad = deg * Math.PI / 180;
		return rad;
	}

	private void plotPoint(String direction, String motion, double currAngle, double startAngle, double endAngle) {
		
		double perp = getPerp(ControlPanel.OffsetData, ControlPanel.BaseRadData);
		
		if (motion == "Simple Harmonic") {
			double len = 0.0;
			if (direction == "Return") {
				len = ShmData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, endAngle - currAngle);
			} else if (direction == "Rise") {
				len = ShmData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, currAngle - startAngle);
			} else if (direction == "Dwell after Rise") {
				len = ContPan.StrokeData;
			} else if (direction == "Dwell after Return") {
				len = 0.0;
			}
			LenArray[(int) currAngle * 2] = len;
			len += perp;
			double newAngle = 0, newRad = 0;
			Point point = null;
				newRad = getHyp(ControlPanel.OffsetData, len);
				if(ControlPanel.OffsetData >= 0)
					newAngle = clampAngle((currAngle) + 90 - getTheta(ControlPanel.OffsetData, len));
				else
					newAngle = clampAngle((currAngle) - 90 + getTheta(Math.abs(ControlPanel.OffsetData), len));
				point = specifyCoords(newRad, newAngle);
			profilePoints.add(point);
			
		} else if (motion == "Uniform Acceleration") {
			double len = 0.0;
			if (direction == "Return") {
				len = UniAccData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, endAngle - currAngle, 300);
			} else if (direction == "Rise") {
				len = UniAccData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, currAngle - startAngle,
						300);
			} else if (direction == "Dwell after Rise") {
				len = ContPan.StrokeData;
			} else if (direction == "Dwell after Return") {
				len = 0.0;
			}
			LenArray[(int) currAngle * 2] = len;
//			len += ControlPanel.BaseRadData;
//			Point point = specifyCoords(len, currAngle);
			
			len += perp;
			double newRad = getHyp(ControlPanel.OffsetData, len);
			double newAngle;
			if(ControlPanel.OffsetData >= 0)
				newAngle = clampAngle((currAngle) + 90 - getTheta(ControlPanel.OffsetData, len));
			else
				newAngle = clampAngle((currAngle) - 90 + getTheta(Math.abs(ControlPanel.OffsetData), len));
			
			Point point = specifyCoords(newRad, newAngle);
			profilePoints.add(point);
			
		} else if (motion == "Uniform Velocity") {
			double len = 0.0;
			if (direction == "Return") {
				len = UniVelData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, endAngle - currAngle);
			} else if (direction == "Rise") {
				len = UniVelData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, currAngle - startAngle);
			} else if (direction == "Dwell after Rise") {
				len = ContPan.StrokeData;
			} else if (direction == "Dwell after Return") {
				len = 0.0;
			}
			LenArray[(int) currAngle * 2] = len;
			len += perp;
			double newRad = getHyp(ControlPanel.OffsetData, len);
			double newAngle;
			if(ControlPanel.OffsetData >= 0)
				newAngle = clampAngle((currAngle) + 90 - getTheta(ControlPanel.OffsetData, len));
			else
				newAngle = clampAngle((currAngle) - 90 + getTheta(Math.abs(ControlPanel.OffsetData), len));
			
			Point point = specifyCoords(newRad, newAngle);
			profilePoints.add(point);
		} else if (motion == "Cycloidal") {
			double len = 0.0;
			if (direction == "Return") {
				len = CyclData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, endAngle - currAngle);
			} else if (direction == "Rise") {
				len = CyclData.getDisplacement(ContPan.StrokeData, endAngle - startAngle, currAngle - startAngle);
			} else if (direction == "Dwell after Rise") {
				len = ContPan.StrokeData;
			} else if (direction == "Dwell after Return") {
				len = 0.0;
			}
			LenArray[(int) currAngle * 2] = len;
			len += perp;
			double newRad = getHyp(ControlPanel.OffsetData, len);
			double newAngle;
			if(ControlPanel.OffsetData >= 0)
				newAngle = clampAngle((currAngle) + 90 - getTheta(ControlPanel.OffsetData, len));
			else
				newAngle = clampAngle((currAngle) - 90 + getTheta(Math.abs(ControlPanel.OffsetData), len));
			
			Point point = specifyCoords(newRad, newAngle);
			profilePoints.add(point);
		}
	}

	private void makeXandYArray() {
		for (int i = 0; i < 720; i++) {
			Point point = profilePoints.get(i);
			xPoints[i] = point.x;
			yPoints[i] = point.y;
			// System.out.println(point);
		}
	}
	
	private double getTheta(double base, double perp){
		
		if (base == 0)
		{ return 90.0; }
		else{
		double theta = toDegrees(Math.atan(perp/base));
		return theta;}
	}
	private  double getThetaThruSin(double hyp, double perp){
		
		double theta = toDegrees(Math.asin(perp/hyp));
		return theta;
	}
	private  double getThetaThruCos(double hyp, double base){
		
		double theta = toDegrees(Math.acos(base/hyp));
		return theta;
	}
	private double getHyp(double base, double perp){
		
		double hyp = Math.sqrt(Math.pow(perp, 2) + Math.pow(base, 2));
		return hyp;
	}
	private double getPerp(double base, double hyp){
		
		double perp = Math.sqrt(Math.pow(hyp, 2) - Math.pow(base, 2));
		return perp;
	}
	

}
