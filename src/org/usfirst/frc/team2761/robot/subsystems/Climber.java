package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.commands.climber.RunClimberJoy;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Climber instance = new Climber();
	
	CANTalon climber1 = new CANTalon (RobotMap.climb1);
	CANTalon climber2 = new CANTalon (RobotMap.climb2);

	public Climber () {
		Logger.println("Initialized Climber subsystem");
		
		SmartDashboard.putNumber("ClimberSpeed", -0.25);
		climber1.enableBrakeMode(true);
		climber2.enableBrakeMode(true);
		climber1.changeControlMode(TalonControlMode.PercentVbus);
		climber2.changeControlMode(TalonControlMode.Follower);
		climber2.set(RobotMap.climb1);
	}
	
	public void setSpeed (double speed) {
		climber1.set(-Math.abs(speed));
		
		climber2.set(-Math.abs(speed));
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
    	setDefaultCommand(new RunClimberJoy());
    }
}

