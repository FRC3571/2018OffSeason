package ca.team3571.offseason.subsystem;

import ca.team3571.offseason.util.Loggable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem implements Loggable, Refreshable {

    private static int FIRST_MOTOR_PORT;
    private static int SECOND_MOTOR_PORT;

    private Spark firstMotor;
    private Spark secondMotor;

    public Intake() {
        firstMotor = new Spark(FIRST_MOTOR_PORT);
        secondMotor = new Spark(SECOND_MOTOR_PORT);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void log() {

    }

    @Override
    protected void initDefaultCommand() {

    }
}
