package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveRightSide extends Command {

	DriveTrain tank;
	Climber climber;
	double timer, lastTime;
	
    public AutoDriveRightSide() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	tank = DriveTrain.getInstance();
    	climber = Climber.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Running auto 2");
    	timer = Timer.getFPGATimestamp();
    	lastTime = Timer.getFPGATimestamp();
    	setTimeout(12);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentTime = Timer.getFPGATimestamp() - timer;
    	System.out.println("Timer " + currentTime);
    	if (currentTime < 3.65) {
    		tank.drive(-0.25, 0.265);
    		climber.setSpeed(-0.1);
    	} else if (currentTime < 4.5) {
    		tank.drive(0.25, 0.265);
    		climber.stop();
    	} else if (currentTime < 7) {
    		tank.stop();
    		climber.stop();
    	} else {
    		tank.stop();
    		climber.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	tank.stop();
		climber.stop();
		System.out.println("Stopped auto 2");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	tank.stop();
		climber.stop();
    }
}
