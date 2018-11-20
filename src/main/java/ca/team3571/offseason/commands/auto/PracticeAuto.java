package ca.team3571.offseason.commands.auto;

import ca.team3571.offseason.commands.DriveStraightDistance;
import ca.team3571.offseason.commands.TurnWithDegrees;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PracticeAuto extends CommandGroup {

    public PracticeAuto() {
        addSequential(new TurnWithDegrees(-3));
        addSequential(new DriveStraightDistance(3500, 0.5));
        addSequential(new TurnWithDegrees(-47));

    }

    @Override
    protected void initialize() {
        System.out.println("Executing practice autonomous");
    }
}
