package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.OI;
import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.drivetrain.TankDrive;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static DriveTrain instance = new DriveTrain();
	Gyro gyro;
	public double gyroZeroValue;
	
	public double circumference = 4 * 2 * Math.PI;
	
	CANTalon frontLeftDrive = new CANTalon (RobotMap.frontLeftDrive);
	CANTalon backLeftDrive = new CANTalon(RobotMap.backLeftDrive);
	CANTalon frontRightDrive = new CANTalon (RobotMap.frontRightDrive);
	CANTalon backRightDrive = new CANTalon (RobotMap.backRightDrive);
	RobotDrive driveTrain = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	
	// Initializes the drive train
	public DriveTrain() {
		Logger.println("Initialized DriveTrain subsystem");
		
		SmartDashboard.putNumber("Vision P", RobotMap.defaultVisionP);
		SmartDashboard.putNumber("Vision I", RobotMap.defaultVisionI);
		SmartDashboard.putNumber("Vision D", RobotMap.defaultVisionD);
		
		zeroEncoders();
		
		gyro = new AnalogGyro(0);
		gyro.calibrate();
		setInput(true);
		gyroZeroValue = 0;
		
		backLeftDrive.configEncoderCodesPerRev(450);
		backLeftDrive.reverseSensor(false);
		frontRightDrive.configEncoderCodesPerRev(450);
		frontRightDrive.reverseSensor(true);
		
		driveTrain.setSafetyEnabled(false);
		
		frontLeftDrive.enableBrakeMode(true);
		backLeftDrive.enableBrakeMode(true);
		frontRightDrive.enableBrakeMode(true);
		backRightDrive.enableBrakeMode(true);
	}
	
	// Returns the instance of this subsystem
	public static DriveTrain getInstance()
	{
		return instance;
	}
	
	// Drives the robot based on joystick input
	public void tankDrive() 
	{
//		System.out.println("Gyro: " + gyro.getAngle());
//		System.out.println("Left Encoder: " + encoderLeft.getDistance());
//		System.out.println("Right Encoder: " + encoderRight.getDistance());
		Boolean driverInput = getInput();
		if (driverInput) {
			// Gear system forwards
			driveTrain.tankDrive(OI.rightJoystick.getY() * 1, OI.leftJoystick.getY() * 1);
		} else {
			// Intake forwards
			driveTrain.tankDrive(OI.leftJoystick.getY() * -1, OI.rightJoystick.getY() * -1);
		}
	}
	
	public void setInput (Boolean input) {
		SmartDashboard.putBoolean("DriverInput", input);
	}
	
	public Boolean getInput () {
		return SmartDashboard.getBoolean("DriverInput", true);
	}
	
	public double getAngle () {
		return gyro.getAngle() - gyroZeroValue;
	}
	
	public void zeroGyro() {
		gyroZeroValue = gyro.getAngle();
	}
	
	public boolean moveAngle (double targetAngle, double initialAngle) {
		double currentAngle = gyro.getAngle();
		if (targetAngle - initialAngle > 0) {
			if (targetAngle - currentAngle < 0) {
				stop();
				return true;
			} else {
				drive(0.1, -0.1);
				return false;
			}
		} else {
			if (targetAngle - currentAngle > 0) {
				stop();
				return true;
			} else {
				drive(-0.1, 0.1);
				return false;
			}
		}
	}
	
	// Drives the robot based on input speed
	public void drive(double leftSpeed, double rightSpeed)
	{
		setControlMode(TalonControlMode.PercentVbus);

		frontLeftDrive.set(leftSpeed);
		backLeftDrive.set(leftSpeed);
		frontRightDrive.set(rightSpeed);
		backRightDrive.set(rightSpeed);
	}
	
	public void pivot (double speed) {
		drive(speed, speed);
	}
	
	public void pivot (double speed, double additive) {
		double leftSpeed = speed + additive;
		double rightSpeed = speed;
//		System.out.println("Left speed: " + leftSpeed + " --- Right speed: " + rightSpeed + " --- Speed: " + speed + " --- Additive: " + additive + " -- LE: " + getLeftDistance() + " -- RE: " + getRightDistance());
		drive(-leftSpeed, -rightSpeed);
	}
	
	public void moveTurn (double speed, double additive) {
		if (additive > 0) {
			drive(-speed, speed + additive);
		} else {
			drive(-speed + additive, speed);
		}
	}
	
	// Drives the robot at full speed forward
	public void drive()
	{
		setControlMode(TalonControlMode.PercentVbus);
		
		frontLeftDrive.set(1);
		backLeftDrive.set(1);
		frontRightDrive.set(1);
		backRightDrive.set(1);
	}
	
	public void forward (double speed, double additive) {
		double leftSpeed = speed + additive;
		double rightSpeed = speed;
		drive(-leftSpeed, rightSpeed);
	}
	
	public void setPosition (double distance) {
		setControlMode(TalonControlMode.Position);
		double rotations = distance / circumference;
		
		frontLeftDrive.set(rotations);
		backLeftDrive.set(rotations);
		frontRightDrive.set(rotations);
		backRightDrive.set(rotations);
	}
	
	public void setControlMode (TalonControlMode controlMode) {
		frontLeftDrive.changeControlMode(controlMode);
		backLeftDrive.changeControlMode(controlMode);
		frontRightDrive.changeControlMode(controlMode);
		backRightDrive.changeControlMode(controlMode);
	}
	
	public void stop () {
		frontLeftDrive.set(0);
		backLeftDrive.set(0);
		frontRightDrive.set(0);
		backRightDrive.set(0);
	}
	
	public double getLeftDistance () {
		return backLeftDrive.getPosition() * 4 * Math.PI;
	}
	
	public double getRightDistance () {
		return frontRightDrive.getPosition() * 4 * Math.PI;
	}
	
	public double getDistance () {
		double leftDistance = getLeftDistance();
		double rightDistance = getRightDistance();
		return (leftDistance + rightDistance) / 2;
	}
	
	public void zeroEncoders () {
		backLeftDrive.setEncPosition(0);
		frontRightDrive.setEncPosition(0);
	}
	
	// Sets the default running command
    public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive());
    }
}
