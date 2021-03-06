package ca.team3571.offseason.commands;

import ca.team3571.offseason.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LiftManualCommand extends Command {
    private static final double LIFT_SPEED = 0.6;
    private double speed;

    public LiftManualCommand(boolean up) {
        requires(Robot.getInstance().getElevator());
        if(up) {
            speed = LIFT_SPEED;
        }
        else {
            speed = -LIFT_SPEED;
        }
    }

    @Override
    protected void initialize() {
        Robot.getInstance().getElevator().getElevatorMotor().setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void end() {
        Robot.getInstance().getElevator().getElevatorMotor().setSpeed(0);
    }
}
