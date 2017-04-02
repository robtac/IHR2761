package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class GearMove extends Command {

	DriveTrain tank;
	NetworkTable table;
	double[] centerDifference, imageSize;
	boolean isFinished;
	
    public GearMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	table = NetworkTable.getTable("Gears");
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
    		tank.drive(-0.12 + moveAdditive(), 0.1);
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
