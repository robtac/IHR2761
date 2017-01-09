package org.usfirst.frc.team2761.commands.shooter;

import org.usfirst.frc.team2761.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	
	Shooter shooter;
	
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooter = Shooter.getInstance();
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        shooter.setSpeed(-1);
    }
    
    public void start() {
    	shooter.setSpeed(-1);
    	shooter.flashlightOn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.setSpeed(0);
    	shooter.flashlighOff();
    }
    
    public void cancel() {
    	shooter.setSpeed(0);
    	shooter.flashlighOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.setSpeed(0);
    }
}