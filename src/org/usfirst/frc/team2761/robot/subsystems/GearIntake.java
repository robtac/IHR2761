package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem {

	private static GearIntake gearIntake = new GearIntake();

//	CANTalon gearPivot = new CANTalon (RobotMap.gearPivot);
	CANTalon gearRoller = new CANTalon (RobotMap.gearRoller);
	
	public GearIntake () {
		gearRoller.enableBrakeMode(true);
	}
	
	public void setSpeed (double speed) {
		gearRoller.set(speed);
	}
	
	public void stop () {
		gearRoller.set(0);
	}
	
	public static GearIntake getInstance () {
		return gearIntake;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

