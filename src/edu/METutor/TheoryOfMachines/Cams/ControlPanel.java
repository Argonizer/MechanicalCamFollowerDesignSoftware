package edu.METutor.TheoryOfMachines.Cams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eu.printingin3d.javascad.exceptions.IllegalValueException;
import eu.printingin3d.javascad.utils.SaveScadFiles;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	private JButton Final, Move, Export;
	private Rectangle rChoice1, rChoice2, rFin, rMove, rExp;
	private JComboBox<String> FollowerDirection;
	private JComboBox<String> FollowerMotion;
	private JComboBox<String> FollowerType;
	
	private JLabel FDir, FMot, Stroke, BaseDia, CamWidth, ShaftDia, Offset;
	private JTextField StrokeText, BaseDiaText, CamWidthText, ShaftDiaText, OffsetText;
	public LinkedList<String> DirectionChoicesArray = new LinkedList<String>();
	public LinkedList<String> MotionChoicesArray = new LinkedList<String>();

	double StrokeData, CamWidthData, ShaftDiaData;
	public static double OffsetData, BaseRadData;
	double MainStrokeData, MainBaseDiaData, MainCamWidthData, MainShaftDiaData, MainOffsetData;
	boolean CamProfileConstructPermit = false;
	public static boolean CamRevolutionPermit = false;
	String FollowerChoice;
	private LinkedList<Component[]> ComponentArrayList = new LinkedList<Component[]>();

	public ControlPanel() {
		this.setPreferredSize(new Dimension(270, MainClass.height));
		this.setMaximumSize(new Dimension(270, MainClass.height));
		this.setMaximumSize(new Dimension(270, MainClass.height));
		
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel(); // Created 5 separate panels to be
										// assembled into the mega panel
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		panel0.setPreferredSize(new Dimension(260, 40));
		panel0.setLayout(null);
		panel0.setBackground(new Color(153, 217, 234));
		
		panel1.setPreferredSize(new Dimension(260, 80));
		panel1.setLayout(null);
		panel1.setBackground(new Color(153, 217, 234));// Associated each panel
														// with a separate
														// background color

		panel2.setPreferredSize(new Dimension(260, 80));
		panel2.setLayout(null);
		panel2.setBackground(new Color(112, 146, 190));

		panel3.setPreferredSize(new Dimension(260, 80));
		panel3.setLayout(null);
		panel3.setBackground(new Color(118, 125, 218));

		panel4.setPreferredSize(new Dimension(260, 80));
		panel4.setLayout(null);
		panel4.setBackground(new Color(0, 162, 232));

		panel5.setPreferredSize(new Dimension(260, 210));
		panel5.setLayout(null);
		panel5.setBackground(new Color(153, 217, 234));
		
		String FollowerOptions[] = { "Knife-Edge"};
		FollowerType = new JComboBox<String>(FollowerOptions);
		rChoice1 = new Rectangle(130, 10, 120, 20);
		FollowerType.setBounds(rChoice1);
		panel0.add(FollowerType);
		
		FDir = new JLabel("Follower");
		FDir.setBounds(new Rectangle(10, 10, 80, 25));
		FDir.setFont(new Font("Verdana", 0, 15));
		panel0.add(FDir);


		drawControlsForSection(0, panel1, 1);
		drawControlsForSection(0, panel2, 2);
		drawControlsForSection(0, panel3, 3);
		drawControlsForSection(0, panel4, 4);

		add(panel0);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);

		Stroke = new JLabel("Stroke (in cm)");
		BaseDia = new JLabel("Base Dia (in cm)");
		CamWidth = new JLabel("Cam Width (in cm)");
		ShaftDia = new JLabel("Shaft Dia (in cm)");
		Offset = new JLabel("Offset (in cm)");
		
		Stroke.setBounds(new Rectangle(10, 10, 120, 25));
		BaseDia.setBounds(new Rectangle(10, 50, 130, 25));
		CamWidth.setBounds(new Rectangle(10, 90, 135, 25));
		ShaftDia.setBounds(new Rectangle(10, 130, 135, 25));
		Offset.setBounds(new Rectangle(10, 170, 135, 25));
		
		Stroke.setFont(new Font("Verdana", 0, 15));
		BaseDia.setFont(new Font("Verdana", 0, 15));
		CamWidth.setFont(new Font("Verdana", 0, 15));
		ShaftDia.setFont(new Font("Verdana", 0, 15));
		Offset.setFont(new Font("Verdana", 0, 15));

		StrokeText = new JTextField();
		StrokeText.setBounds(160, 10, 90, 20);

		BaseDiaText = new JTextField();
		BaseDiaText.setBounds(160, 50, 90, 20);
		
		CamWidthText = new JTextField();
		CamWidthText.setBounds(160, 90, 90, 20);
		
		ShaftDiaText = new JTextField();
		ShaftDiaText.setBounds(160, 130, 90, 20);
		
		OffsetText = new JTextField();
		OffsetText.setBounds(160, 170, 90, 20);

		StrokeText.setText("80.0");
		BaseDiaText.setText("120.0");
		CamWidthText.setText("40.0");
		ShaftDiaText.setText("30.0");
		OffsetText.setText("0.0");

		panel5.add(Stroke);
		panel5.add(StrokeText);
		panel5.add(BaseDia);
		panel5.add(BaseDiaText);
		panel5.add(CamWidth);
		panel5.add(CamWidthText);
		panel5.add(ShaftDia);
		panel5.add(ShaftDiaText);
		panel5.add(Offset);
		panel5.add(OffsetText);

		add(panel5);

		Component[] PanelComp1 = panel1.getComponents();
		Component[] PanelComp2 = panel2.getComponents();
		Component[] PanelComp3 = panel3.getComponents();
		Component[] PanelComp4 = panel4.getComponents();
		Component[] PanelComp5 = panel5.getComponents();

		ComponentArrayList.add(PanelComp1);
		ComponentArrayList.add(PanelComp2);
		ComponentArrayList.add(PanelComp3);
		ComponentArrayList.add(PanelComp4);
		ComponentArrayList.add(PanelComp5);

		Final = new JButton("Apply");
		rFin = new Rectangle(170, 610, 110, 30);
		Final.setBounds(rFin);
		add(Final);

		Move = new JButton("Toggle Move");
		rMove = new Rectangle(170, 670, 110, 30);
		Move.setBounds(rMove);
		add(Move);

		Export = new JButton("Export 3D");
		rExp = new Rectangle(170, 730, 220, 30);
		Export.setBounds(rExp);
		add(Export);

		Final.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//System.out.println("Apply");
				
				FollowerChoice = (String) FollowerType.getSelectedItem();

				while (!DirectionChoicesArray.isEmpty()) {
					DirectionChoicesArray.removeFirst();
				}
				while (!MotionChoicesArray.isEmpty()) {
					MotionChoicesArray.removeFirst();
				}

				for (int i = 0; i < ComponentArrayList.size(); i++) {

					Component[] currCompList = ComponentArrayList.get(i);
					for (int j = 0; j < currCompList.length; j++) {
						Component getComp = currCompList[j];
						if (getComp.getClass().equals(JComboBox.class)) {
							JComboBox<String> combo = (JComboBox<String>) getComp;
							String item = (String) combo.getSelectedItem();
							if (item == "Rise" || item == "Dwell after Rise" || item == "Return"
									|| item == "Dwell after Return") {
								DirectionChoicesArray.add(item);
							} else
								MotionChoicesArray.add(item);
						}

					}

				}
				MainStrokeData = Double.parseDouble(StrokeText.getText());
				MainBaseDiaData = Double.parseDouble(BaseDiaText.getText());
				MainCamWidthData = Double.parseDouble(CamWidthText.getText());
				MainShaftDiaData = Double.parseDouble(ShaftDiaText.getText());
				MainOffsetData = Double.parseDouble(OffsetText.getText());

				StrokeData = MainStrokeData / (MainStrokeData + MainBaseDiaData / 2) * RenderCamProfile.camViewBoxSize;
				BaseRadData = MainBaseDiaData / 2 / (MainStrokeData + MainBaseDiaData / 2)
						* RenderCamProfile.camViewBoxSize;
				CamWidthData = MainCamWidthData / (MainStrokeData + MainBaseDiaData / 2) * RenderCamProfile.camViewBoxSize;
				ShaftDiaData = MainShaftDiaData / (MainStrokeData + MainBaseDiaData / 2) * RenderCamProfile.camViewBoxSize;
				OffsetData = MainOffsetData / (MainStrokeData + MainBaseDiaData / 2) * RenderCamProfile.camViewBoxSize;

				CamProfileConstructPermit = true;

			}
		});

		Move.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (CamRevolutionPermit)
					CamRevolutionPermit = false;
				else
					CamRevolutionPermit = true;

			}
		});

		Export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame start = new JFrame();
				start.setTitle("Enter Expoeted File Name");
				start.setSize(new Dimension(500, 100));
				//start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				start.setLayout(new BorderLayout(10, 10));

				start.setAutoRequestFocus(true);
				start.setLocationRelativeTo(null);
				start.setResizable(false);
				start.setVisible(true);

				JButton Solid = new JButton("Export Solid Cam");
				Rectangle rSolid = new Rectangle(100, 50, 80, 30);
				Solid.setBounds(rSolid);
				start.add(Solid, BorderLayout.EAST);
				
				JButton Hollow = new JButton("Export Hollow Cam");
				Rectangle rHollow = new Rectangle(220, 50, 80, 30);
				Solid.setBounds(rHollow);
				start.add(Hollow, BorderLayout.WEST);

				JTextField filename = new JTextField(FileName());
				filename.setBounds(50, 10, 300, 20);
				start.add(filename, BorderLayout.NORTH);

				String finalName = filename.getText();
				Solid.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						try {
							new SaveScadFiles(new File("C:/temp")).addModel((finalName + "_Solid.scad"), new New3DSolidCam(RenderCamProfile.profilePoints, CamWidthData, ShaftDiaData))
									.saveScadFiles();
						} catch (IllegalValueException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						start.dispose();
					}
					
				});
				
				Hollow.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {
							new SaveScadFiles(new File("C:/temp")).addModel((finalName + "_Hollow.scad"), new New3DHollowCam(RenderCamProfile.profilePoints, CamWidthData, ShaftDiaData))
									.saveScadFiles();
						} catch (IllegalValueException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						start.dispose();
					}
					
				});
				
				

			}
		});

	}

	private void drawControlsForSection(int i, JPanel panel, int choice) {

		/**
		 * OK1 = new JButton("Okay"); OK2 = new JButton("Okay"); rOK1 = new
		 * Rectangle(210, 100 * i + 10, 50, 30); rOK2 = new Rectangle(210, 100 *
		 * i + 50 + 10, 50, 30); OK1.setBounds(rOK1); OK2.setBounds(rOK2);
		 * panel.add(OK1); panel.add(OK2);
		 */

		String options[] = { "Rise", "Dwell after Rise", "Return", "Dwell after Return" };
		FollowerDirection = new JComboBox<String>(options);
		rChoice1 = new Rectangle(130, 90 * i + 10, 120, 20);
		FollowerDirection.setBounds(rChoice1);
		FollowerDirection.setSelectedIndex(choice-1);
		panel.add(FollowerDirection);

		String options2[] = { "Simple Harmonic", "Uniform Velocity", "Uniform Acceleration", "Cycloidal" };
		FollowerMotion = new JComboBox<String>(options2);
		rChoice2 = new Rectangle(130, 90 * i + 40 + 10, 120, 20);
		FollowerMotion.setBounds(rChoice2);
		panel.add(FollowerMotion);

		FDir = new JLabel("Direction");
		FMot = new JLabel("Motion");
		FDir.setBounds(new Rectangle(10, 100 * i + 10, 80, 25));
		FMot.setBounds(new Rectangle(10, 100 * i + 40 + 10, 80, 25));
		FDir.setFont(new Font("Verdana", 0, 15));
		FMot.setFont(new Font("Verdana", 0, 15));
		panel.add(FDir);
		panel.add(FMot);

	}

	private boolean CamProfileConstructAllowed() {
		return CamProfileConstructPermit;
	}

	private String FileName() {
		String filename = "";
		for (int i = 0; i < DirectionChoicesArray.size(); i++) {

			if (DirectionChoicesArray.get(i) == "Rise" || DirectionChoicesArray.get(i) == "Return") {
				if (DirectionChoicesArray.get(i) == "Rise") {
					filename += "Ri-";
				} else {
					filename += "Ret-";
				}

				if (MotionChoicesArray.get(i) == "Simple Harmonic") {
					filename += "SHM-";
				} else if (MotionChoicesArray.get(i) == "Uniform Velocity") {
					filename += "UVM-";
				} else if (MotionChoicesArray.get(i) == "Uniform Acceleration") {
					filename += "UAM-";
				} else if (MotionChoicesArray.get(i) == "Cycloidal") {
					filename += "CYC-";
				}
			} else {
				if (DirectionChoicesArray.get(i) == "Dwell after Rise") {
					filename += "DUp-";
				} else if (DirectionChoicesArray.get(i) == "Dwell after Return") {
					filename += "DDown-";
				}
			}
			if (i == 0) {
				filename += Integer.toString((int) DispAngleDiagram.AngleMark1) + "_";
			} else if (i == 1) {
				filename += Integer.toString((int) (DispAngleDiagram.AngleMark2 - DispAngleDiagram.AngleMark1)) + "_";
			} else if (i == 2) {
				filename += Integer.toString((int) (DispAngleDiagram.AngleMark3 - DispAngleDiagram.AngleMark2)) + "_";
			} else if (i == 3) {
				filename += Integer.toString((int) (360 - DispAngleDiagram.AngleMark3)) + "_";
			}

		}
		filename += "BDia-" + Double.toString(MainBaseDiaData) + "_";
		filename += "Strk-" + Double.toString(MainStrokeData);
		return filename;
	}
}
