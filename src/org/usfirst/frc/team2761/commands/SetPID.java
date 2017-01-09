package org.usfirst.frc.team2761.commands;

import org.usfirst.frc.team2761.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetPID extends Command {

    public SetPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.ANGLE_MOTOR_P = SmartDashboard.getNumber("P");
    	RobotMap.ANGLE_MOTOR_I = SmartDashboard.getNumber("I");
    	RobotMap.ANGLE_MOTOR_D = SmartDashboard.getNumber("D");
//    	System.out.println("P" + RobotMap.ANGLE_MOTOR_P);
//    	System.out.println("I" + RobotMap.ANGLE_MOTOR_I);
//    	System.out.println("D" + RobotMap.ANGLE_MOTOR_D);

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