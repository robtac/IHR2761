package org.usfirst.frc.team2761.robot.commands.shooter;

import org.usfirst.frc.team2761.robot.Logger;
import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShootPID extends Command {

	Shooter shooter;
	PIDController pidController;
	
    public ShootPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooter = Shooter.getInstance();
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	PIDSource encSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kRate;
			}

			@Override
			public double pidGet() {
				return shooter.getSpeed();
			}
    	};
    	
    	PIDOutput driveOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				shooter.setVBus(output);
			}
    	};
    	
    	final double kP = SmartDashboard.getNumber("Shooter P", RobotMap.shooterP) / 1000;
    	final double kI = SmartDashboard.getNumber("Shooter I", RobotMap.shooterI);
    	final double kD = SmartDashboard.getNumber("Shooter D", RobotMap.shooterD);
    	
    	
    	pidController = new PIDController(kP, kI, kD, encSource, driveOutput);
    	
    	pidController.setInputRange(0, 6000);
    	
    	pidController.setAbsoluteTolerance(10);
		
		final double MIN_SPEED = 0;
		final double MAX_SPEED = 1;
		
		pidController.setOutputRange(MIN_SPEED, MAX_SPEED);
		
		pidController.setToleranceBuffer(1);
		
		double speed = SmartDashboard.getNumber("ShooterSpeed", 4300);
		pidController.setSetpoint(speed);
		
		pidController.enable();
		Logger.println("ShootPID init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.printSpeed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pidController.disable();
    	pidController.free();
    	shooter.stop();
    	Logger.println("Stopping ShootPID");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
