package org.usfirst.frc.team2761.subsystems;

import org.usfirst.frc.team2761.OI;
import org.usfirst.frc.team2761.RobotMap;
import org.usfirst.frc.team2761.commands.TankDrive;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem
{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static DriveTrain instance = new DriveTrain();
	
	CANTalon frontLeftDrive = new CANTalon (RobotMap.frontLeftDrive);
	CANTalon backLeftDrive = new CANTalon(RobotMap.backLeftDrive);
	CANTalon frontRightDrive = new CANTalon (RobotMap.frontRightDrive);
	CANTalon backRightDrive = new CANTalon (RobotMap.backRightDrive);
	RobotDrive driveTrain = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	
	public DriveTrain() {
//		frontLeftDrive.configMaxOutputVoltage(10);
//		frontRightDrive.configMaxOutputVoltage(10);
//		backLeftDrive.configMaxOutputVoltage(10);
//		backRightDrive.configMaxOutputVoltage(10);

	}
	
	public static DriveTrain getInstance()
	{
		return instance;
	}
	
	public void tankDrive() 
	{
		driveTrain.tankDrive(OI.leftJoystick.getY() * -1, OI.rightJoystick.getY() * -1);
	}
	
	public void drive(double leftSpeed, double rightSpeed)
	{
//		driveTrain.tankDrive(leftSpeed, rightSpeed);

		frontLeftDrive.set(leftSpeed);
		backLeftDrive.set(leftSpeed);
		frontRightDrive.set(rightSpeed);
		backRightDrive.set(rightSpeed);
	}
	
	public void drive()
	{
		frontLeftDrive.set(1);
		backLeftDrive.set(1);
		frontRightDrive.set(1);
		backRightDrive.set(1);
	}
	
    public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive());
    }
}