package ca.team3571.offseason.subsystem;

import ca.team3571.offseason.Robot;
import ca.team3571.offseason.util.Loggable;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tilt extends Subsystem implements Loggable, Refreshable {

    private static int ENCODER_CHANNEL_A;
    private static int ENCODER_CHANNEL_B;

    private static int MOTOR_PORT = 5;

    private boolean isReverse = false;

    private Encoder encoder;
    private Spark motor;

    public Tilt() {
        encoder = new Encoder(ENCODER_CHANNEL_A, ENCODER_CHANNEL_B, isReverse, CounterBase.EncodingType.k1X);
        motor = new Spark(MOTOR_PORT);
    }

    @Override
    public void refresh() {
        //TODO do real-time calculation && movements here
        motor.setSpeed(Robot.getInstance().getSubsystemController().RightStick.Y);
    }

    @Override
    public void log() {

    }

    @Override
    protected void initDefaultCommand() {

    }
}
