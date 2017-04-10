package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ZeroEncoders extends InstantCommand {

	DriveTrain driveTrain;
	
    public ZeroEncoders() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    // Called once when the command executes
    protected void initialize() {
    	driveTrain.zeroEncoders();
    }

}
