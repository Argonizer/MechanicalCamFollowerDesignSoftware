package edu.METutor.TheoryOfMachines.Cams;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

// "Cam Profile Designer and Exporter"

// @Author - Akshay Rawal


public class MainClass extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int width = 1100;  //This is the app width in Pixels
	public static int height = 700;  //This is the app height in Pixels
	public static double baseCircleRad = 100; //To store default base circle radius of cam
	public static double stroke = 120; //To store length of cam displacement angle diagram
	private boolean running = false;//To set live/dead status of app
	private Thread thread;//Thread that runs indefinitely
	private Window window;//A JFrame extension that forms window for app
	private ControlPanel panel;//A JPanel extension that contains swing features necessary to modify cam profile
	private DispAngleDiagram disp;//A separate object that renders the cam Displacement Angle Diagram
	private RenderCamProfile rcp;//A separate object that draws the cam profile based on parameters applied
	private InputHandler input;// A separate object implementing mouse listeners handling input to the app
	
	public MainClass()
	{
		this.setPreferredSize(new Dimension(width - 300, height));// This sets the dimensions of the graphic space where cam is drawn
		panel = new ControlPanel();                    // Initialization of various objects incorporated into the main class
		window = new Window(this, panel);				//
		disp = new DispAngleDiagram(stroke);			//
		rcp = new RenderCamProfile(disp, panel);		//
		input = new InputHandler(disp);					//
		
		this.addMouseListener(input);					
		this.addMouseMotionListener(input);
	}

	public void run() {
		int fps = 0;
		long timer = 0;
		long previousTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double na = 1000000000.0 / amountOfTicks;
		double delta = 0.0;
		while (running) {
			//requestFocus();
			long currentTime = System.nanoTime();
			delta += (currentTime - previousTime) / na;
			previousTime = currentTime;
			if (delta >= 1) {
				tick();
				delta--;
				fps++;
				// previousTime = currentTime;
			}
			if (running)
				render();
			
			if (System.currentTimeMillis() - timer > 1000) {
				//System.out.println(fps + " fps");
				fps = 0;
				timer = System.currentTimeMillis();
			}
		}
		stop();
	}

		
	public synchronized void start()
	{
		if(running)return;
				running = true;
				thread = new Thread(this);
				thread.start();
	}
	public synchronized void stop()
	{
		if(!running)return;
		running = false;
		try{
			thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void tick()
	{
		if(ControlPanel.CamRevolutionPermit)
		 {
			rcp.CamAngleTick();
		 }
		
	}
	public void render()
	{
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width - 300, height);
		rcp.renderProfile(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		new MainClass();
	}

}
