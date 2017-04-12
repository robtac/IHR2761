package org.usfirst.frc.team2761.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2761.robot.commands.*;
import org.usfirst.frc.team2761.robot.commands.auto.PivotPID;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterSet;
import org.usfirst.frc.team2761.robot.commands.shooter.Shoot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	
	// Initializes the joysticks and their buttons
	public static Joystick leftJoystick = new Joystick(0);
	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
	
	public static Joystick rightJoystick = new Joystick(1);
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);
	
	public static Joystick climberJoystick = new Joystick(3);
	public static JoystickButton climberTrigger = new JoystickButton(climberJoystick, 1);
	
	public static XboxController xbox = new XboxController(2);
	public static JoystickButton buttonA = new JoystickButton(xbox, 1);
	public static JoystickButton buttonB = new JoystickButton(xbox, 2);
	public static JoystickButton buttonX = new JoystickButton(xbox, 3);
	public static JoystickButton buttonY = new JoystickButton(xbox, 4);
	public static JoystickButton bumperLeft = new JoystickButton(xbox, 5);
	public static JoystickButton bumperRight = new JoystickButton(xbox, 6);
	
	static {
		// Dictates what the buttons do
		OI.leftTrigger.whenPressed(new Gears());
//		OI.rightTrigger.whileHeld(new Shoot());
//		OI.rightTrigger.whenPressed(new ChangeDriverInput(true));
//		OI.leftTrigger.whenPressed(new ChangeDriverInput(false));
//		OI.leftTrigger.whenPressed(new PivotPID(360));
		OI.rightTrigger.whenPressed(new ZeroEncoders());
		
		OI.climberTrigger.whileHeld(new RunClimberJoy());
		
		OI.xbox.a.whileHeld(new RunGearIntake());
		OI.xbox.x.whileHeld(new RunGearIntakeBack());
		
		OI.xbox.rt.whileHeld(new RunGearPivotForward());
		OI.xbox.lt.whileHeld(new RunGearPivotBackwards());
		
		OI.xbox.lb.whileHeld(new RunGearReleaseBack());
		OI.xbox.rb.whileHeld(new RunGearRelease());
		
//		OI.xbox.a.whileHeld(new RunIntake());
//		OI.xbox.x.whileHeld(new RunGearIntake());
		OI.xbox.b.whileHeld(new RunClimberFull());
//		OI.xbox.x.whileHeld(new RunClimberVariable());
		OI.xbox.y.whileHeld(new RunClimber());
		OI.xbox.start.whileHeld(new Shoot());
		OI.xbox.back.whileHeld(new Shoot());
//		OI.xbox.rb.whileHeld(new ChangeClimberSpeed(true));
//		OI.xbox.lb.whileHeld(new ChangeClimberSpeed(false));
//		OI.xbox.rb.whileHeld(new runPaddle());
//		OI.xbox.rb.whileHeld(new runPaddle());
		
		// Publishes data to the SmartDashboard
		SmartDashboard.putNumber("Shooter P", RobotMap.shooterP);
		SmartDashboard.putNumber("Shooter I", RobotMap.shooterD);
		SmartDashboard.putNumber("Shooter D", RobotMap.shooterD);
		SmartDashboard.putNumber("ShooterSpeed", RobotMap.shooterSpeed);
		SmartDashboard.putData("Set Shooter PID Values", new ShooterSet());
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
