package org.usfirst.frc.team2761.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2761.robot.*;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

/**
 *
 */
public class Drive extends Command {

	DriveTrain driveTrain;
	
	private final double timePerRotation = 0.25;
	
	boolean hasEncoders;
    boolean hasGyro;
    boolean hasVision;
    
    boolean isFinished;
	
    double distance;
    
    public Drive(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	
    	hasEncoders = RobotMap.hasEncoders;
    	hasGyro = RobotMap.hasGyro;
    	
    	if (hasEncoders) {
    		// Distance based
    		this.distance = distance;
    	} else {
    		// Time based
    		setTime(distance);
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (hasGyro && hasEncoders) {
    		executeGyroEncoders();
    	} else if (hasGyro) {
    		executeGyro();
    	} else if (hasEncoders) {
    		executeEncoders();
    	} else {
    		executeTime();
    	}
    }
    
    private void executeGyroEncoders () {
    	double angle = driveTrain.getAngle();
    	if (angle > 2) {
    		driveTrain.drive(-0.28, 0.25);
    	} else if (angle < -2) {
    		driveTrain.drive(-0.25, 0.28);
    	} else {
    		driveTrain.drive(-0.25, 0.25);
    	}
    	
    	testFinish();
    }
    
    private void executeGyro () {
    	double angle = driveTrain.getAngle();
    	if (angle > 2) {
    		driveTrain.drive(-0.28, 0.25);
    	} else if (angle < -2) {
    		driveTrain.drive(-0.25, 0.28);
    	} else {
    		driveTrain.drive(-0.25, 0.25);
    	}
    }
    
    private void executeEncoders () {
    	driveTrain.drive(-0.25, 0.25);
    	
    	testFinish();
    }
    
    private void executeTime () {
    	driveTrain.drive(-0.25, 0.25);
    }
    
    private void testFinish () {
    	if (driveTrain.getDistance() > distance) {
    		isFinished = true;
    	}
    }
    
    private void setTime (double distance) {
    	double rotation = distance / driveTrain.circumference;
    	double time = rotation * timePerRotation;
    	
    	setTimeout(time);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (hasGyro || hasEncoders) {
        	return isFinished;
        } else {
        	return isTimedOut();
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveTrain.stop();
    }
}
