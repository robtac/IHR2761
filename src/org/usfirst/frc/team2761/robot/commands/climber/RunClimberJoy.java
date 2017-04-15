package org.usfirst.frc.team2761.robot.commands.climber;

import org.usfirst.frc.team2761.robot.OI;
import org.usfirst.frc.team2761.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunClimberJoy extends Command {

	Climber climber;
	
    public RunClimberJoy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	climber = Climber.getInstance();
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = Math.abs(OI.climberJoystick.getY());
    	if (speed > 0.9) {
    		speed = 0.9;
    	}
    	climber.setSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
