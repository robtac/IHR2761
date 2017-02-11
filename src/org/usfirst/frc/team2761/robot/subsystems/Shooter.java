package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.SetShooter;
import org.usfirst.frc.team2761.robot.commands.Shoot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private static Shooter instance = new Shooter();
	
	public CANTalon shooterMotor = new CANTalon(RobotMap.shooterMotor);
	
	public Shooter() {
		shooterMotor.changeControlMode(TalonControlMode.Speed);
		shooterMotor.setFeedbackDevice(FeedbackDevice.PulseWidth);
    	shooterMotor.setEncPosition(0);
    	shooterMotor.setF(RobotMap.shooterF);
		shooterMotor.setP(RobotMap.shooterP);
		shooterMotor.setI(RobotMap.shooterI);
		shooterMotor.setD(RobotMap.shooterD);
	}
	
	public void setSpeed(double speed) {
		shooterMotor.set(speed);
	}

	public static Shooter getInstance() {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SetShooter());
    }
}

