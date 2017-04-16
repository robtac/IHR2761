
package org.usfirst.frc.team2761.robot;

import org.usfirst.frc.team2761.robot.commands.ChangeCamera;
import org.usfirst.frc.team2761.robot.commands.auto.AutoBaseline;
import org.usfirst.frc.team2761.robot.commands.auto.AutoBlueLeft;
import org.usfirst.frc.team2761.robot.commands.auto.AutoBlueRight;
import org.usfirst.frc.team2761.robot.commands.auto.AutoRedCenter;
import org.usfirst.frc.team2761.robot.commands.auto.AutoRedLeft;
import org.usfirst.frc.team2761.robot.commands.auto.AutoRedRight;
import org.usfirst.frc.team2761.robot.commands.drivetrain.DriveForward;
import org.usfirst.frc.team2761.robot.commands.drivetrain.TankDrive;
import org.usfirst.frc.team2761.robot.subsystems.GearRelease;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	Command autonomousCommand;
	
	AutoRedCenter gearsCenter;
	AutoBaseline baseline;
	AutoBlueLeft blueLeft;
	AutoBlueRight blueRight;
	AutoRedLeft redLeft;
	AutoRedRight redRight;
	DriveForward test;
	
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	double lastAutoTime = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//NetworkTable.setServerMode();
		//NetworkTable.initialize();
		Logger.println("Robot init");
		oi = new OI();
		
		new ChangeCamera(true);
		
		chooser.addDefault("Default Auto", new TankDrive());
		//chooser.addObject("My Auto", new AutoDriveForward());
		SmartDashboard.putData("Auto mode", chooser);
		teleopDrive = new TankDrive();
		
		gearsCenter = new AutoRedCenter();
		baseline = new AutoBaseline();
		blueLeft = new AutoBlueLeft();
		blueRight = new AutoBlueRight();
		redLeft = new AutoRedLeft();
		redRight = new AutoRedRight();
		test = new DriveForward(100);
		
		chooser.addObject("Center Gears", gearsCenter);
		chooser.addObject("Pass Baseline", baseline);
		chooser.addObject("Blue Left", blueLeft);
		chooser.addObject("Blue Right", blueRight);
		chooser.addObject("Red Left", redLeft);
		chooser.addObject("Red Right", redRight);
		chooser.addObject("Test", test);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Logger.println("Robot disabled");
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
		Logger.println("Start of auto");
		autonomousCommand = chooser.getSelected();
		
		lastAutoTime = Timer.getFPGATimestamp();
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
		// Takes image from rpi
		if (lastAutoTime < Timer.getFPGATimestamp() + 1) {
			NetworkTable table = NetworkTable.getTable("gears");
			table.putBoolean("takeImage", true);
			lastAutoTime = Timer.getFPGATimestamp();
		}
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Logger.println("Start of teleop");
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
		System.out.println("isOpen: " + GearRelease.getInstance().isFullOpen() + " -- isClosed: " + GearRelease.getInstance().isFullClosed());
		LiveWindow.run();
	}
}
