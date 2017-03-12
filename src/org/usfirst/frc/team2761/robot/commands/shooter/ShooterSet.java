package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSet extends Command {

	Shooter shooter;
	
	// Constructor for the command
    public ShooterSet() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);    	
    	shooter = Shooter.getInstance();
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shooterP = SmartDashboard.getNumber("P", RobotMap.shooterP);
    	RobotMap.shooterI = SmartDashboard.getNumber("I", RobotMap.shooterI);
    	RobotMap.shooterD = SmartDashboard.getNumber("D", RobotMap.shooterD);
    	RobotMap.shooterSpeed = SmartDashboard.getNumber("ShooterSpeed", RobotMap.shooterD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//shooter.printSpeed();
    	RobotMap.shooterP = SmartDashboard.getNumber("P", 0.020);
    	RobotMap.shooterI = SmartDashboard.getNumber("I", 0.000);
    	RobotMap.shooterD = SmartDashboard.getNumber("D", 0.000);
    	RobotMap.shooterSpeed = SmartDashboard.getNumber("ShooterSpeed", -4500);
    	
    	shooter.setPIDShoot();
//    	System.out.println("P" + RobotMap.shooterP);
//    	System.out.println("I" + RobotMap.shooterI);
//    	System.out.println("D" + RobotMap.shooterD);

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