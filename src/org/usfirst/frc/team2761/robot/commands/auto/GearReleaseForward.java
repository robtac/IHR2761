package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.subsystems.GearRelease;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class GearReleaseForward extends TimedCommand {

	GearRelease gearRelease;
	
    public GearReleaseForward(double timeout) {
        super(timeout);
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
    	gearRelease.setSpeed(1);
    }

    // Called once after timeout
    protected void end() {
    	gearRelease.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
