package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.ShooterAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterAngleSet extends Command {

	ShooterAngle shooterAngle;
	int location;
	boolean isFinished = false;
	
	// Constructor for the command
    public ShooterAngleSet(int l) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooterAngle = ShooterAngle.getInstance();
    	requires(shooterAngle);
    	location = l;
    	System.out.println("D-Pad input: " + l);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooterAngle.printValues();
    	setPosition();
    }

    // Starts the movement of the shooter angle motors
    private void setPosition() {
    	boolean limitX1 = shooterAngle.getMagHallX1();
    	boolean limitX2 = shooterAngle.getMagHallX2();
    	boolean limitY1 = shooterAngle.getMagHallY1();
    	boolean limitY2 = shooterAngle.getMagHallY2();
    	switch (location) {
        case 1: if (!limitY1)
        			shooterAngle.setPositionY(RobotMap.shooterAngleYShift);
                break;
        case 2: if (!limitX2) shooterAngle.setPositionX(RobotMap.shooterAngleXShift);
        		if (!limitY2) shooterAngle.setPositionY(RobotMap.shooterAngleYShift);
                break;
        case 3: if (!limitX2) shooterAngle.setPositionX(RobotMap.shooterAngleXShift);
                break;
        case 4: if (!limitX2) shooterAngle.setPositionX(RobotMap.shooterAngleXShift);
        		if (!limitY1) shooterAngle.setPositionY(-RobotMap.shooterAngleYShift);
        		break;
        case 5: if (!limitY1) shooterAngle.setPositionY(-RobotMap.shooterAngleYShift);
                break;
        case 6: if (!limitX1) shooterAngle.setPositionX(-RobotMap.shooterAngleXShift);
        		if (!limitY1) shooterAngle.setPositionY(-RobotMap.shooterAngleYShift);
                break;
        case 7: if (!limitX1) shooterAngle.setPositionX(-RobotMap.shooterAngleXShift);
                break;
        case 8: if (!limitX1) shooterAngle.setPositionX(-RobotMap.shooterAngleXShift);
        		if (!limitY2) shooterAngle.setPositionY(RobotMap.shooterAngleYShift);
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
