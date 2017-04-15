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
	CANTalon paddle = new CANTalon(RobotMap.ballAgitator);
	
	public Paddle () {
		
	}
	
	public void setSpeed () {
//		System.out.println("Paddle setSpeed");
//		paddle.set(-0.27);
		paddle.set(-0.3);
	}
	
	public void stop () {
		paddle.set(0);
	}

	public static Paddle getInstance () {
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

