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
    private static int ELEVATOR_PORT = 2;

    //encoder ports/channels
    private static int ELEVATOR_ENCODER_CHANNEL_A = 0;
    private static int ELEVATOR_ENCODER_CHANNEL_B = 1;

    private static boolean FORWARD_DIRECTION, REVERSE_DIRECTION;
    private static CounterBase.EncodingType ENCODER_TYPE;

    //encoder mapping
    private static int COUNTS_PER_REVOLUTION;
    private static double WHEEL_RADIUS;

    //limit switch mapping
    private static int LIMIT_SWITCH_BOTTOM;

    //controller port
    private static int CONTROLLER_PORT;

    static {

        FORWARD_DIRECTION = false;
        REVERSE_DIRECTION = true;

        ENCODER_TYPE = CounterBase.EncodingType.k1X;
        COUNTS_PER_REVOLUTION = 2048;
        WHEEL_RADIUS = 47.75; //in mm

        LIMIT_SWITCH_BOTTOM = 2;    //DIO = 2

        CONTROLLER_PORT = 0;
    }

    //elevator
    //motors
    private Spark elevatorMotor = new Spark(ELEVATOR_PORT);

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
        //elevatorMotor.set(controller.LeftStick.Y);
    }

    @Override
    public void log() {
       // SmartDashboard.putNumber("Elevator motor", ???? );
        System.out.println(elevatorEncoder.getDistance());
    }

    @Override
    protected void initDefaultCommand() {

    }

    public Spark getElevatorMotor() {
        return elevatorMotor;
    }

    public Encoder getDistanceEncoder() {
        return elevatorEncoder;
    }

    public double getDistance() {
        return elevatorEncoder.getDistance();
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
