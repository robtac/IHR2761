package org.usfirst.frc.team2761.Code2016.subsystems;

import org.usfirst.frc.team2761.Code2016.RobotMap;
import org.usfirst.frc.team2761.Code2016.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	
	private static Shooter instance = new Shooter();
    
	CANTalon shootMotor = new CANTalon(RobotMap.shootMotor);
	CANTalon angleMotor = new CANTalon(RobotMap.shootAngleMotor);
	CANTalon catapultMotor = new CANTalon(RobotMap.catapultMotor);
	CANTalon flashLight = new CANTalon(RobotMap.flashLight);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Shooter() {
    	angleMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	angleMotor.changeControlMode(CANTalon.TalonControlMode.Position);
    	angleMotor.setEncPosition(0);
		angleMotor.setP(RobotMap.ANGLE_MOTOR_P);
		angleMotor.setI(RobotMap.ANGLE_MOTOR_I);
		angleMotor.setD(RobotMap.ANGLE_MOTOR_D);
	}
	
	public static Shooter getInstance() {
		return instance;
	}
	
	public void setSpeed(double speed) {
		shootMotor.set(speed);
	}
	
	public void adjustAngle(double speed) {
		angleMotor.enable();
		angleMotor.setP(RobotMap.ANGLE_MOTOR_P);
		angleMotor.setI(RobotMap.ANGLE_MOTOR_I);
		angleMotor.setD(RobotMap.ANGLE_MOTOR_D);
		angleMotor.set(speed);
	}
	
	public void disableAngleMotor() {
		angleMotor.disable();
	}
	
	public void flashlightOn() {
		flashLight.set(.25);
	}
	
	public void flashlighOff() {
		flashLight.set(0);
	}
	
	public void zeroEncoder() {
		angleMotor.setEncPosition(0);
	}
	
	public double getPosition() {
		return angleMotor.get();
	}
	
	public void moveCatapult(double speed) {
		catapultMotor.set(speed);
		System.out.println("Catapult:" + catapultMotor.get());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Shoot());
    }
}