package org.usfirst.frc.team2761.robot.commands.gearrelease;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.subsystems.GearRelease;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearReleaseOpen extends Command {

	GearRelease gearRelease;
	
    public GearReleaseOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearRelease = GearRelease.getInstance();
    	requires(gearRelease);
    	setTimeout(2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Logger.println("GearReleaseOpen init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Opening release");
    	gearRelease.setSpeed(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return gearRelease.isFullOpen() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearRelease.stop();
    	Logger.println("Stopping GearReleaseOpen");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
