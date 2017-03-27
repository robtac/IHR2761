package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.subsystems.Climber;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2761.robot.subsystems.Paddle;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DumbCenterGears extends Command {

	DriveTrain driveTrain;
	Climber climber;
	double initTime;
	
    public DumbCenterGears() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	System.out.println("Running dumb center gears");
    	driveTrain = DriveTrain.getInstance();
    	climber = Climber.getInstance();
    	requires(driveTrain);
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = Timer.getFPGATimestamp();
    	setTimeout(7);
//    	setTimeout(1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.drive(-0.25, 0.25);
    	climber.setSpeed(-0.25);
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
