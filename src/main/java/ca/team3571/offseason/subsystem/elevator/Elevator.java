package ca.team3571.offseason.subsystem.elevator;

import ca.team3571.offseason.Robot;
import ca.team3571.offseason.subsystem.Refreshable;
import ca.team3571.offseason.util.Loggable;
import ca.team3571.offseason.util.RobotMath;
import ca.team3571.offseason.util.XboxController;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Elevator extends Subsystem implements Loggable, Refreshable {
    //motor ports
    private static int FIRST_ELEVATOR_PORT;
    private static int SECOND_ELEVATOR_PORT;
    private static int THIRD_ELEVATOR_PORT;
    private static int FOURTH_ELEVATOR_PORT;

    //encoder ports/channels
    private static int ELEVATOR_ENCODER_CHANNEL_A;
    private static int ELEVATOR_ENCODER_CHANNEL_B;

    private static boolean FORWARD_DIRECTION, REVERSE_DIRECTION;
    private static CounterBase.EncodingType ENCODER_TYPE;

    //encoder mapping
    private static int COUNTS_PER_REVOLUTION;
    private static double WHEEL_RADIUS;

    //limit switch mapping
    private static int LIMIT_SWITCH_BOTTOM;
    private static int LIMIT_SWITCH_MIDDLE;
    private static int LIMIT_SWITCH_TOP;

    //controller port
    private static int CONTROLLER_PORT;

    static {
        ELEVATOR_ENCODER_CHANNEL_B = 1;  // DIO = 1

        FORWARD_DIRECTION = false;
        REVERSE_DIRECTION = true;
        ENCODER_TYPE = CounterBase.EncodingType.k1X;
        COUNTS_PER_REVOLUTION = 2048;
        WHEEL_RADIUS = 62.5; //in mm

        LIMIT_SWITCH_BOTTOM = 2;    //DIO = 2
        LIMIT_SWITCH_MIDDLE = 3;    //DIO = 3
        LIMIT_SWITCH_TOP = 4;       //DIO = 4

        CONTROLLER_PORT = 0;
    }

    //elevator
    //motors
    private Spark FIRST_ELEVATOR_MOTOR = new Spark(FIRST_ELEVATOR_PORT);
    private Spark SECOND_ELEVATOR_MOTOR = new Spark(SECOND_ELEVATOR_PORT);
    private Spark THIRD_ELEVATOR_MOTOR = new Spark(THIRD_ELEVATOR_PORT);
    private Spark FOURTH_ELEVATOR_MOTOR = new Spark(FOURTH_ELEVATOR_PORT);

    //Speed Controller
    private SpeedControllerGroup motors =
            new SpeedControllerGroup(FIRST_ELEVATOR_MOTOR, SECOND_ELEVATOR_MOTOR, THIRD_ELEVATOR_MOTOR, FOURTH_ELEVATOR_MOTOR);
    //encoder
    private Encoder elevatorEncoder;
    //driver controller
    private XboxController controller;

    public Elevator() {

        //initialize hardware
        initializeEncoders();
        controller = Robot.getInstance().getSubsystemController();
    }

    @Override
    public void refresh() {
        //run elevator code here
        motors.set(controller.LeftStick.Y);
    }

    @Override
    public void log() {
       // SmartDashboard.putNumber("Elevator motor", ???? );

    }

    @Override
    protected void initDefaultCommand() {

    }

    private void initializeEncoders() {
        elevatorEncoder = new Encoder(ELEVATOR_ENCODER_CHANNEL_A,
                ELEVATOR_ENCODER_CHANNEL_B,
                REVERSE_DIRECTION,
                ENCODER_TYPE);

        final double encoderLinearDistancePerPulse = RobotMath.getDistancePerPulse(COUNTS_PER_REVOLUTION, WHEEL_RADIUS);

        elevatorEncoder.setDistancePerPulse(encoderLinearDistancePerPulse);
    }
}
