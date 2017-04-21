package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.commands.ChangeCamera;
import org.usfirst.frc.team2761.robot.commands.climber.ReleaseClimber;
import org.usfirst.frc.team2761.robot.commands.drivetrain.*;
import org.usfirst.frc.team2761.robot.commands.gearrelease.GearReleaseOpen;
import org.usfirst.frc.team2761.robot.commands.paddle.RunPaddleGlobal;
import org.usfirst.frc.team2761.robot.commands.shooter.ShootPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedCenterShoot extends CommandGroup {

    public AutoRedCenterShoot() {
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
    	Logger.println("Starting auto red center shoot");
    	
    	addSequential(new ChangeCamera(true));
    	
    	addParallel(new ReleaseClimber());
    	
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ForwardEncoderPID(20));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ChangeCamera(true));
    	addSequential(new Wait(0.2));
    	addSequential(new GearMovePID());
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ForwardEncoderPID(4));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new GearReleaseOpen());
    	
    	addSequential(new ForwardEncoderPID(-40));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addParallel(new ShootPID());
    	
    	addSequential(new PivotGyroPID(50));
    	addSequential(new ZeroEncoders());
    	addSequential(new ZeroGyro());
    	addSequential(new Wait(0.01));
    	
    	addSequential(new ForwardEncoderPID(-18));
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
    	addParallel(new BoilerAlignPID());
    	addParallel(new RunPaddleGlobal());
    }
}
