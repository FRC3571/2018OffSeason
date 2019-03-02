package ca.team3571.offseason.commands;

import ca.team3571.offseason.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class OpenCloseCommand extends Command {
    private static final int SOLENOID_PORT = 1;
    private static final int FIRST_ID = 1;
    private static final int SECOND_ID = 6;

    public OpenCloseCommand() {
        requires(Robot.getInstance().getPneumatics());
        //intake
        Robot.getInstance().getPneumatics().createSolenoid(SOLENOID_PORT, FIRST_ID, SECOND_ID);
    }

    @Override
    public void initialize() {
        if(Robot.getInstance().getPneumatics().getIntakeOpenState()) {
            Robot.getInstance().getPneumatics().solenoidReverse(SOLENOID_PORT);
            Robot.getInstance().getPneumatics().setIntakeOpenState(false);
        }
        else {
            Robot.getInstance().getPneumatics().solenoidForward(SOLENOID_PORT);
            Robot.getInstance().getPneumatics().setIntakeOpenState(true);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
