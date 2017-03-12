package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeDriverInput extends Command {

	DriveTrain tank;
	Boolean val;
	
    public ChangeDriverInput(Boolean value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	tank = DriveTrain.getInstance();
    	requires(tank);
    	val = value;
    	System.out.println("Constructor val = " + value + " tank = " + tank.getInput());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	tank.setInput(val);
    	System.out.println(tank.getInput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
