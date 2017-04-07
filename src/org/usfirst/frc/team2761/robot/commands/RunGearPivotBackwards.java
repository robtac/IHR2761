package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.GearPivot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunGearPivotBackwards extends Command {

	GearPivot gearPivot;
	
    public RunGearPivotBackwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearPivot = GearPivot.getInstance();
    	requires(gearPivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearPivot.setSpeed(-0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearPivot.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	gearPivot.stop();
    }
}
