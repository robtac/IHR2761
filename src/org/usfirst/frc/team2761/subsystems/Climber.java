package org.usfirst.frc.team2761.subsystems;

import org.usfirst.frc.team2761.RobotMap;
import org.usfirst.frc.team2761.commands.climbers.LiftClimbers;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.anmol
	
	private static Climber instance = new Climber();
	
	CANTalon climbMotor = new CANTalon(RobotMap.climberMotor);
//	static Compressor c;
//	Solenoid valve = new Solenoid(RobotMap.solenoidModule, RobotMap.solenoidChannel);
	
	
	public static Climber getInstance() {
		return instance;
	}
	
	public static void startCompressor() {
//		c.start();
	}
	
	public void raiseClimbers() {
//		valve.set(true);
		climbMotor.set(-0.5);
	}
	
	public void lowerClimbers() {
//		valve.set(false);
		climbMotor.set(0.5);
	}
	
	public void setSpeed(double speed) {
		climbMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}