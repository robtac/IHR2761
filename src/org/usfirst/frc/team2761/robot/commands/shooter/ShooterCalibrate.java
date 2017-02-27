package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.subsystems.Shooter;
import org.usfirst.frc.team2761.robot.subsystems.ShooterAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCalibrate extends Command {

	Boolean isFinishedX = false;
	Boolean isFinishedY = false;
	
	ShooterAngle shooterAngle;
	
	// Constructor for the command
    public ShooterCalibrate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	shooterAngle = ShooterAngle.getInstance();
    	requires(shooterAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("init");
    	shooterAngle.zeroX();
    	shooterAngle.zeroY();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (shooterAngle.getMagHallX() && !isFinishedX) {
    		shooterAngle.setPositionX(3);
    	} else {
    		shooterAngle.setPositionX(0);
    		shooterAngle.zeroX();
    		isFinishedX = true;
    		System.out.println("Angle X Calibrated");
    	}
    	if (shooterAngle.getMagHallY() && !isFinishedY) {
    		shooterAngle.setPositionY(3);
    	} else {
    		shooterAngle.setPositionY(0);
    		shooterAngle.zeroY();
    		isFinishedY = true;
    		System.out.println("Angle Y Calibrated");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isFinishedX && isFinishedY) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterAngle.setPositionX(0);
    	shooterAngle.setPositionY(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooterAngle.setPositionX(0);
    	shooterAngle.setPositionY(0);
    }
}
