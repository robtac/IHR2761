package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ZeroGyro extends InstantCommand {

	DriveTrain driveTrain;
	
    public ZeroGyro() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    // Called once when the command executes
    protected void initialize() {
    	driveTrain.zeroGyro();
    	System.out.println("Gyro: " + driveTrain.getAngle() + " -- Zero: " + driveTrain.gyroZeroValue);
    }

}
