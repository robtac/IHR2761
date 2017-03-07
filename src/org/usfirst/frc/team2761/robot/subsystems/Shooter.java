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
	
	// Initializes the main shooter motor and talon
	public Shooter() {
		System.out.println("Shooter const");
		
		shooterMotor.changeControlMode(TalonControlMode.Speed);
		shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	shooterMotor.setEncPosition(0);
    	shooterMotor.setF(RobotMap.shooterF);
		setPIDShoot();
	}
	
	// Prints out the motor speed to the console
	public void printSpeed() {
		double motorVelocity = (int) (shooterMotor.getPulseWidthVelocity() * ENCODERVELOCITY);
    	System.out.println("Is executing at: " + motorVelocity + " rpm");
    	motorVelocity = shooterMotor.getSpeed() * ENCODERVELOCITY;
    	SmartDashboard.putNumber("Actual Shooter Speed", motorVelocity);
    	SmartDashboard.putNumber("Actual Shooter Speed Number", motorVelocity);
	}
	
	// Sets the motor to the desired speed
	public void setSpeed(double speed) {
		shooterMotor.set(speed);
	}

	// Stops the motor abruptly
	public void stop() {
		shooterMotor.set(0);
		shooterMotor.disable();
		shooterMotor.enable();
	}
	
	// Sets the PIDs for the main shooter motor
	public void setPIDShoot() {
		shooterMotor.setP(RobotMap.shooterP);
		shooterMotor.setI(RobotMap.shooterI);
		shooterMotor.setD(RobotMap.shooterD);
	}
	
	// Returns the instance of the main subsystem
	public static Shooter getInstance() {
		return instance;
	}
	
	// Sets the default running command
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterSet());
    }
}

