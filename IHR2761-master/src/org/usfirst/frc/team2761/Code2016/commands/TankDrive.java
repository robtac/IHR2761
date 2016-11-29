package org.usfirst.frc.team2761.Code2016.commands;

import org.usfirst.frc.team2761.Code2016.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {
	
	DriveTrain tank;

    public TankDrive() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	tank = DriveTrain.getInstance();
    	requires(tank);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	tank.tankDrive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}