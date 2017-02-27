package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.OI;
import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.TankDrive;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static DriveTrain instance = new DriveTrain();
	
	CANTalon frontLeftDrive = new CANTalon (RobotMap.frontLeftDrive);
	CANTalon backLeftDrive = new CANTalon(RobotMap.backLeftDrive);
	CANTalon frontRightDrive = new CANTalon (RobotMap.frontRightDrive);
	CANTalon backRightDrive = new CANTalon (RobotMap.backRightDrive);
	RobotDrive driveTrain = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	
	// Initializes the drive train
	public DriveTrain() {
//		frontLeftDrive.configMaxOutputVoltage(10);
//		frontRightDrive.configMaxOutputVoltage(10);
//		backLeftDrive.configMaxOutputVoltage(10);
//		backRightDrive.configMaxOutputVoltage(10);

		driveTrain.setSafetyEnabled(false);
		
	}
	
	// Returns the instance of this subsystem
	public static DriveTrain getInstance()
	{
		return instance;
	}
	
	// Drives the robot based on joystick input
	public void tankDrive() 
	{
		driveTrain.tankDrive(OI.leftJoystick.getY() * -1, OI.rightJoystick.getY() * -1);
	}
	
	// Drives the robot based on input speed
	public void drive(double leftSpeed, double rightSpeed)
	{
		//driveTrain.tankDrive(leftSpeed, rightSpeed);

		frontLeftDrive.set(leftSpeed);
		backLeftDrive.set(leftSpeed);
		frontRightDrive.set(rightSpeed);
		backRightDrive.set(rightSpeed);
	}
	
	// Drives the robot at full speed forward
	public void drive()
	{
		frontLeftDrive.set(1);
		backLeftDrive.set(1);
		frontRightDrive.set(1);
		backRightDrive.set(1);
	}
	
	// Sets the default running command
    public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive());
    }
}
