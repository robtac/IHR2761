package org.usfirst.frc.team2761.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */



public class RobotMap {
	
	
	public static int backLeftDrive = 1;
	public static int frontLeftDrive = 2;
	public static int backRightDrive = 14;
	public static int frontRightDrive = 13;
	public static int shooterMotor = 12;
	
	public static double shooterF = 0.0319;
	public static double shooterP = 1.100;
    public static double shooterI = 0.001;
    public static double shooterD = 0.600; 
    public static double shooterSpeed = 4000;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
