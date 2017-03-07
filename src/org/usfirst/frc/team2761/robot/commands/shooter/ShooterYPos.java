package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.subsystems.ShooterAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterYPos extends Command {

	ShooterAngle shooterAngle;
	
    public ShooterYPos() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	shooterAngle = ShooterAngle.getInstance();
    	requires(shooterAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooterAngle.printValues();
    	shooterAngle.setPositionY(shooterAngle.getPositionY() + 5);
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
    	shooterAngle.setPositionY(0);
    }
}
