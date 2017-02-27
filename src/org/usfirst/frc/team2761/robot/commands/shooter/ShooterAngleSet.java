package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.subsystems.Shooter;
import org.usfirst.frc.team2761.robot.subsystems.ShooterAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterAngleSet extends Command {

	ShooterAngle shooterAngle;
	int location;
	double MOVEMENT = 0.05;
	boolean isFinished = false;
	
	// Constructor for the command
    public ShooterAngleSet(int l) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooterAngle = ShooterAngle.getInstance();
    	requires(shooterAngle);
    	location = l;
    	System.out.println("Button: " + location);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Button: " + location);
    	//setPosition();
    }

    // Starts the movement of the shooter angle motors
    private void setPosition() {
    	switch (location) {
        case 1: shooterAngle.setPositionX(MOVEMENT);
                break;
        case 2: shooterAngle.setPositionX(MOVEMENT);
        		shooterAngle.setPositionX(MOVEMENT);
                break;
        case 3: shooterAngle.setPositionX(MOVEMENT);
                break;
        case 4: shooterAngle.setPositionX(MOVEMENT);
        		shooterAngle.setPositionX(MOVEMENT);
        		break;
        case 5: shooterAngle.setPositionX(MOVEMENT);
                break;
        case 6: shooterAngle.setPositionX(MOVEMENT);
        		shooterAngle.setPositionX(MOVEMENT);
                break;
        case 7: shooterAngle.setPositionX(MOVEMENT);
                break;
        case 8: shooterAngle.setPositionX(MOVEMENT);
        		shooterAngle.setPositionX(MOVEMENT);
                break;
        default: shooterAngle.setPositionX(0);
                 break;
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterAngle.setPositionX(0);
    	shooterAngle.setPositionY(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooterAngle.setPositionX(0);
    	shooterAngle.setPositionY(0);
    }
}
