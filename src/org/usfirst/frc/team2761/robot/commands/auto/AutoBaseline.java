package org.usfirst.frc.team2761.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.commands.drivetrain.*;

/**
 *
 */
public class AutoBaseline extends CommandGroup {

    public AutoBaseline() {
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
    	Logger.println("Starting auto baseline");
    	
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	addSequential(new ForwardEncoderPID(200));
    }
}
