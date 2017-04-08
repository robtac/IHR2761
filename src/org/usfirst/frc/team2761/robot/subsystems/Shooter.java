package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterSet;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	public static final double ENCODERVELOCITY = 60.0 * 10.0 / 4096.0;
	
	private static Shooter instance = new Shooter();
	
	public CANTalon shooterMotor1 = new CANTalon(RobotMap.shooterMotor1, 1);
	public CANTalon shooterMotor2 = new CANTalon(RobotMap.shooterMotor2, 1);
	
	// Initializes the main shooter motor and talon
	public Shooter() {
		System.out.println("Shooter const");
		
		shooterMotor1.changeControlMode(TalonControlMode.Speed);
		shooterMotor1.set(0);
//		shooterMotor1.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterMotor1.reverseSensor(true);
    	shooterMotor1.setEncPosition(0);
    	shooterMotor1.configEncoderCodesPerRev(1024);
    	shooterMotor1.setF(RobotMap.shooterF);
		setPIDShoot();
		
//		shooterMotor2.changeControlMode(TalonControlMode.Follower);
//		shooterMotor2.set(RobotMap.shooterMotor1);
//		shooterMotor2.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	// Prints out the motor speed to the console
	public void printSpeed() {
		double motorVelocity = (shooterMotor1.getSpeed());
    	System.out.println("Is executing at: " + motorVelocity + " rpm");
//    	motorVelocity = shooterMotor1.getSpeed() * ENCODERVELOCITY;
    	motorVelocity = shooterMotor1.getSpeed();
    	SmartDashboard.putNumber("Actual Shooter Speed", motorVelocity);
    	SmartDashboard.putNumber("Actual Shooter Speed Number", motorVelocity);
	}
	
	// Sets the motor to the desired speed
	public void setSpeed(double speed) {
		shooterMotor1.set(speed);
//		shooterMotor2.set(speed);
//		shooterMotor1.set(0.91);
	}

	// Stops the motor abruptly
	public void stop() {
		shooterMotor1.set(0);
		shooterMotor1.disable();
		shooterMotor1.enable();
		shooterMotor2.set(0);
		shooterMotor2.disable();
		shooterMotor2.enable();
	}
	
	// Sets the PIDs for the main shooter motor
	public void setPIDShoot() {
		shooterMotor1.setP(RobotMap.shooterP);
		shooterMotor1.setI(RobotMap.shooterI);
		shooterMotor1.setD(RobotMap.shooterD);
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

