package ca.team3571.offseason.commands;

import ca.team3571.offseason.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command {

    private static final int SOLENOID_PORT = 0;

    public ClimbCommand() {
        requires(Robot.getInstance().getPneumatics());
    }

    @Override
    public void initialize() {
        if(Robot.getInstance().getPneumatics().getOpenState()) {
            Robot.getInstance().getPneumatics().solenoidReverse(SOLENOID_PORT);
            Robot.getInstance().getPneumatics().setOpenState(false);
        }
        else {
            Robot.getInstance().getPneumatics().solenoidForward(SOLENOID_PORT);
            Robot.getInstance().getPneumatics().setOpenState(true);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
