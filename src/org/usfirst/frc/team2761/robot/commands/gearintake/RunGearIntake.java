package org.usfirst.frc.team2761.robot.commands.gearintake;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunGearIntake extends Command {

	GearIntake gearIntake;
	
    public RunGearIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearIntake = GearIntake.getInstance();
    	requires(gearIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Logger.println("GearIntake init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearIntake.setSpeed(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearIntake.stop();
    	Logger.println("GearIntake stop");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
