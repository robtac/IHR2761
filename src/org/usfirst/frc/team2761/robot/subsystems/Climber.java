package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Climber instance = new Climber();
	
	CANTalon climber1 = new CANTalon (RobotMap.climb);
	CANTalon climber2 = new CANTalon (RobotMap.climbRoller);

	public Climber () {
		SmartDashboard.putNumber("ClimberSpeed", -0.25);
		climber1.enableBrakeMode(true);
		climber2.enableBrakeMode(true);
	}
	
	public void setSpeed (double speed) {
		climber1.set(speed);
		climber2.set(speed);
	}
	
	public void stop () {
		climber1.stopMotor();
		climber2.stopMotor();
	}
	
	public static Climber getInstance () {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

