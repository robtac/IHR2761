package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearPivot extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static GearPivot instance = new GearPivot();
	
	public CANTalon gearPivot = new CANTalon(RobotMap.gearPivot);
	
	public GearPivot () {
		gearPivot.enableBrakeMode(true);
	}
	
	public void setSpeed (double speed) {
		gearPivot.set(speed);
	}
	
	public void stop () {
		gearPivot.set(0);
	}
	
	public static GearPivot getInstance () {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

