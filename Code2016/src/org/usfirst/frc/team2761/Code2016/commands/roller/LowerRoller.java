package org.usfirst.frc.team2761.Code2016.commands.roller;

import org.usfirst.frc.team2761.Code2016.subsystems.Roller;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerRoller extends Command {

	Roller roller;

    public LowerRoller() {
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
    	System.out.println("LowerRoller Start");
    	roller.setLifterSpeed(0.45);
    }
    
    public void cancel() {
    	roller.setLifterSpeed(0);
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