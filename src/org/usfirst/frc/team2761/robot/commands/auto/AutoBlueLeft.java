package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.commands.ChangeCamera;
import org.usfirst.frc.team2761.robot.commands.drivetrain.*;
import org.usfirst.frc.team2761.robot.commands.gearrelease.GearReleaseOpen;
import org.usfirst.frc.team2761.robot.commands.paddle.RunPaddleGlobal;
import org.usfirst.frc.team2761.robot.commands.shooter.ShootPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeft extends CommandGroup {

    public AutoBlueLeft() {
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
    	addSequential(new ChangeCamera(true));
    	
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ForwardEncoderPID(63));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new PivotGyroPID(38));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
//    	addSequential(new ForwardEncoderPID(40));
//    	addSequential(new ZeroEncoders());
//    	addSequential(new ZeroGyro());
//    	addSequential(new Wait(0.01));
    	
    	addSequential(new Wait(0.1));
    	
    	addSequential(new ChangeCamera(true));
    	addSequential(new GearMovePID());
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ForwardEncoderPID(4));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new GearReleaseOpen());
    	
    	addParallel(new ShootPID());
    	
    	addSequential(new ForwardEncoderPID(-20));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ChangeCamera(false));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.2));
//    	
//    	GearAlignBoilerPID a = new GearAlignBoilerPID();
//    	addParallel(a);
    	addParallel(new GearAlignBoilerPID());
    	addParallel(new RunPaddleGlobal());
    }
}
