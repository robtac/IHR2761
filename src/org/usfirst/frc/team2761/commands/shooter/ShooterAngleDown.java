package org.usfirst.frc.team2761.commands.shooter;

import org.usfirst.frc.team2761.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterAngleDown extends Command {

	Shooter shooter;
	
    public ShooterAngleDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooter = Shooter.getInstance();
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void start() {
//    	shooter.adjustAngle(-0.25);
    	System.out.println("Down Start     -     " + shooter.getPosition());// for debugging purposes only
    	shooter.adjustAngle(2564);
    }
    
    public void cancel() {
    	shooter.disableAngleMotor();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}