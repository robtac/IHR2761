package org.usfirst.frc.team2761.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2761.robot.commands.Gears;
import org.usfirst.frc.team2761.robot.commands.RunClimber;
import org.usfirst.frc.team2761.robot.commands.RunPaddle;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterSet;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterXNeg;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterXPos;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterYNeg;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterYPos;
import org.usfirst.frc.team2761.robot.subsystems.Paddle;
import org.usfirst.frc.team2761.robot.commands.shooter.Shoot;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterAngleSet;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterCalibrate;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	
	
	public static Joystick leftJoystick = new Joystick(0);
	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
	
	public static Joystick rightJoystick = new Joystick(1);
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);
	
	public static Joystick xbox = new Joystick(2);
	public static JoystickButton buttonA = new JoystickButton(xbox, 1);
	public static JoystickButton buttonB = new JoystickButton(xbox, 2);
	public static JoystickButton buttonX = new JoystickButton(xbox, 3);
	public static JoystickButton bumperLeft = new JoystickButton(xbox, 5);
	public static JoystickButton bumperRight = new JoystickButton(xbox, 6);
//	public static JoystickAnalogButton xboxLeftTrigger = new JoystickAnalogButton(xbox, 2);
//	public static JoystickAnalogButton xboxRightTrigger = new JoystickAnalogButton(xbox, 3);
	
	static {
		OI.leftTrigger.whileHeld(new Gears());
		OI.rightTrigger.whileHeld(new Shoot());
		
		OI.buttonA.whileHeld(new RunPaddle());
		OI.buttonB.whileHeld(new RunClimber());
		OI.buttonX.whileHeld(new ShooterAngleSet());
		OI.bumperLeft.whileHeld(new ShooterYPos());
		OI.bumperRight.whileHeld(new ShooterYNeg());
		
		
		SmartDashboard.putNumber("P", RobotMap.shooterP);
		SmartDashboard.putNumber("I", RobotMap.shooterD);
		SmartDashboard.putNumber("D", RobotMap.shooterD);
		SmartDashboard.putNumber("ShooterSpeed", RobotMap.shooterSpeed);
		SmartDashboard.putData("Set PID Values", new ShooterSet());
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
