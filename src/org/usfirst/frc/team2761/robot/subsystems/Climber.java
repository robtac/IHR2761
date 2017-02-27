package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static Climber instance = new Climber();
	
	CANTalon climber = new CANTalon (RobotMap.climb);
	CANTalon climberRoller = new CANTalon (RobotMap.climbRoller);
	
	// Sets the control mode of the talons
	public Climber() {
		climber.changeControlMode(TalonControlMode.PercentVbus);
		climberRoller.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	// Returns the instance of this subsystem
	public static Climber getInstance () {
		return instance;
	}
	
	// Sets the speed of the climber motors
	public void setSpeed (double speed) {
		System.out.println(speed);
		double negSpeed = -speed;
		System.out.println(negSpeed);
		climber.set(speed);
		
		climberRoller.set(speed);
	}
	
	// Stops the motors abruptly
	public void stop() {
		climber.set(0);
		climber.disable();
		climber.enable();
		
		climberRoller.set(0);
		climberRoller.disable();
		climberRoller.enable();
	}
	
	// Sets the default running command
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

