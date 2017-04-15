package org.usfirst.frc.team2761.robot.commands.drivetrain;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	DriveTrain driveTrain;
	double distance, time;
	
    public DriveForward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	distance = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double rotationsPerSecond = 4. / 9;
    	double circumference = 4 * Math.PI;
    	double distancePerSecond = rotationsPerSecond * circumference;
    	double secondsPerInch = 1 / distancePerSecond;
    	time = secondsPerInch * distance;
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(time);
    	driveTrain.drive(-0.25, 0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
