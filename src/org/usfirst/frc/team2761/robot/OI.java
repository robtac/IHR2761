package org.usfirst.frc.team2761.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2761.robot.commands.*;
import org.usfirst.frc.team2761.robot.commands.auto.*;
import org.usfirst.frc.team2761.robot.commands.climber.RunClimber;
import org.usfirst.frc.team2761.robot.commands.climber.RunClimberFull;
import org.usfirst.frc.team2761.robot.commands.climber.RunClimberJoy;
import org.usfirst.frc.team2761.robot.commands.drivetrain.ChangeDriverInput;
import org.usfirst.frc.team2761.robot.commands.drivetrain.ForwardEncoderPID;
import org.usfirst.frc.team2761.robot.commands.drivetrain.PivotGyroPID;
import org.usfirst.frc.team2761.robot.commands.drivetrain.BoilerAlignPID;
import org.usfirst.frc.team2761.robot.commands.gearintake.RunGearIntake;
import org.usfirst.frc.team2761.robot.commands.gearintake.RunGearIntakeBack;
import org.usfirst.frc.team2761.robot.commands.gearpivot.RunGearPivotBackwards;
import org.usfirst.frc.team2761.robot.commands.gearpivot.RunGearPivotForward;
import org.usfirst.frc.team2761.robot.commands.gearrelease.GearReleaseClose;
import org.usfirst.frc.team2761.robot.commands.gearrelease.GearReleaseOpen;
import org.usfirst.frc.team2761.robot.commands.paddle.RunPaddle;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterSet;
import org.usfirst.frc.team2761.robot.commands.shooter.Shoot;
import org.usfirst.frc.team2761.robot.commands.shooter.ShootPID;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	
	// Initializes the joysticks and their buttons
	public static Joystick leftJoystick = new Joystick(0);
	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
	public static JoystickButton leftThumbDown = new JoystickButton(leftJoystick, 2);
	public static JoystickButton leftThumbLeft = new JoystickButton(leftJoystick, 3);
	public static JoystickButton leftThumbRight = new JoystickButton(leftJoystick, 4);
	
	public static Joystick rightJoystick = new Joystick(1);
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);
	
	public static Joystick climberJoystick = new Joystick(3);
	public static JoystickButton climberTrigger = new JoystickButton(climberJoystick, 1);
	public static JoystickButton climberThumbDown = new JoystickButton(climberJoystick, 2);
	public static JoystickButton climberThumbRight = new JoystickButton(climberJoystick, 3);
	public static JoystickButton climberLeft1 = new JoystickButton(climberJoystick, 5);
	public static JoystickButton climberLeft2 = new JoystickButton(climberJoystick, 6);
	public static JoystickButton climberLeft3 = new JoystickButton(climberJoystick, 7);
	public static JoystickButton climberLeft4 = new JoystickButton(climberJoystick, 10);
	public static JoystickButton climberLeft5 = new JoystickButton(climberJoystick, 9);
	public static JoystickButton climberLeft6 = new JoystickButton(climberJoystick, 8);
	public static JoystickButton climberRight1 = new JoystickButton(climberJoystick, 13);
	public static JoystickButton climberRight2 = new JoystickButton(climberJoystick, 12);
	public static JoystickButton climberRight3 = new JoystickButton(climberJoystick, 11);
	public static JoystickButton climberRight4 = new JoystickButton(climberJoystick, 14);
	public static JoystickButton climberRight5 = new JoystickButton(climberJoystick, 15);
	public static JoystickButton climberRight6 = new JoystickButton(climberJoystick, 16);
	
	public static XboxController xbox = new XboxController(2);
	public static JoystickButton buttonA = new JoystickButton(xbox, 1);
	public static JoystickButton buttonB = new JoystickButton(xbox, 2);
	public static JoystickButton buttonX = new JoystickButton(xbox, 3);
	public static JoystickButton buttonY = new JoystickButton(xbox, 4);
	public static JoystickButton bumperLeft = new JoystickButton(xbox, 5);
	public static JoystickButton bumperRight = new JoystickButton(xbox, 6);
	
	static {
		// Dictates what the buttons do
		OI.leftThumbDown.whenPressed(new GearReleaseOpen());
		OI.leftThumbLeft.whenPressed(new GearReleaseClose());
		OI.leftThumbRight.whenPressed(new ForwardEncoderPID(-40));
		
		OI.climberThumbDown.whenPressed(new Gears());
		OI.climberThumbRight.whenPressed(new BoilerAlignPID());
//		OI.rightTrigger.whileHeld(new Shoot());
		OI.rightTrigger.whenPressed(new ChangeDriverInput(true));
		OI.leftTrigger.whenPressed(new ChangeDriverInput(false));
//		OI.leftTrigger.whenPressed(new PivotGyroPID(90));
//		OI.rightTrigger.whenPressed(new ZeroEncoders());
		
		OI.climberLeft1.whenPressed(new AutoBlueLeftShoot());
		OI.climberLeft2.whenPressed(new AutoBlueCenterShoot());
		OI.climberLeft3.whenPressed(new AutoBlueRight());
		OI.climberLeft4.whenPressed(new AutoRedLeft());
		OI.climberLeft5.whenPressed(new AutoRedCenterShoot());
		OI.climberLeft6.whenPressed(new AutoRedRightShoot());
		OI.climberRight1.whenPressed(new AutoBaseline());
		OI.climberRight2.whenPressed(new PivotGyroPID(-90));
		OI.climberRight3.whenPressed(new PivotGyroPID(90));
		OI.climberRight4.whenPressed(new ZeroGyro());
		OI.climberRight5.whenPressed(new ZeroEncoders());
		OI.climberRight6.whenPressed(new ForwardEncoderPID(50));
		
//		OI.climberTrigger.whileHeld(new RunClimberJoy());
		
		OI.xbox.a.whileHeld(new RunGearIntake());
		OI.xbox.x.whileHeld(new RunGearIntakeBack());
		
		OI.xbox.rt.whileHeld(new RunGearPivotForward());
		OI.xbox.lt.whileHeld(new RunGearPivotBackwards());
		
		OI.xbox.lb.whileHeld(new GearReleaseClose());
		OI.xbox.rb.whileHeld(new GearReleaseOpen());
		
//		OI.xbox.a.whileHeld(new RunIntake());
//		OI.xbox.x.whileHeld(new RunGearIntake());
		OI.xbox.b.whileHeld(new RunClimberFull());
//		OI.xbox.x.whileHeld(new RunClimberVariable());
		OI.xbox.y.whileHeld(new RunClimber());
		OI.xbox.start.whileHeld(new ShootPID());
		OI.xbox.back.whileHeld(new RunPaddle());
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
