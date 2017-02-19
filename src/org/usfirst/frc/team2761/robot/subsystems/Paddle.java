package org.usfirst.frc.team2761.robot.subsystems;

import org.usfirst.frc.team2761.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Paddle extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static Paddle instance = new Paddle();
	
	CANTalon paddle = new CANTalon (RobotMap.paddle);
	
	public Paddle () {
		
	}

	public static Paddle getInstance() {
		return instance;
	}
	
	public void setSpeed (double speed) {
		paddle.set(speed);
	}
	
	public void stop() {
		paddle.set(0);
		paddle.disable();
		paddle.enable();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

