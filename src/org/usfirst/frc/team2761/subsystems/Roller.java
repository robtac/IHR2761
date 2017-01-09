package org.usfirst.frc.team2761.subsystems;

import org.usfirst.frc.team2761.RobotMap;
import org.usfirst.frc.team2761.commands.roller.Intake;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Roller extends Subsystem {
	
	private static Roller instance = new Roller();
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon rollerMotor = new CANTalon(RobotMap.rollerMotor);
	CANTalon rollerLifterMotor = new CANTalon(RobotMap.rollerLifter);
	
	public static Roller getInstance() {
		return instance;
	}
	
	public void setLifterSpeed(double speed) {
		rollerLifterMotor.set(speed);
	}
	
	public void setSpeed(double speed) {
		rollerMotor.set(speed);
	} 

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Intake());
    }
}