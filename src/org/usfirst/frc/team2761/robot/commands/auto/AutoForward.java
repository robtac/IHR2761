package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.subsystems.Climber;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2761.robot.subsystems.Paddle;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoForward extends Command {

	DriveTrain driveTrain;
	double targetDistance;
	boolean isFinished;
	double initTime;
	Climber climber;
	Paddle paddle;
	
    public AutoForward(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	isFinished = false;
    	targetDistance = distance;
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	climber = Climber.getInstance();
    	requires(climber);
    	paddle = Paddle.getInstance();
    	requires(paddle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = Timer.getFPGATimestamp();
//    	setTimeout(3.5);
    	setTimeout(10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double encoderAverage = (driveTrain.getEncoderLeft() + driveTrain.getEncoderRight()) / 2;
//    	if (encoderAverage < targetDistance) {
//    		driveTrain.drive(-0.25, 0.25);
//    	} else {
//    		driveTrain.stop();
//    	}
    	paddle.stop();
    	double angle = driveTrain.getAngle();
    	if (angle > 2) {
    		driveTrain.drive(-0.28, 0.25);
    	} else if (angle < -2) {
    		driveTrain.drive(-0.25, 0.28);
    	} else {
    		driveTrain.drive(-0.25, 0.25);
    	}
    	driveTrain.drive(-0.25, 0.25);
    	climber.setSpeed(-0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stop();
    	climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveTrain.stop();
    	climber.stop();
    }
}
