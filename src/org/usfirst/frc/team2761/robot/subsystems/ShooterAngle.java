package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterAnglePrintValues;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterCalibrate;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterAngle extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static ShooterAngle instance = new ShooterAngle();
	
	public CANTalon shooterAngleX = new CANTalon(RobotMap.shooterAngleX);
	public CANTalon shooterAngleY = new CANTalon(RobotMap.shooterAngleY);
	
	public DigitalInput magHallX = new DigitalInput(RobotMap.LimitX);
	public DigitalInput magHallY = new DigitalInput(RobotMap.LimitY);
	
	// Initializes the motors for the shooter alignment system
	public ShooterAngle () {
		System.out.println("ShooterAngle Constructor");
		
		shooterAngleX.changeControlMode(TalonControlMode.Position);
		shooterAngleX.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		setPIDAngleX();
		shooterAngleX.setF(RobotMap.shooterAngleXF);
		shooterAngleX.setEncPosition(0);
		shooterAngleX.enableControl();
		
		shooterAngleY.changeControlMode(TalonControlMode.Position);
		shooterAngleY.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		setPIDAngleY();
		shooterAngleY.setF(RobotMap.shooterAngleYF);
		shooterAngleY.setEncPosition(0);
		shooterAngleY.enableControl();
	}
	
	// Gets the value of the MagHall X sensor
	public boolean getMagHallX() {
		return magHallX.get();
	}
	
	// Gets the value of the MagHall Y sensor
	public boolean getMagHallY() {
		return magHallY.get();
	}
	
	// Sets the x position of the shooter 
	public void setPositionX(double posDiff) {
		System.out.println(getPositionX());
		shooterAngleX.set(getPositionX() + posDiff);
	}
	
	// Returns the x position of the shooter
	public int getPositionX() {
		return shooterAngleX.getEncPosition();
	}
	
	// Sets the PIDs of the angle X talon
	public void setPIDAngleX() {
		shooterAngleX.setP(RobotMap.shooterAngleXP);
		shooterAngleX.setI(RobotMap.shooterAngleXI);
		shooterAngleX.setD(RobotMap.shooterAngleXD);
	}
	
	public void printPIDX () {
		System.out.println("P: " + shooterAngleX.getP());
		System.out.println("I: " + shooterAngleX.getI());
		System.out.println("D: " + shooterAngleX.getD());
	}
	
	// Sets the y position of the shooter
	public void setPositionY(double posDiff) {
		shooterAngleY.set(getPositionY() + posDiff);
	}
	
	// Returns the y position of the shooter
	public int getPositionY() {
		putAngleY(shooterAngleY.getEncPosition());
		return shooterAngleY.getEncPosition();
	}
	
	// Puts the Angle y value on the SmartDashboard
	public void putAngleY (int val) {
		SmartDashboard.putNumber("Y value", val);
	}
	
	// Sets the PIDs of the angle Y talon
	public void setPIDAngleY() {
		shooterAngleY.setP(RobotMap.shooterAngleYP);
		shooterAngleY.setI(RobotMap.shooterAngleYI);
		shooterAngleY.setD(RobotMap.shooterAngleYD);
	}
	
	// Zeroes the encoder of the angle x talon
	public void zeroX () {
		shooterAngleX.setEncPosition(0);
	}
	
	// Zeroes the encoder of the angle y talon
	public void zeroY () {
		shooterAngleY.setEncPosition(0);
	}
	
	// Returns the instance of this subsystem
	public static ShooterAngle getInstance () {
		return instance;
	}
	
	public void printValues() {
    	SmartDashboard.putNumber("Angle X Position", getPositionX());
    	SmartDashboard.putNumber("Angle Y Position", getPositionY());
	}
	
	// Sets the default running command for this subsystem
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ShooterCalibrate());
    	setDefaultCommand(new ShooterAnglePrintValues());
    }
}

