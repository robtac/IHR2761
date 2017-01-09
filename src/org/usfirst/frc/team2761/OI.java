package org.usfirst.frc.team2761;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2761.commands.AutoDriveForward;
import org.usfirst.frc.team2761.commands.Outtake;
import org.usfirst.frc.team2761.commands.SetPID;
import org.usfirst.frc.team2761.commands.climbers.LiftClimbers;
import org.usfirst.frc.team2761.commands.climbers.LowerClimbers;
import org.usfirst.frc.team2761.commands.roller.Intake;
import org.usfirst.frc.team2761.commands.roller.LiftRoller;
import org.usfirst.frc.team2761.commands.roller.LowerRoller;
import org.usfirst.frc.team2761.commands.shooter.Shoot;
import org.usfirst.frc.team2761.commands.shooter.ShooterAngle90;
import org.usfirst.frc.team2761.commands.shooter.ShooterAngleDown;
import org.usfirst.frc.team2761.commands.shooter.ShooterAngleUp;
import org.usfirst.frc.team2761.commands.shooter.ShooterCatapultDown;
import org.usfirst.frc.team2761.commands.shooter.ShooterCatapultUp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
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
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public static Joystick leftJoystick = new Joystick(0);
	public static Joystick rightJoystick = new Joystick(1);
	public static Joystick xbox = new Joystick(2);
	
	public static JoystickButton aButtonXbox = new JoystickButton(xbox, 1);
	public static JoystickButton bButtonXbox = new JoystickButton(xbox, 2);
	public static JoystickButton xButtonXbox = new JoystickButton(xbox, 3);
	public static JoystickButton yButtonXbox = new JoystickButton(xbox, 4);
	public static JoystickButton leftBumperXbox = new JoystickButton(xbox, 5);
	public static JoystickButton rightBumperXbox = new JoystickButton(xbox, 6);
	public static JoystickButton startButtonXbox = new JoystickButton(xbox, 8);	
	
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);
	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
	
	public static int xboxLeftStickX = 1;
	public static int xboxLeftStickY = 2;
	public static int xboxRightStickX = 4;
	public static int xboxRightStickY = 5;
	
	public static DigitalInput limitSwitchDownCatapult = new DigitalInput(0);
	public static DigitalInput limitSwitchUpCatapult = new DigitalInput(1);
	
	public static JoystickAnalogButton xboxRightStick = new JoystickAnalogButton(xbox, 5);
	public static JoystickAnalogButton xboxRightStickYUp = new JoystickAnalogButton(xbox, 5, -0.5);
	public static JoystickAnalogButton xboxRightStickYDown = new JoystickAnalogButton(xbox, 5, 0.5);
	
	public static JoystickAnalogButton xboxLeftTrigger = new JoystickAnalogButton(xbox, 2);
	public static JoystickAnalogButton xboxRightTrigger = new JoystickAnalogButton(xbox, 3);

	
	public static JoystickAnalogButton xboxLeftStickYUp = new JoystickAnalogButton(xbox, 1, -0.5);
	public static JoystickAnalogButton xboxLeftStickYDown = new JoystickAnalogButton(xbox, 1, 0.5);
	
	public static JoystickButton smash = new JoystickButton(leftJoystick, 10);
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
   
	
	public OI() {
		SmartDashboard.putNumber("P", RobotMap.ANGLE_MOTOR_P);
		SmartDashboard.putNumber("I", RobotMap.ANGLE_MOTOR_I);
		SmartDashboard.putNumber("D", RobotMap.ANGLE_MOTOR_D);
		SmartDashboard.putData("Set PID Values", new SetPID());
		
//    	OI.aButtonXbox.whileHeld(new Intake());
//    	OI.yButtonXbox.whileHeld(new Shoot());
//    	OI.leftBumperXbox.whenPressed(new LowerClimbers());
//    	OI.rightBumperXbox.whenPressed(new LiftClimbers());
//    	OI.startButtonXbox.whileHeld(new Climb());
    	
//    	SmartDashboard.putData(new Intake());
//    	SmartDashboard.putData(new Shoot());
	}
	
		
	
	static{                                                                                                                                                                                                                      
		OI.startButtonXbox.whileHeld(new Shoot());
		
		OI.rightBumperXbox.whileHeld(new Intake());
		OI.leftBumperXbox.whileHeld(new Outtake());
		
//		OI.xboxRightStickYUp.whileHeld(new ShooterCatapultUp());
//		OI.xboxRightStickYDown.whileHeld(new ShooterCatapultDown());
//
//		OI.xboxLeftStickYUp.whileHeld(new LiftRoller());
//		OI.xboxLeftStickYDown.whileHeld(new LowerRoller());
		
		//OI.xboxRightStickYUp.whileHeld(new LiftRoller());
		OI.xboxRightStickYUp.whileHeld(new LiftRoller());
		OI.xboxRightStickYDown.whileHeld(new LowerRoller());
		OI.xboxLeftStickYUp.whileHeld(new ShooterCatapultUp());
		OI.xboxLeftStickYDown.whileHeld(new ShooterCatapultDown());
		
		OI.bButtonXbox.whileHeld(new ShooterAngleDown());
		OI.xboxLeftTrigger.whileHeld(new ShooterAngleUp());
//		OI.xButtonXbox.whileHeld(new ShooterToTowerBase());
		
		OI.xboxRightTrigger.whileHeld(new ShooterAngle90());
		
		OI.yButtonXbox.whileHeld(new LiftClimbers());
		OI.aButtonXbox.whileHeld(new LowerClimbers());
		
		// TODO AT MADERA
		// 1. Check all subsystem functionality (Web Interface)
		// 2. Tune PID values for shooter angle and test encoder positions (90 degrees, 45 degrees, 0 degrees)
		// 3. Check Auto (Time is at 5 seconds and power is at 75%)
		// 3. Limit Switch for Catapult? + Hard stop for lifting the roller and HARD STOP FOR THE SHOOTER

	}
}

