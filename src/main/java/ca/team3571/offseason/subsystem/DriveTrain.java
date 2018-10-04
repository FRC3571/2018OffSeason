package ca.team3571.offseason.subsystem;

import ca.team3571.offseason.commands.TankDriveCommand;
import ca.team3571.offseason.util.Loggable;
import ca.team3571.offseason.util.RobotMath;
import ca.team3571.offseason.util.XboxController;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem implements Loggable, Refreshable {

    //motor ports
    private static int FRONT_LEFT_DRIVE_MOTOR, MIDDLE_LEFT_DRIVE_MOTOR,
            FRONT_RIGHT_DRIVE_MOTOR, MIDDLE_RIGHT_DRIVE_MOTOR;
    //encoder ports/channels
    private static int FRONT_LEFT_ENCODER_CHANNEL_A,
    FRONT_LEFT_ENCODER_CHANNEL_B,
    FRONT_RIGHT_ENCODER_CHANNEL_A,
    FRONT_RIGHT_ENCODER_CHANNEL_B;

    private static boolean FORWARD_DIRECTION, REVERSE_DIRECTION;
    private static CounterBase.EncodingType ENCODER_TYPE;

    //encoder mapping
    private static int COUNTS_PER_REVOLUTION;
    private static double WHEEL_RADIUS;

    //controller port
    private static int CONTROLLER_PORT;

    //gear ratios
    public static final double GEAR_RATIO_LOW;
    public static final double GEAR_RATIO_HIGH;

    static {
        //initialization
        FRONT_LEFT_DRIVE_MOTOR = 0;
        MIDDLE_LEFT_DRIVE_MOTOR = 1;
        FRONT_RIGHT_DRIVE_MOTOR = 2;
        MIDDLE_RIGHT_DRIVE_MOTOR = 3;

        FRONT_LEFT_ENCODER_CHANNEL_A = 0;
        FRONT_LEFT_ENCODER_CHANNEL_B = 1;
        FRONT_RIGHT_ENCODER_CHANNEL_A = 2;
        FRONT_RIGHT_ENCODER_CHANNEL_B = 3;

        FORWARD_DIRECTION = false;
        REVERSE_DIRECTION = true;
        ENCODER_TYPE = CounterBase.EncodingType.k1X;
        COUNTS_PER_REVOLUTION = 2048;
        WHEEL_RADIUS = 62.5; //in mm

        CONTROLLER_PORT = 0;

        GEAR_RATIO_LOW = 4.6;
        GEAR_RATIO_HIGH = 2.7;
    }


    //left
    private Spark frontLeft;
    private Spark midLeft;
    private SpeedControllerGroup leftController;
    //right
    private Spark frontRight;
    private Spark midRight;
    private SpeedControllerGroup rightController;
    //underlying mechanism
    private DifferentialDrive drive;
    //distance encoders
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    //driver controller
    private XboxController controller;


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDriveCommand(controller));
    }



    public DriveTrain() {
        super();

        //initialize hardware
        frontLeft = new Spark(FRONT_LEFT_DRIVE_MOTOR);
        midLeft = new Spark(MIDDLE_LEFT_DRIVE_MOTOR);

        leftController = new SpeedControllerGroup(frontLeft, midLeft);

        frontRight = new Spark(FRONT_RIGHT_DRIVE_MOTOR);
        midRight = new Spark(MIDDLE_RIGHT_DRIVE_MOTOR);

        rightController = new SpeedControllerGroup(frontRight, midRight);

        drive = new DifferentialDrive(leftController, rightController);

        initializeEncoders();

        //Middle motor may need to travel in opposite direction to others based on gearbox design
        midLeft.setInverted(true);
        midRight.setInverted(true);

        drive(0, 0);
        drive.setSafetyEnabled(false);

        controller = new XboxController(CONTROLLER_PORT);
    }

    /**
     * The log method puts interesting information to the SmartDashboard.
     */
    @Override
    public void log() {
        
    }

    /**
     * Tank style driving for the DriveTrain.
     *
     * @param left
     *            Speed in range [-1,1]
     * @param right
     *            Speed in range [-1,1]
     */
    public void drive(double left, double right) {
        System.out.println(getDistance());
        drive.tankDrive(left, right);
    }

    /**
     * Tank style driving for the DriveTrain.
     *
     * @param xbox The XboxController use to drive tank style.
     */
    public void drive(XboxController xbox) {
        //drive(-xbox.getY(GenericHID.Hand.kLeft), xbox.getY(GenericHID.Hand.kRight));
        drive(-xbox.LeftStick.Y, -xbox.RightStick.Y);
    }

    /**
     * Reset the robots sensors to the zero states.
     */
    public void reset() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Get the average distance of the encoders since the last reset.
     *
     * @return The distance driven (average of leftControllerand rightControllerencoders).
     */
    public double getDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
    }

    //will be replace by gyro!

    public double getTurnDistance() {
        return (Math.abs(leftEncoder.getDistance()) + Math.abs(rightEncoder.getDistance()))/2;
    }

    private void initializeEncoders() {
        leftEncoder = new Encoder(FRONT_LEFT_ENCODER_CHANNEL_A,
                FRONT_LEFT_ENCODER_CHANNEL_B,
                REVERSE_DIRECTION,
                ENCODER_TYPE);

        rightEncoder = new Encoder(FRONT_RIGHT_ENCODER_CHANNEL_A,
                FRONT_RIGHT_ENCODER_CHANNEL_B,
                FORWARD_DIRECTION,
                ENCODER_TYPE);

        final double encoderLinearDistancePerPulse = RobotMath.getDistancePerPulse(COUNTS_PER_REVOLUTION, WHEEL_RADIUS);

        leftEncoder.setDistancePerPulse(encoderLinearDistancePerPulse);
        rightEncoder.setDistancePerPulse(encoderLinearDistancePerPulse);
    }

    @Override
    public void refresh() {
        controller.refresh();
        drive(controller);
    }
}
