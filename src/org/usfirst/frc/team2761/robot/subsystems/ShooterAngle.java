package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterCalibrate;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterAngle extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static ShooterAngle instance = new ShooterAngle();
	
	public CANTalon shooterAngleX = new CANTalon(RobotMap.shooterAngleX);
	public CANTalon shooterAngleY = new CANTalon(RobotMap.shooterAngleY);
	
	public DigitalInput magHallX = new DigitalInput(RobotMap.magHallX);
	public DigitalInput magHallY = new DigitalInput(RobotMap.magHallY);
	
	public ShooterAngle () {
		System.out.println("ShooterAngle Constructor");
		
		shooterAngleX.changeControlMode(TalonControlMode.PercentVbus);
		System.out.println("TalonX control mode: " + shooterAngleX.getControlMode());
		shooterAngleX.setFeedbackDevice(FeedbackDevice.PulseWidth);
		shooterAngleX.setEncPosition(0);
		//shooterAngleX.setF(RobotMap.shooterAngleXF);
		//setPIDAngleX();
		
		shooterAngleY.changeControlMode(TalonControlMode.PercentVbus);
		System.out.println("TalonY control mode: " + shooterAngleY.getControlMode());
		shooterAngleY.setFeedbackDevice(FeedbackDevice.PulseWidth);
		shooterAngleY.setEncPosition(0);
		//shooterAngleY.setF(RobotMap.shooterAngleXF);
		//setPIDAngleY();
	}
	
	public boolean getMagHallX() {
		return magHallX.get();
	}
	
	public boolean getMagHallY() {
		return magHallY.get();
	}
	
	public void setPositionX(double pos) {
		shooterAngleX.set(pos);
	}
	
	public double getPositionX() {
		return shooterAngleX.getEncPosition();
	}
	
	public void setPIDAngleX() {
		shooterAngleX.setP(RobotMap.shooterAngleXP);
		shooterAngleX.setI(RobotMap.shooterAngleXI);
		shooterAngleX.setD(RobotMap.shooterAngleXD);
	}
	
	public void setPositionY(double pos) {
		shooterAngleY.set(pos);
	}
	
	public double getPositionY() {
		return shooterAngleX.getEncPosition();
	}
	
	public void setPIDAngleY() {
		shooterAngleY.setP(RobotMap.shooterAngleXP);
		shooterAngleY.setI(RobotMap.shooterAngleXI);
		shooterAngleY.setD(RobotMap.shooterAngleXD);
	}
	
	public void zeroX () {
		shooterAngleX.setEncPosition(0);
	}
	
	public void zeroY () {
		shooterAngleY.setEncPosition(0);
	}
	
	public static ShooterAngle getInstance () {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ShooterCalibrate());
    }
}

