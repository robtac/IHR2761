package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.Climber;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveForward extends Command {
	
	DriveTrain tankDrive;
	Climber climber;
	double initTime, lastTime;
	
    public AutoDriveForward() {
    	tankDrive = DriveTrain.getInstance();
    	climber = Climber.getInstance();
    	requires(tankDrive);
    	requires(climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Running auto");
    	initTime = Timer.getFPGATimestamp();
    	lastTime = Timer.getFPGATimestamp();
    	setTimeout(3.1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentTime = Timer.getFPGATimestamp();
    	if (currentTime > lastTime + 1) {
    		lastTime = currentTime;
    		SmartDashboard.putBoolean("takePicture", true);
    	}
    	tankDrive.drive(-0.25, 0.25);
    	climber.setSpeed(-0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	tankDrive.drive(0, 0);
    	climber.stop();
    	System.out.println("Stopped Auto");
    }

    public void cancel() {
    	tankDrive.drive(0, 0);
    	climber.stop();
    	System.out.println("Stopped Auto");
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	tankDrive.drive(0, 0);
    	climber.stop();
    }
}