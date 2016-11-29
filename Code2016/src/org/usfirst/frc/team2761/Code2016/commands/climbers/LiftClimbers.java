package org.usfirst.frc.team2761.Code2016.commands.climbers;

import org.usfirst.frc.team2761.Code2016.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftClimbers extends Command {
	
	Climber climber;
	
    public LiftClimbers() {
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
    	climber.setSpeed(1);
    	System.out.println("Executed LiftClimbers");
    }
    
    public void start() {
    	climber.setSpeed(0.5);
    	System.out.println("Started LiftClimbers");
    }
    
    public void cancel() {
    	climber.setSpeed(0);
    	System.out.println("Canceled LiftClimbers");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climber.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}