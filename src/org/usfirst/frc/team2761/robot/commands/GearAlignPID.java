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
	    	
	    	final double kP = SmartDashboard.getNumber("Vision P", RobotMap.defaultVisionP);
	    	final double kI = SmartDashboard.getNumber("Vision I", RobotMap.defaultVisionI);
	    	final double kD = SmartDashboard.getNumber("Vision D", RobotMap.defaultVisionD);
	    	
	    	
	    	pidController = new PIDController(kP, kI, kD, visionSource, driveOutput);
	    	
	    	pidController.setInputRange(-100, 100);
	    	
	    	pidController.setAbsoluteTolerance(10);
			
			final double MIN_SPEED = 0.1;
			final double MAX_SPEED = 0.225;
			
			pidController.setOutputRange(-MAX_SPEED, MAX_SPEED);
			
			pidController.setToleranceBuffer(2);
			
			pidController.setSetpoint(0);
			
			pidController.enable();
			
			System.out.println("Vision P: " + kP);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("PIDController get: " + pidController.get() + 
    			" --- PIDController error: " + pidController.getError() + 
    			" --- PIDController P: " + pidController.getP());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
//    	if (isTableValid)
//    		return pidController.onTarget();
//    	else
//    		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("GearAlignPID is finished!");
    	pidController.disable();
    	pidController.free();
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
