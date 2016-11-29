package org.usfirst.frc.team2761.Code2016.commands;

import org.usfirst.frc.team2761.Code2016.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveForward extends Command {
	
	DriveTrain tankDrive;
	double initTime;
	
    public AutoDriveForward() {
    	tankDrive = DriveTrain.getInstance();
    	requires(tankDrive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime =  Timer.getFPGATimestamp();
    	setTimeout(2.25);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	tankDrive.drive(1, -1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	tankDrive.drive(0, 0);
    	System.out.println("Stopped Auto");
    }

    public void cancel() {
    	tankDrive.drive(0, 0);
    	System.out.println("Stopped Auto");
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}