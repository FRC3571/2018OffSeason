package ca.team3571.offseason.commands;

import ca.team3571.offseason.Robot;
import ca.team3571.offseason.subsystem.DriveTrain;
import ca.team3571.offseason.util.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TankDriveCommand extends Command {

    private DriveTrain driveTrain;
    private XboxController controller;

    public TankDriveCommand(XboxController controller) {
        this.driveTrain = Robot.getInstance().getDrive();
        this.controller = controller;
        requires(driveTrain);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        driveTrain.drive(controller);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        driveTrain.drive(0, 0);
    }
}
