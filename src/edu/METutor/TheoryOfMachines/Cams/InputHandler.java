package edu.METutor.TheoryOfMachines.Cams;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, MouseMotionListener {

	DispAngleDiagram disp;
	public int MouseX, MouseY;
	private boolean[] buttons = new boolean[4];

	public InputHandler(DispAngleDiagram disp) {
		this.disp = disp;
		for (int i = 0; i < 4; i++) {
			buttons[i] = false;
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		MouseX = e.getX();
		MouseY = e.getY();
		Rectangle MouseR = new Rectangle(MouseX - 1, MouseY - 1, 2, 2);

		if (disp.AngleMark1ButtonBounds().intersects(MouseR)) {
			DispAngleDiagram.AngleMark1 = (MouseX - 50) / 2;
			DispAngleDiagram.AngleMark1 = clamp((int)DispAngleDiagram.AngleMark1, 3, (int)DispAngleDiagram.AngleMark2 - 5);
			buttons[0] = true;
		}
		if (disp.AngleMark2ButtonBounds().intersects(MouseR)) {
			DispAngleDiagram.AngleMark2 = (MouseX - 50) / 2;
			DispAngleDiagram.AngleMark2 = clamp((int)DispAngleDiagram.AngleMark2, (int)DispAngleDiagram.AngleMark1 + 6, (int)DispAngleDiagram.AngleMark3 - 5);
			buttons[1] = true;
		}
		if (disp.AngleMark3ButtonBounds().intersects(MouseR)) {
			DispAngleDiagram.AngleMark3 = (MouseX - 50) / 2;
			DispAngleDiagram.AngleMark3 = clamp((int)DispAngleDiagram.AngleMark3, (int)DispAngleDiagram.AngleMark2 + 6, 357);
			buttons[2] = true;
		}
		if (disp.getFollowerBounds().intersects(MouseR)) {
			ControlPanel.OffsetData = (MouseY - RenderCamProfile.centreY);
			ControlPanel.OffsetData = clamp((int)ControlPanel.OffsetData, -1 * (int)ControlPanel.BaseRadData, (int)ControlPanel.BaseRadData);
			buttons[3] = true;
		}

	}

	public void mouseReleased(MouseEvent e) {
		
		for (int i = 0; i < 4; i++) {
			buttons[i] = false;
		}

	}
 
	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

		MouseX = e.getX();
		MouseY = e.getY();

		if (buttons[0]) {
			DispAngleDiagram.AngleMark1 = (MouseX - 50) / 2;
			DispAngleDiagram.AngleMark1 = clamp((int)DispAngleDiagram.AngleMark1, 3, (int)DispAngleDiagram.AngleMark2 - 5);
		}
		else if (buttons[1]) {
			DispAngleDiagram.AngleMark2 = (MouseX - 50 ) / 2;
			DispAngleDiagram.AngleMark2 = clamp((int)DispAngleDiagram.AngleMark2, (int)DispAngleDiagram.AngleMark1 + 6, (int)DispAngleDiagram.AngleMark3 - 5);
		}
		else if (buttons[2]) {
			DispAngleDiagram.AngleMark3 = (MouseX - 50) / 2;
			DispAngleDiagram.AngleMark3 = clamp((int)DispAngleDiagram.AngleMark3, (int)DispAngleDiagram.AngleMark2 + 6, 357);
		}
		else if (buttons[3]){
			ControlPanel.OffsetData = (MouseY - RenderCamProfile.centreY);
			ControlPanel.OffsetData = clamp((int)ControlPanel.OffsetData, -1 * (int)ControlPanel.BaseRadData, (int)ControlPanel.BaseRadData);
		}
	
	}

	public void mouseMoved(MouseEvent e) {

	}
	public int clamp(int Angle, int MinAngle, int MaxAngle)
	{
		if(Angle < MinAngle) return MinAngle;
		else if(Angle > MaxAngle) return MaxAngle;
		else return Angle;
	}

}
