package org.usfirst.frc.team2761.Code2016.subsystems;

import org.usfirst.frc.team2761.Code2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public abstract class ShooterPID extends PIDSubsystem {

    // Initialize your subsystem here
//	CANTalon angleMotor = new CANTalon(RobotMap.shootAngleMotor);
//	public static ShooterPID instance = new ShooterPID();
	
    public ShooterPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooter", 0.1, 0.1, 0.1);
//    	angleMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//    	angleMotor.changeControlMode(CANTalon.TalonControlMode.Position);
//    	angleMotor.setEncPosition(0);
    	getPIDController().setContinuous(false);
    	// set distance per pulse here?
    	setPercentTolerance(0.05);
    	setSetpoint(0);
    	enable();
    }
//    
//    public static ShooterPID getInstance() {
////    	return instance;
//    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//    protected double returnPIDInput() {
//        // Return your input value for the PID loop
//        // e.g. a sensor, like a potentiometer:
//        // yourPot.getAverageVoltage() / kYourMaxVoltage;
////    	return angleMotor.getPosition();
//    }
    
    public void move() {
//		System.out.println("getPosition(): " + angleMotor.get());
//		System.out.println("getPulseWidthPosition(): " + angleMotor.getPulseWidthPosition());
    }
    
    public void setPosition(int position) {
//    	angleMotor.set(position);
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
//    	angleMotor.pidWrite(output);
    }
}