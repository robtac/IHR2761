package org.usfirst.frc.team2761.Code2016;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static int backLeftDrive = 1;
	public static int frontLeftDrive = 2;
	public static int backRightDrive = 14;
	public static int frontRightDrive = 13;
	
    public static int shootMotor = 15;
    public static int shootAngleMotor = 11;
    public static int catapultMotor = 4;
    
    public static int rollerMotor = 3;
    public static int rollerLifter = 10;
    
    public static int climberMotor = 0;
    
    public static int flashLight = 5;
    
    // The CAN ID of the PCM the solenoid is attached to.
    public static int solenoidModule = 100;
    
    // The channel on the PCM to control (0..7).
    public static int solenoidChannel = 100;
    
    // Limit Switch port on the DIO
    public static double limitSwitchDIO = 0;
    
    public static double ANGLE_MOTOR_P = 1.100;
    public static double ANGLE_MOTOR_I = 0.001;
    public static double ANGLE_MOTOR_D = 0.600;       
}
