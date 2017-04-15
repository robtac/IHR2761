package org.usfirst.frc.team2761.robot.commands.drivetrain;

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
public class GearAlignBoilerPID extends Command {

	DriveTrain driveTrain;
	PIDController pidController;
	NetworkTable table;
	Boolean isTableValid;
	
    public GearAlignBoilerPID() {
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
	    	
	    	final double kP = RobotMap.defaultVisionPivotP;
	    	final double kI = RobotMap.defaultVisionPivotI;
	    	final double kD = RobotMap.defaultVisionPivotD;
	    	
	    	pidController = new PIDController(kP, kI, kD, visionSource, driveOutput);
	    	
	    	pidController.setInputRange(-100, 100);
	    	
	    	pidController.setAbsoluteTolerance(15);
			
			final double MIN_SPEED = 0.1;
			final double MAX_SPEED = 0.225;
			
			pidController.setOutputRange(-MAX_SPEED, MAX_SPEED);
			
			pidController.setToleranceBuffer(10);
			
			pidController.setSetpoint(0);
			
			pidController.enable();
			
			System.out.println("Vision P: " + kP);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("PIDController get: " + pidController.get() + 
//    			" --- PIDController error: " + pidController.getError() + 
//    			" --- PIDController P: " + pidController.getP());
    	
    	System.out.println("Error: " + pidController.getError() + 
    			" -- onTarget: " + pidController.onTarget() + 
    			" -- RobotMap: " + RobotMap.isBoilerAlignOnTarget);
    	RobotMap.isBoilerAlignOnTarget = pidController.onTarget();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isValid = table.getBoolean("isValid", false);
//    	if (isValid)
//    		return pidController.onTarget();
//    	else
//    		return true;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("GearAlignPID is finished!");
    	pidController.disable();
    	pidController.free();
    	driveTrain.stop();
    	RobotMap.isBoilerAlignOnTarget = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
