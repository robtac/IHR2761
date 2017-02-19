package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterSet;
import org.usfirst.frc.team2761.robot.commands.shooter.Shoot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	public static final double ENCODERVELOCITY = 60.0 * 10.0 / 4096.0;
	
	private static Shooter instance = new Shooter();
	
	public CANTalon shooterMotor = new CANTalon(RobotMap.shooterMotor, 1);
	public CANTalon shooterAngleX = new CANTalon(RobotMap.shooterAngleX);
	
	public DigitalInput magHallX1 = new DigitalInput(RobotMap.magHallX1);
	public DigitalInput magHallX2 = new DigitalInput(RobotMap.magHallX2);
	public DigitalInput magHallY = new DigitalInput(RobotMap.magHallY);
	
	public Shooter() {
		shooterMotor.changeControlMode(TalonControlMode.Speed);
		shooterMotor.setFeedbackDevice(FeedbackDevice.PulseWidth);
    	shooterMotor.setEncPosition(0);
    	shooterMotor.setF(RobotMap.shooterF);
		setPIDShoot();
		
		shooterAngleX.changeControlMode(TalonControlMode.Position);
		shooterAngleX.setFeedbackDevice(FeedbackDevice.PulseWidth);
		shooterAngleX.setEncPosition(0);
		shooterAngleX.setF(RobotMap.shooterAngleXF);
		setPIDAngleX();
	}
	
	public boolean getMagHallX1() {
		return magHallX1.get();
	}
	
	public boolean getMagHallX2() {
		return magHallX2.get();
	}
	
	public boolean getMagHallY() {
		return magHallY.get();
	}
	
	public void printSpeed() {
		double motorVelocity = (int) (shooterMotor.getPulseWidthVelocity() * ENCODERVELOCITY);
    	System.out.println("Is executing at: " + motorVelocity + " rpm");
    	motorVelocity = shooterMotor.getSpeed();
    	SmartDashboard.putNumber("Actual Shooter Speed", motorVelocity);
    	SmartDashboard.putNumber("Actual Shooter Speed Number", motorVelocity);
	}
	
	public void setSpeed(double speed) {
		shooterMotor.set(speed);
	}

	public void stop() {
		shooterMotor.set(0);
		shooterMotor.disable();
		shooterMotor.enable();
	}
	
	public void setPIDShoot() {
		shooterMotor.setP(RobotMap.shooterP);
		shooterMotor.setI(RobotMap.shooterI);
		shooterMotor.setD(RobotMap.shooterD);
	}
	
	public void setPIDAngleX() {
		shooterAngleX.setP(RobotMap.shooterAngleXP);
		shooterAngleX.setI(RobotMap.shooterAngleXI);
		shooterAngleX.setD(RobotMap.shooterAngleXD);
	}
	
	public static Shooter getInstance() {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterSet());
    }
}

