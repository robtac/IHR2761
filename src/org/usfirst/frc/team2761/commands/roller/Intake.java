package org.usfirst.frc.team2761.commands.roller;

import org.usfirst.frc.team2761.OI;
import org.usfirst.frc.team2761.subsystems.Roller;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {
	
	Roller roller;

    public Intake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	roller = Roller.getInstance();
    	requires(roller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    
    public void start() {
    	System.out.println("STARTED Intake");
        roller.setSpeed(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	roller.setSpeed(0);
    }
    
    public void cancel() {
    	roller.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	roller.setSpeed(0);
    }
}