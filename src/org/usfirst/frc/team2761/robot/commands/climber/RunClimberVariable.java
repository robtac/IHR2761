package org.usfirst.frc.team2761.robot.commands.climber;

import org.usfirst.frc.team2761.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunClimberVariable extends Command {

	Climber climber;
	
    public RunClimberVariable() {
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
    	double speed = SmartDashboard.getNumber("ClimberSpeed", 0.5);
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
    	climber.stop();
    }
}
