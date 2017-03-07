package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.Shooter;
import org.usfirst.frc.team2761.robot.subsystems.ShooterAngle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterAngleXSetPID extends Command {

	ShooterAngle shooterAngle;
	
	// Constructor for the command
    public ShooterAngleXSetPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);    	
    	shooterAngle = ShooterAngle.getInstance();
    	requires(shooterAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shooterAngleXP = SmartDashboard.getNumber("P");
    	RobotMap.shooterAngleXI = SmartDashboard.getNumber("I");
    	RobotMap.shooterAngleXD = SmartDashboard.getNumber("D");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//shooter.printSpeed();
    	RobotMap.shooterAngleXP = SmartDashboard.getNumber("P");
    	RobotMap.shooterAngleXI = SmartDashboard.getNumber("I");
    	RobotMap.shooterAngleXD = SmartDashboard.getNumber("D");
    	
    	shooterAngle.setPIDAngleX();

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
