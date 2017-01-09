package org.usfirst.frc.team2761.commands.roller;

import org.usfirst.frc.team2761.subsystems.Roller;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftRoller extends Command {

	Roller roller;

    public LiftRoller() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	roller = Roller.getInstance();
    	requires(roller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void start() {
    	System.out.println("LiftRoller Start");
    	roller.setLifterSpeed(-0.50);
    }
    
    public void cancel() {
    	roller.setLifterSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	roller.setLifterSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	roller.setLifterSpeed(0);
    }
}