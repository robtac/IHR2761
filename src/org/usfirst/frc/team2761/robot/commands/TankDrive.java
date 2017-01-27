package org.usfirst.frc.team2761.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2761.robot.Robot;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

/**
 *
 */
public class TankDrive extends Command {
	
	DriveTrain tank;
	
	public TankDrive() {
		// Use requires() here to declare subsystem dependencies
		tank = DriveTrain.getInstance();
		requires(tank);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		tank.tankDrive();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
