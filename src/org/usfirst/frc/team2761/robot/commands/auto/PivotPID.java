package org.usfirst.frc.team2761.robot.commands.auto;

import org.usfirst.frc.team2761.robot.RobotMap;
import org.usfirst.frc.team2761.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PivotPID extends Command {

	DriveTrain driveTrain;
	Boolean isTableValid;
	NetworkTable table;
	PIDController pidController;
	double angle;
	
    public PivotPID(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	driveTrain.zeroEncoders();
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	PIDSource source = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				double inchesTurned = (driveTrain.getLeftDistance() - driveTrain.getRightDistance()) / 2;
				double turnCircumference = 22 * Math.PI;
				double currentAngle = inchesTurned / turnCircumference * 360;
				System.out.println("Current Angle: " + currentAngle);
				return currentAngle;
			}
    	};
    	
    	PIDOutput output = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				driveTrain.pivot(-output);
			}
    	};
    	
    	final double kP = SmartDashboard.getNumber("Pivot P", RobotMap.defaultPivotP);
    	final double kI = SmartDashboard.getNumber("Pivot I", RobotMap.defaultPivotI);
    	final double kD = SmartDashboard.getNumber("Pivot D", RobotMap.defaultPivotD);
    	
    	
    	pidController = new PIDController(kP, kI, kD, source, output);
    	
    	pidController.setAbsoluteTolerance(2);
		
		final double MAX_SPEED = 0.25;
		
		pidController.setOutputRange(-MAX_SPEED, MAX_SPEED);
		
		pidController.setToleranceBuffer(2);
		
		pidController.setSetpoint(angle);
		
		pidController.enable();
		
		System.out.println("Vision P: " + kP);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Error: " + pidController.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pidController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pidController.disable();
    	pidController.free();
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
