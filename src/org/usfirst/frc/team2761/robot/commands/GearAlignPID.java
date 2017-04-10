package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearAlignPID extends Command {

	DriveTrain driveTrain;
	PIDController pidController;
	NetworkTable table;
	Boolean isTableValid;
	
    public GearAlignPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isTableValid = true;
    	try {
    		table = NetworkTable.getTable("Gears");
    	} catch (Exception e) {
    		isTableValid = false;
    	}
    	
    	if (isTableValid) {
	    	PIDSource visionSource = new PIDSource() {
				@Override
				public void setPIDSourceType(PIDSourceType pidSource) {}
	
				@Override
				public PIDSourceType getPIDSourceType() {
					return PIDSourceType.kDisplacement;
				}
	
				@Override
				public double pidGet() {
					Boolean isValid = table.getBoolean("isValid", false);
					if (!isValid)
						return 0;
					double[] blankArray = {};
			    	double[] centerDifference = table.getNumberArray("CenterDifference", blankArray);
			    	return centerDifference[0];
				}
	    	};
	    	
	    	PIDOutput driveOutput = new PIDOutput() {
				@Override
				public void pidWrite(double output) {
					driveTrain.pivot(output);
				}
	    	};
	    	
	    	final double kP = RobotMap.defaultVisionP;
	    	final double kI = RobotMap.defaultVisionI;
	    	final double kD = RobotMap.defaultVisionD;
	    	
	    	
	    	pidController = new PIDController(kP, kI, kD, visionSource, driveOutput);
	    	
	    	pidController.setAbsoluteTolerance(10);
			
			final double MIN_SPEED = 0.2;
			final double MAX_SPEED = 0.5;
			
			if (visionSource.pidGet() > 0) {
				pidController.setOutputRange(MIN_SPEED, MAX_SPEED);
			}
			else {
				pidController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
			}
			
			pidController.setSetpoint(0);
			
			pidController.enable();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("pidController get", pidController.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isTableValid)
    		return pidController.onTarget();
    	else
    		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("GearAlignPID is finished!");
    	pidController.disable();
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
