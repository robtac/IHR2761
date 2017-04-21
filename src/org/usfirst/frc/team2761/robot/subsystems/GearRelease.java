package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearRelease extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static GearRelease instance = new GearRelease();
	CANTalon gearRelease = new CANTalon (RobotMap.gearRelease);
	
	DigitalInput limitIsClosed = new DigitalInput(RobotMap.GearReleaseClosedLimit);
	DigitalInput limitIsOpen = new DigitalInput(RobotMap.GearReleaseOpenLimit);
	
	public GearRelease () {
		Logger.println("Initialized GearRelease subsystem");
		
		gearRelease.enableBrakeMode(true);
	}
	
	public void setSpeed (double speed) {
		gearRelease.set(speed);
	}
	
	public void stop () {
		gearRelease.set(0);
	}
	
	public boolean isFullClosed () {
		return limitIsClosed.get();
	}
	
	public boolean isFullOpen () {
		return limitIsOpen.get();
	}
	
	public static GearRelease getInstance () {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

