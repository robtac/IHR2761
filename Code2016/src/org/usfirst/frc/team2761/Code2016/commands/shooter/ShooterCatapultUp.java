package org.usfirst.frc.team2761.Code2016.commands.shooter;

import org.usfirst.frc.team2761.Code2016.OI;
import org.usfirst.frc.team2761.Code2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCatapultUp extends Command {

	Shooter shooter;
	
    public ShooterCatapultUp() {
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
    }
    
    public void start() {
    	System.out.println("Catapult UP");
//    	if(OI.limitSwitchUpCatapult.get() == true)
//    	{
        	shooter.moveCatapult(-1);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    public void cancel() {
    	shooter.moveCatapult(0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.moveCatapult(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.moveCatapult(0);
    }
}