package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.commands.auto.Drive;
import org.usfirst.frc.team2761.robot.commands.auto.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Gears extends CommandGroup {

    public Gears() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new ZeroEncoders());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new Drive(50));
    	addSequential(new ZeroEncoders());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new PivotTurn(1));
    	addSequential(new ZeroEncoders());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new Drive(20));
    	addSequential(new ZeroEncoders());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new GearMovePID());
    	addSequential(new ZeroEncoders());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new Drive(20));
    }
}
