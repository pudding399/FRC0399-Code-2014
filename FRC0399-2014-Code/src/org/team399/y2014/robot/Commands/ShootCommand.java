/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team399.y2014.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team399.y2014.robot.Config.Constants;
import org.team399.y2014.robot.Systems.Robot;
import org.team399.y2014.robot.Systems.Shooter;

/**
 * Command to shoot a ball
 *
 * @author jeremy.germita@gmail.com (Jeremy Germita)
 */
public class ShootCommand extends Command {

    private double timeout = 0.0;

    public ShootCommand(double timeout) {
        this.timeout = timeout;
    }

    protected void initialize() {
        this.setTimeout(timeout);
        Robot.getInstance().shooter.setState(Shooter.States.SHOOT);
        
        if(Robot.getInstance().intake.state 
                == Constants.Intake.RETRACTED) {
            Robot.getInstance().intake.setActuators(
                    Constants.Intake.EXTENDED);
        }
        
        Robot.getInstance().comp.stop();
    }

    protected void execute() {
        Robot.getInstance().shooter.run();
    }

    protected void end() {
        Robot.getInstance().shooter.setState(Shooter.States.STAGE);
        //Robot.getInstance().comp.start();
        Robot.getInstance().shooter.setOutput(0.0);
    }
    
    protected void interrupted() {
        
    }

    protected boolean isFinished() {
        return Robot.getInstance().shooter.getShootDone() || this.isTimedOut();
    }
}
