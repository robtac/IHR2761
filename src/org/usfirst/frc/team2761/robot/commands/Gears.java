package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Gears extends Command {

	DriveTrain tank;
	NetworkTable table;
	double[] centerDifference, imageSize;
	
    public Gears() {
    	tank = DriveTrain.getInstance();
        requires(tank);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	table = NetworkTable.getTable("Gears");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Boolean isValid = table.getBoolean("isValid");
    	if (isValid) {
    		visionAlign();
    	} else {
    		tank.drive(0, 0);
    	}
    }

    private void visionAlign () {
    	centerDifference = table.getNumberArray("CenterDifference");
    	imageSize = table.getNumberArray("Image Size");
    	double[] imageCenter = {imageSize[0] / 2, imageSize[1] / 2};
    	double percentageX = centerDifference[0] / imageCenter[0];
    	System.out.println("Percentage X: " + percentageX);
    	tank.drive(percentageX * 0.35, (percentageX * 0.35));
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
