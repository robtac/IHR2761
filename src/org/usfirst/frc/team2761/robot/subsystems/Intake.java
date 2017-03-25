package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Intake instance = new Intake();
	CANTalon intake = new CANTalon(RobotMap.intakeBelt);
	
	public Intake () {
		intake.changeControlMode(TalonControlMode.PercentVbus);
//		intake.setCurrentLimit(25);
	}
	
	public void setSpeed (double speed) {
		System.out.println(intake.getOutputCurrent());
		intake.set(speed);
	}
	
	public void stop () {
		intake.set(0);
	}
	
	public static Intake getInstance () {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

