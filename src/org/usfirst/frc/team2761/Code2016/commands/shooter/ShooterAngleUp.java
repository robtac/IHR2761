package org.usfirst.frc.team2761.Code2016.commands.shooter;

import org.usfirst.frc.team2761.Code2016.subsystems.Shooter;
import org.usfirst.frc.team2761.Code2016.subsystems.ShooterPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterAngleUp extends Command {

	Shooter shooter;
	
    public ShooterAngleUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooter= Shooter.getInstance();
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
    
    public void start() {
    	System.out.println("Up Start     -     " + shooter.getPosition());
//        if(shooter.getPosition() >= 9950 && shooter.getPosition() <= 1050) {
//        	this.isFinished();
//        } else {
//        	shooter.adjustAngle(-1220);
    		//shooter.adjustAngle(-810); 
    	shooter.adjustAngle(1734);
//        }
    }
    
    public void cancel() {
    	shooter.disableAngleMotor();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}