package edu.METutor.TheoryOfMachines.Cams;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eu.printingin3d.javascad.coords.Coords3d;
import eu.printingin3d.javascad.coords.Dims3d;
import eu.printingin3d.javascad.coords2d.Coords2d;
import eu.printingin3d.javascad.models.Abstract3dModel;
import eu.printingin3d.javascad.models.Cube;
import eu.printingin3d.javascad.models.Cylinder;
import eu.printingin3d.javascad.models.LinearExtrude;
import eu.printingin3d.javascad.models2d.Abstract2dModel;
import eu.printingin3d.javascad.models2d.Polygon;
import eu.printingin3d.javascad.tranzitions.Difference;
import eu.printingin3d.javascad.tranzitions.Scale;
import eu.printingin3d.javascad.tranzitions.Translate;
import eu.printingin3d.javascad.tranzitions.Union;

public class New3DHollowCam extends Union{
	
	

	public New3DHollowCam(LinkedList<Point> profile, double width, double ShaftDia){
		super(makeModel(profile, width, ShaftDia));
	}
		
	public static List<Abstract3dModel> makeModel(LinkedList<Point> profile, double width, double ShaftDia){
		
		List <Abstract3dModel> model = new ArrayList<>();
		
		Polygon base = getBase(profile);
		Abstract2dModel baseModel = base;
		Abstract3dModel camModel = new LinearExtrude(baseModel, width, null);
		Abstract3dModel camBase = new LinearExtrude(baseModel, width/20, null).move(Coords3d.zOnly(-width/2));
		Difference diffCamBase = new Difference(camBase, new Cylinder(width * 2, ShaftDia/2 * 1.16));
		Difference CamWall = new Difference(camModel, new Scale(camModel, new Coords3d(0.95, 0.95, 1.1)));
		@SuppressWarnings("deprecation")
		Difference bush = new Difference(new Cylinder(width, ShaftDia/2 * 1.161), new Cylinder(width + 1, ShaftDia/2));
		@SuppressWarnings("deprecation")
		Difference diff = new Difference(CamWall, new Cylinder(width + 1, ShaftDia/2));
		
//		model.add(diffCamBase);
//		model.add(diff);
//		model.add(bush);
	
		Scale scale1 = new Scale(diff, new Coords3d(0.01/RenderCamProfile.scale, 0.01/RenderCamProfile.scale, 0.01/RenderCamProfile.scale));
		Scale scale2 = new Scale(diffCamBase, new Coords3d(0.01/RenderCamProfile.scale, 0.01/RenderCamProfile.scale, 0.01/RenderCamProfile.scale));
		Scale scale3 = new Scale(bush, new Coords3d(0.01/RenderCamProfile.scale, 0.01/RenderCamProfile.scale, 0.01/RenderCamProfile.scale));
		
		//model.add(new Cube(5.0));
		model.add(scale1);
		model.add(scale2);
		model.add(scale3);
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