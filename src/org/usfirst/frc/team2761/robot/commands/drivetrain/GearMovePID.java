package org.usfirst.frc.team2761.robot.commands.drivetrain;

import org.usfirst.frc.team2761.robot.Logger;
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
public class GearMovePID extends Command {

	DriveTrain driveTrain;
	PIDController pidController;
	NetworkTable table, contourTable;
	Boolean isTableValid, isValids;
	
    public GearMovePID() {
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
			    	isValids = isValid;
			    	return centerDifference[0];
				}
	    	};
	    	
	    	PIDOutput driveOutput = new PIDOutput() {
				@Override
				public void pidWrite(double output) {
					driveTrain.moveTurn(0.22, output);
				}
	    	};
	    	
//	    	final double kP = SmartDashboard.getNumber("Vision P", RobotMap.defaultVisionP);
//	    	final double kI = SmartDashboard.getNumber("Vision I", RobotMap.defaultVisionI);
//	    	final double kD = SmartDashboard.getNumber("Vision D", RobotMap.defaultVisionD);
	    	final double kP = RobotMap.defaultVisionP;
	    	final double kI = RobotMap.defaultVisionI;
	    	final double kD = RobotMap.defaultVisionD;
	    	
	    	pidController = new PIDController(kP, kI, kD, visionSource, driveOutput);
	    	
	    	pidController.setInputRange(-100, 100);
	    	
	    	pidController.setAbsoluteTolerance(10);
			
			final double MIN_SPEED = 0.05;
			final double MAX_SPEED = 0.1;
			
			pidController.setOutputRange(-MAX_SPEED, MAX_SPEED);
			
			pidController.setToleranceBuffer(2);
			
			pidController.setSetpoint(0);
			
			pidController.enable();
			
			Logger.println("GearMovePID init");
    	} else {
    		new ForwardEncoderPID(80);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String s = ("PIDController get: " + pidController.get() + 
    			" --- PIDController error: " + pidController.getError() + 
    			" --- PIDController P: " + pidController.getP());
    	Logger.println("GearMovePID - - " + s);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isValid = table.getBoolean("isValid", false);
    	if (isValid)
    		return testCloseness();
    	else
    		return true;
    }

    private boolean testCloseness () {
    	double[] blankArray = {0.};
    	double[] imageSize = table.getNumberArray("Image Size", blankArray);
    	double contour1Boundary, contour2Boundary;
    	// Contour 1
    	contourTable = (NetworkTable) table.getSubTable("Contour1");
    	double[] contour1Center = contourTable.getNumberArray("Center", blankArray);
    	System.out.println("Contour 1 Center: " + contour1Center[1]);
    	double[] contour1Size = contourTable.getNumberArray("Size", blankArray);
    	// If on right side, get left boundary
    	if (contour1Center[0] > imageSize[0] / 2.) {
    		contour1Boundary = contour1Center[0] - (contour1Size[0] / 2);
    	} else {
    		contour1Boundary = contour1Center[0] + (contour1Size[0] / 2);
    	}
    	
    	// Contour 2
    	contourTable = (NetworkTable) table.getSubTable("Contour2");
    	double[] contour2Center = contourTable.getNumberArray("Center", blankArray);
    	System.out.println("Contour 2 Center: " + contour2Center[1]);
    	double[] contour2Size = contourTable.getNumberArray("Size", blankArray);
    	// If on right side, get left boundary
    	if (contour2Center[0] > imageSize[0] / 2.) {
    		contour2Boundary = contour2Center[0] - (contour2Size[0] / 2);
    	} else {
    		contour2Boundary = contour2Center[0] + (contour2Size[0] / 2);
    	}
    	
    	// Get proportion based off image size
    	double contourDifference = Math.abs(contour1Boundary - contour2Boundary);
    	double differenceProportion = contourDifference / imageSize[0];
    	
    	System.out.println("Contour Difference: " + contourDifference +
    			" --- Difference Proportion: " + differenceProportion);
    	
    	if (differenceProportion > 0.5) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("GearMOvePID is finished!");
    	pidController.disable();
    	driveTrain.stop();
    	Logger.println("Stopping GearMovePID");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
