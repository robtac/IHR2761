package org.usfirst.frc.team2761.robot.commands.gearpivot;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.subsystems.GearPivot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunGearPivotForward extends Command {

	GearPivot gearPivot;
	
    public RunGearPivotForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearPivot = GearPivot.getInstance();
    	requires(gearPivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Logger.println("GearPivotForward init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearPivot.setSpeed(0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearPivot.stop();
    	Logger.println("Stopping GearPivotForward");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
