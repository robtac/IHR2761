package org.usfirst.frc.team2761.robot.commands.climber;

import org.usfirst.frc.team2761.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class ReleaseClimber extends TimedCommand {

	Climber climber;
	
    public ReleaseClimber() {
        super(1);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        climber = Climber.getInstance();
        requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	climber.setSpeed(0.25);
    }

    // Called once after timeout
    protected void end() {
    	climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
