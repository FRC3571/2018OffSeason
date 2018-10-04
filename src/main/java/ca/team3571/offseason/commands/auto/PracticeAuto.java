package ca.team3571.offseason.commands.auto;

import ca.team3571.offseason.commands.DriveStraightDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PracticeAuto extends CommandGroup {

    public PracticeAuto() {
        addSequential(new DriveStraightDistance(4, 0.5));
    }

    @Override
    protected void initialize() {
        System.out.println("Executing practice autonomous");
    }

}
