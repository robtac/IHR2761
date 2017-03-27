
package org.usfirst.frc.team2761.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2761.robot.commands.TankDrive;
import org.usfirst.frc.team2761.robot.commands.auto.*;
import org.usfirst.frc.team2761.robot.commands.shooter.ShooterCalibrate;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	NetworkTable table;
	
	public static OI oi;
	Command teleopDrive;

	ShooterCalibrate shooterCalibrate;
	Command autonomousCommand;
	
	AutoCenterGears gearsCenter;
	AutoBaseline baseline;
	AutoBlueLeft blueLeft;
	AutoBlueRight blueRight;
	AutoRedLeft redLeft;
	AutoRedRight redRight;
	DumbBaseline dumbBaseline;
	DumbCenterGears dumbCenterGears;
	DriveForward test;
	
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//NetworkTable.setServerMode();
		//NetworkTable.initialize();
		shooterCalibrate = new ShooterCalibrate();
		
		oi = new OI();
		chooser.addDefault("Default Auto", new TankDrive());
		//chooser.addObject("My Auto", new AutoDriveForward());
		SmartDashboard.putData("Auto mode", chooser);
		teleopDrive = new TankDrive();
		
		gearsCenter = new AutoCenterGears();
		baseline = new AutoBaseline();
		blueLeft = new AutoBlueLeft();
		blueRight = new AutoBlueRight();
		redLeft = new AutoRedLeft();
		redRight = new AutoRedRight();
		dumbBaseline = new DumbBaseline();
		dumbCenterGears = new DumbCenterGears();
		test = new DriveForward(100);
		
		chooser.addObject("Center Gears", gearsCenter);
		chooser.addObject("Pass Baseline", baseline);
		chooser.addObject("Blue Left", blueLeft);
		chooser.addObject("Blue Right", blueRight);
		chooser.addObject("Red Left", redLeft);
		chooser.addObject("Red Right", redRight);
		chooser.addObject("Dumb Baseline", dumbBaseline);
		chooser.addObject("Dumb Center Gears", dumbCenterGears);
		chooser.addObject("Test", test);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		// schedule the autonomous command (example)
		//shooterCalibrate.start();
		if (autonomousCommand != null)
			autonomousCommand.start();
		//mainAuto.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
		teleopDrive.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
