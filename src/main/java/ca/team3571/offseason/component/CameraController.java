package ca.team3571.offseason.component;


public class CameraController {

    private RobotCamera frontCamera;
    private RobotCamera rearCamera;

    public CameraController(RobotCamera frontCamera, RobotCamera rearCamera) {
        this.frontCamera = frontCamera;
        this.rearCamera = rearCamera;
    }

    public void begin() {
        //default with front and rear prioritized
        frontCamera.setAsPriority();
        rearCamera.setAsPriority();
    }

    public void setFrontPriority() {
        frontCamera.setAsPriority();
        rearCamera.removePriority();
    }

    public void setRearPriority() {
        rearCamera.setAsPriority();
        frontCamera.removePriority();
    }
}
