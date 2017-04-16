package org.usfirst.frc.team2761.robot.commands.drivetrain;

import org.usfirst.frc.team2761.robot.Logger;
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
public class PivotGyroPID extends Command {

	DriveTrain driveTrain;
	NetworkTable table;
	PIDController speedPIDController, additivePIDController;
	double angle;
	double pivotSpeed, pivotAdditive;
	
    public PivotGyroPID(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	driveTrain.zeroGyro();
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	PIDSource speedSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				return -driveTrain.getAngle();
			}
    	};
    	
    	PIDOutput speedOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				pivotSpeed = output;
			}
    	};
    	
    	PIDSource additiveSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				double sideDifference = driveTrain.getLeftDistance() + driveTrain.getRightDistance();
				return sideDifference;
			}
    	};
    	
    	PIDOutput additiveOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				pivotAdditive = output;
			}
    	};
    	
    	final double kPSpeed = SmartDashboard.getNumber("Pivot Gyro P", RobotMap.defaultPivotGyroSpeedP);
    	final double kISpeed = SmartDashboard.getNumber("Pivot Gyro I", RobotMap.defaultPivotGyroSpeedI);
    	final double kDSpeed = SmartDashboard.getNumber("Pivot Gyro D", RobotMap.defaultPivotGyroSpeedD);
    	final double kPAdditive = SmartDashboard.getNumber("Pivot Gyro P", RobotMap.defaultPivotGyroAdditiveP);
    	final double kIAdditive = SmartDashboard.getNumber("Pivot Gyro I", RobotMap.defaultPivotGyroAdditiveI);
    	final double kDAdditive = SmartDashboard.getNumber("Pivot Gyro D", RobotMap.defaultPivotGyroAdditiveD);
    	
    	speedPIDController = new PIDController(kPSpeed, kISpeed, kDSpeed, speedSource, speedOutput);
    	additivePIDController = new PIDController(kPAdditive, kIAdditive, kDAdditive, additiveSource, additiveOutput);
    	
    	speedPIDController.setAbsoluteTolerance(5);
    	additivePIDController.setAbsoluteTolerance(2);
		
		final double MAX_SPEED = 0.25;
		final double MAX_ADDITIVE = 0.15;
		
		speedPIDController.setOutputRange(-MAX_SPEED, MAX_SPEED);
		additivePIDController.setOutputRange(-MAX_ADDITIVE, MAX_ADDITIVE);
		
		speedPIDController.setToleranceBuffer(5);
		additivePIDController.setToleranceBuffer(2);
		
		speedPIDController.setSetpoint(angle);
		additivePIDController.setSetpoint(0);
		
		speedPIDController.enable();
		additivePIDController.enable();
		
		Logger.println("PivotGyroPID init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("PivotSpeed: " + pivotSpeed + " -- Gyro Angle: " + driveTrain.getAngle() + " -- Error: " + speedPIDController.getError());
    	driveTrain.pivot(pivotSpeed, pivotAdditive);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return speedPIDController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	speedPIDController.disable();
    	speedPIDController.free();
    	additivePIDController.disable();
    	additivePIDController.free();
    	driveTrain.stop();
    	
    	Logger.println("Stopping PivotGyroPID");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
