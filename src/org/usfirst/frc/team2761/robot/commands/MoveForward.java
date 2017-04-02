package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForward extends Command {

	DriveTrain tank;
	
    public MoveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	tank = DriveTrain.getInstance();
    	requires(tank);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0.45);
    	System.out.println("Moving Forward");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	tank.drive(-0.18, 0.15);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	tank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	tank.stop();
    }
}
