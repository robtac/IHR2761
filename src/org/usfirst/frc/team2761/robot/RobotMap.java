package org.usfirst.frc.team2761.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */



public class RobotMap {
	
	// Sets the IDs of the talons
	public static int frontLeftDrive = 12;
	public static int backLeftDrive = 13;
	public static int frontRightDrive = 14;
	public static int backRightDrive = 15;
	
	public static int climb1 = 0;
	public static int shooterMotor1 = 1;
	public static int shooterMotor2 = 2;
	public static int climb2 = 3;
	public static int intakeBelt = 4;
	public static int ballAgitator = 5;
	
	public static int gearPivot = 7;
	public static int gearRoller = 6;
	
	public static int shooterAngleX = 30; // Deprecated
	public static int shooterAngleY = 31; // Deprecated
	
	// Sets the IDs of the MagHall sensors
	public static int LimitX1 = 2;
	public static int LimitX2 = 3;
	public static int LimitY1 = 0;
	public static int LimitY2 = 1;
	
	// Default PID values for the shooter
	public static double shooterF = 0.025;
	public static double shooterP = 0.005;
    public static double shooterI = 0.000;
    public static double shooterD = 0.020;
//    public static double shooterSpeed = 4300;
    public static double shooterSpeed = 91;
    
    // Default PID values for the shooter angle talons
    public static double shooterAngleXF = 0.025;
	public static double shooterAngleXP = 1.000;
    public static double shooterAngleXI = 0.000;
    public static double shooterAngleXD = 0.000;
    public static double shooterAngleXShift = 1024;
    
    public static double shooterAngleYF = 0.025;
	public static double shooterAngleYP = 0.020;
    public static double shooterAngleYI = 0.000;
    public static double shooterAngleYD = 0.000;
    public static double shooterAngleYShift = 1024;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
