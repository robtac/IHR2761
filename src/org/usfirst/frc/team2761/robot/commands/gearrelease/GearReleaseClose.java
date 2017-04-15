package org.usfirst.frc.team2761.robot.commands.gearrelease;

import org.usfirst.frc.team2761.robot.subsystems.GearRelease;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearReleaseClose extends Command {

	GearRelease gearRelease;
	
    public GearReleaseClose() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearRelease = GearRelease.getInstance();
    	requires(gearRelease);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Closing release");
    	gearRelease.setSpeed(-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return gearRelease.isFullClosed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearRelease.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
