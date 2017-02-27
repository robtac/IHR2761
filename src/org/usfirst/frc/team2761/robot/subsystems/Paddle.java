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
	
	// Initializes the paddle subsystem
	public Paddle () {
		
	}

	// Returns the instance of this subsystem
	public static Paddle getInstance() {
		return instance;
	}
	
	// Sets the speed of the motor
	public void setSpeed (double speed) {
		paddle.set(speed);
	}
	
	// Stops the motor abruptly
	public void stop() {
		paddle.set(0);
		paddle.disable();
		paddle.enable();
	}
	
	// Sets the default running command
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

