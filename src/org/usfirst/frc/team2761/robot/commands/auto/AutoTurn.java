package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	DriveTrain driveTrain;
	double targetAngle;
	boolean isFinished;
	
    public AutoTurn(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	targetAngle = angle;
    	isFinished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = driveTrain.getAngle();
		if (targetAngle - currentAngle < 0) {
			isFinished = true;
		} else {
			driveTrain.drive(0.1, -0.1);
		}
		if (targetAngle - currentAngle > 0) {
			isFinished = true;
		} else {
			driveTrain.drive(-0.1, 0.1);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
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
