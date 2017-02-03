package org.usfirst.frc.team2761.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2761.robot.commands.Gears;
import org.usfirst.frc.team2761.robot.commands.TankDrive;
import org.usfirst.frc.team2761.robot.commands.setPID;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.Command;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	
	
	public static Joystick leftJoystick = new Joystick(0);
	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
	
	public static Joystick rightJoystick = new Joystick(1);
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);
	
	
	static {
		OI.leftTrigger.whileHeld(new Gears());
		
		
		SmartDashboard.putNumber("P", RobotMap.Pvalue);
		SmartDashboard.putNumber("I", RobotMap.Ivalue);
		SmartDashboard.putNumber("D", RobotMap.Dvalue);
		SmartDashboard.putData("Set PID Values", new setPID());
	}
	
	
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
