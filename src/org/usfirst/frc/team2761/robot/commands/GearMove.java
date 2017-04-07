package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class GearMove extends Command {

	DriveTrain tank;
	NetworkTable table, contourTable;
	double[] centerDifference, imageSize;
	boolean isFinished;
	
    public GearMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	table = NetworkTable.getTable("Gears");
    	contourTable = NetworkTable.getTable("Gears");
    	table.getBoolean("isValid", false);
    	tank = DriveTrain.getInstance();
        requires(tank);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Boolean isValid = table.getBoolean("isValid", false);
    	if (isValid) {
//    		System.out.println("Proportional Distance: " + getProportionalDistance());
    		tank.drive(-0.12 + moveAdditive(), 0.1);
//    		getProportionalDistance();
    	} else {
    		isFinished = true;
    	}
    }
    
    private double moveAdditive () {
    	double[] blankArray = {};
    	double[] centerDifference = table.getNumberArray("CenterDifference", blankArray);;
    	if (centerDifference[0] > 0) {
    		return -0.02;
    	} else if (centerDifference[0] < 0) {
    		return 0.02;
    	} else {
    		return 0;
    	}
    }
    
    private double getProportionalDistance () {
    	double[] blankArray = {0.};
    	double[] imageSize = table.getNumberArray("Image Size", blankArray);
    	double contour1Boundary, contour2Boundary;
    	// Contour 1
    	System.out.println(contourTable.getSubTable("Contour  1"));
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
    	contourTable.getSubTable("Contour 2");
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
    	double differenceProportion = imageSize[0] / contourDifference;
    	
    	return differenceProportion;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	tank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	tank.stop();
    }
}
