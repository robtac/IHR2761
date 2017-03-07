package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.ShooterAngle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterAngleYSetPID extends Command {

	ShooterAngle shooterAngle;
	
	// Constructor for the command
    public ShooterAngleYSetPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);    	
    	shooterAngle = ShooterAngle.getInstance();
    	requires(shooterAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shooterAngleYP = SmartDashboard.getNumber("P");
    	RobotMap.shooterAngleYI = SmartDashboard.getNumber("I");
    	RobotMap.shooterAngleYD = SmartDashboard.getNumber("D");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//shooter.printSpeed();
    	RobotMap.shooterAngleYP = SmartDashboard.getNumber("P");
    	RobotMap.shooterAngleYI = SmartDashboard.getNumber("I");
    	RobotMap.shooterAngleYD = SmartDashboard.getNumber("D");
    	
    	shooterAngle.setPIDAngleY();

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
