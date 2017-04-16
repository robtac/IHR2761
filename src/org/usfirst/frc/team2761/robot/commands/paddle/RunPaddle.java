package org.usfirst.frc.team2761.robot.commands.paddle;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.subsystems.Paddle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunPaddle extends Command {

	Paddle paddle;
	
    public RunPaddle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	paddle = Paddle.getInstance();
    	requires(paddle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("runPaddle init");
    	Logger.println("RunPaddle init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("runPaddle exec");
    	paddle.setSpeed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	paddle.stop();
    	Logger.println("Stopping paddle");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
