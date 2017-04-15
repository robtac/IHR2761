package org.usfirst.frc.team2761.robot.commands.climber;

import org.usfirst.frc.team2761.robot.OI;
import org.usfirst.frc.team2761.robot.XboxController;
import org.usfirst.frc.team2761.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberJoystick extends Command {

	Climber climber;
	XboxController.Thumbstick rightThumbstick;
	
    public ClimberJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	climber = Climber.getInstance();
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rightThumbstick = OI.xbox.rightStick;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double moveValue = rightThumbstick.getY();
    	if (moveValue > 0.1) {
    		if (moveValue > 0.75) {
    			climber.setSpeed(0.75);
    		} else {
    			climber.setSpeed(moveValue);
    		}
    	} else {
    		climber.stop();
    	}
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
    	climber.stop();
    }
}
