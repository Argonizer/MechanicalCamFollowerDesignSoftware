package edu.METutor.TheoryOfMachines.Cams;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import eu.printingin3d.javascad.coords.Coords3d;
import eu.printingin3d.javascad.coords2d.Coords2d;
import eu.printingin3d.javascad.models.Abstract3dModel;
import eu.printingin3d.javascad.models.Cube;
import eu.printingin3d.javascad.models.Cylinder;
import eu.printingin3d.javascad.models2d.Abstract2dModel;
import eu.printingin3d.javascad.models2d.Polygon;
import eu.printingin3d.javascad.tranzitions.Difference;
import eu.printingin3d.javascad.tranzitions.Scale;
import eu.printingin3d.javascad.tranzitions.Union;
import eu.printingin3d.javascad.models.LinearExtrude;

public class New3DSolidCam extends Union{
	
	
	public New3DSolidCam(LinkedList<Point> profile, double width, double ShaftDia){
		super(makeModel(profile, width, ShaftDia));
	}
		
	public static List<Abstract3dModel> makeModel(LinkedList<Point> profile, double width, double ShaftDia){
		
		List <Abstract3dModel> model = new ArrayList<>();

		Polygon base = getBase(profile);
		Abstract2dModel baseModel = base;
		Abstract3dModel camModel = new LinearExtrude(baseModel, width, null);
		Difference diff = new Difference(camModel, new Cylinder(width + 1, ShaftDia/2));
		Scale scale = new Scale(diff, new Coords3d(1/RenderCamProfile.scale, 1/RenderCamProfile.scale, 1/RenderCamProfile.scale));
		//model.add(diff);
		model.add(scale);
		//model.add(new Cube(5.0));
		
		return model;
		
	}

	public static Polygon getBase(LinkedList<Point> profile){
		
		System.out.println(profile.size());
		List<Coords2d> Base = new ArrayList<Coords2d>();
		for(int i = 0; i < profile.size(); i++)
		{
			Point pt = profile.get(i);
			Coords2d pt2 = new Coords2d(pt.x - RenderCamProfile.camViewBoxSize, pt.y - RenderCamProfile.camViewBoxSize);
			Base.add(pt2);
		}
		System.out.println(Base.size());
		return new Polygon(Base);
	}
	
}