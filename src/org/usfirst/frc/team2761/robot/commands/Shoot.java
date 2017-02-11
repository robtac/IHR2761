package org.usfirst.frc.team2761.robot.commands;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	Shooter shooter;
	public static final double ENCODERVELOCITY = 60.0 * 10.0 / 4096.0;
	
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
    	int motorVelocity = (int) (shooter.shooterMotor.getPulseWidthVelocity() * ENCODERVELOCITY);
    	System.out.println("Is executing at: " + motorVelocity + " rpm");
    	shootRPM();
    }
    
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
    	shooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.setSpeed(0);
    }
}
