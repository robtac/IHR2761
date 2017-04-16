package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.Logger;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class ChangeCamera extends InstantCommand {

	NetworkTable table;
	boolean isGears;
	
    public ChangeCamera(boolean val) {
        super();
        Logger.println("Changing camera to " + val);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        isGears = val;
    }

    // Called once when the command executes
    protected void initialize() {
    	table = NetworkTable.getTable("Gears");
    	table.putBoolean("isGears", isGears);
    	System.out.println(" -- Switching to: " + table.getBoolean("isGears", true));
    }
}
