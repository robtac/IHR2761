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
public class ForwardEncoderPID extends Command {

	DriveTrain driveTrain;
	Boolean isTableValid;
	NetworkTable table;
	PIDController speedPIDController, additivePIDController;
	double distance;
	double pivotSpeed, pivotAdditive;
	
    public ForwardEncoderPID(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	driveTrain.zeroEncoders();
    	this.distance = distance;
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
				return driveTrain.getDistance();
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
				double sideDifference = driveTrain.getLeftDistance() - driveTrain.getRightDistance();
				return sideDifference;
			}
    	};
    	
    	PIDOutput additiveOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				pivotAdditive = output;
			}
    	};
    	
    	final double kPSpeed = SmartDashboard.getNumber("Forward Speed P", RobotMap.defaultForwardSpeedP);
    	final double kISpeed = SmartDashboard.getNumber("Forward Speed I", RobotMap.defaultForwardSpeedI);
    	final double kDSpeed = SmartDashboard.getNumber("Forward Speed D", RobotMap.defaultForwardSpeedD);
    	final double kPAdditive = SmartDashboard.getNumber("Forward Speed P", RobotMap.defaultForwardAdditiveP);
    	final double kIAdditive = SmartDashboard.getNumber("Forward Speed I", RobotMap.defaultForwardAdditiveI);
    	final double kDAdditive = SmartDashboard.getNumber("Forward Speed D", RobotMap.defaultForwardAdditiveD);
    	
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
		
		speedPIDController.setSetpoint(distance);
		additivePIDController.setSetpoint(0);
		
		speedPIDController.enable();
		additivePIDController.enable();
		
		Logger.println("Forward Encoder PID init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.forward(speedPIDController.get(), additivePIDController.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return speedPIDController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	speedPIDController.disable();
    	additivePIDController.disable();
    	speedPIDController.free();
    	additivePIDController.free();
    	driveTrain.stop();
    	Logger.println("Stopping ForwardEncoderPID");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
