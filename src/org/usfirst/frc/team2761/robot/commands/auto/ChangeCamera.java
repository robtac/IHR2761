package org.usfirst.frc.team2761.robot.commands.auto;

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
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        isGears = val;
    }

    // Called once when the command executes
    protected void initialize() {
    	table = NetworkTable.getTable("Gears");
    	table.putBoolean("isGears", isGears);
    }
}
