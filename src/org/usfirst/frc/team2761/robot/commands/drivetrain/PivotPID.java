package org.usfirst.frc.team2761.robot.commands.drivetrain;

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
	PIDController speedPIDController, additivePIDController;
	double angle;
	double pivotSpeed, pivotAdditive;
	
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
    	PIDSource speedSource = new PIDSource() {
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
				return currentAngle;
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
    	
    	final double kPSpeed = SmartDashboard.getNumber("Pivot P", RobotMap.defaultPivotSpeedP);
    	final double kISpeed = SmartDashboard.getNumber("Pivot I", RobotMap.defaultPivotSpeedI);
    	final double kDSpeed = SmartDashboard.getNumber("Pivot D", RobotMap.defaultPivotSpeedD);
    	final double kPAdditive = SmartDashboard.getNumber("Pivot P", RobotMap.defaultPivotAdditiveP);
    	final double kIAdditive = SmartDashboard.getNumber("Pivot I", RobotMap.defaultPivotAdditiveI);
    	final double kDAdditive = SmartDashboard.getNumber("Pivot D", RobotMap.defaultPivotAdditiveD);
    	
    	speedPIDController = new PIDController(kPSpeed, kISpeed, kDSpeed, speedSource, speedOutput);
    	additivePIDController = new PIDController(kPAdditive, kIAdditive, kDAdditive, additiveSource, additiveOutput);
    	
    	speedPIDController.setAbsoluteTolerance(5);
    	additivePIDController.setAbsoluteTolerance(2);
		
		final double MAX_SPEED = 0.35;
		final double MAX_ADDITIVE = 0.15;
		
		speedPIDController.setOutputRange(-MAX_SPEED, MAX_SPEED);
		additivePIDController.setOutputRange(-MAX_ADDITIVE, MAX_ADDITIVE);
		
		speedPIDController.setToleranceBuffer(2);
		additivePIDController.setToleranceBuffer(2);
		
		speedPIDController.setSetpoint(angle);
		additivePIDController.setSetpoint(0);
		
		speedPIDController.enable();
		additivePIDController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
