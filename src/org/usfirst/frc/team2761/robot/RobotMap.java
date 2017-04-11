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
	public static int backLeftDrive = 13; // Has encoder
	public static int frontRightDrive = 14; // Has encoder
	public static int backRightDrive = 15;
	
	public static int climb1 = 0;
	public static int shooterMotor1 = 2;
	public static int shooterMotor2 = 1;
	public static int climb2 = 3;
	public static int intakeBelt = 4;
	public static int ballAgitator = 5;
	
	public static int gearPivot = 6;
	public static int gearRoller = 4;
	
	public static int gearRelease = 7;
	
	public static int shooterAngleX = 30; // Deprecated
	public static int shooterAngleY = 31; // Deprecated
	
	// Sets the IDs of the MagHall sensors
	public static int LimitX1 = 2;
	public static int LimitX2 = 3;
	public static int LimitY1 = 0;
	public static int LimitY2 = 1;
	
	// Default PID values for the shooter
	public static double shooterF = 0.0346;
	public static double shooterP = 0.08525;
    public static double shooterI = 0.001;
    public static double shooterD = 0.8525;
    public static double shooterSpeed = 4300;
//    public static double shooterSpeed = 91;
    
    public static double defaultVisionP = 0.005;
    public static double defaultVisionI = 0.000;
    public static double defaultVisionD = 0.000;
    
    public static double defaultPivotP = 1.000;
    public static double defaultPivotI = 0.000;
    public static double defaultPivotD = 0.000;
    
    public static boolean hasEncoders = true;
    public static boolean hasGyro = false;
    public static boolean hasVision = true;
    
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
