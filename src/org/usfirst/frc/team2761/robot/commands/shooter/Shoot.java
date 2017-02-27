package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	Shooter shooter;
	
	// Constructor for the command
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooter = Shooter.getInstance();
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.printSpeed();
    	//shootRPM();
    	shootSpeed();
    }
    
    // Sets the speed of the shooter motor
    private void shootSpeed() {
    	shooter.setSpeed(RobotMap.shooterSpeed);
    }
    
    // Sets speed of the motor based on RPM
    private void shootRPM() {
    	double rpm = RobotMap.shooterSpeed;
    	double shootPercentage = rpm / 5000;
    	shooter.setSpeed(shootPercentage);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.stop();
    }
}
