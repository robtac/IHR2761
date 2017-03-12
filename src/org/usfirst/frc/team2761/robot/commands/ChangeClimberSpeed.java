package org.usfirst.frc.team2761.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChangeClimberSpeed extends Command {
	
	boolean rampUp;
	
    public ChangeClimberSpeed(boolean bool) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	rampUp = bool;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ChangeClimberSpeed const @ " + rampUp);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speedChange = 0.01;
    	double climberSpeed = SmartDashboard.getNumber("ClimberSpeed", -0.25);
    	if (rampUp && climberSpeed > -1) {
    		climberSpeed = climberSpeed - speedChange;
    	} else if (climberSpeed < -0.25) {
    		climberSpeed = climberSpeed + speedChange;
    	}
    	System.out.println("Climber Speed: " + climberSpeed);
    	SmartDashboard.putNumber("ClimberSpeed", climberSpeed);
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
