/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team399.y2014.robot.Systems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import org.team399.y2014.Utilities.PulseTriggerBoolean;
import org.team399.y2014.robot.Config.Constants;

/**
 * Contains functions related to operation of intake system.
 *
 * @author (jeremy.germita@gmail.com) Jeremy Germita
 * @author ivansalazar003@gmail.com (Ivan Salazar)
 */
public class Intake {

    private Talon m_intakeA = null;
    private Solenoid m_solA = null;
    private Solenoid m_solB = null;

    public boolean state = Constants.Intake.RETRACTED;
    public double output = 0.0;
    /**
     * Constructor.
     *
     * @param motor Motor controller PWM port
     * @param sA Solenoid port A
     * @param sB Solenoid port B
     */
    public Intake(int motor, int sA, int sB) {
        m_intakeA = new Talon(motor);
        m_solA = new Solenoid(sA);
        m_solB = new Solenoid(sB);

    }

    /**
     * Sets motor outputs
     *
     * @param input
     */
    public void setMotors(double input) {
        output  = input;
        m_intakeA.set(input);
    }
    private boolean prevState = false;

    private PulseTriggerBoolean toggle = new PulseTriggerBoolean();

    /**
     * Operator-friendly toggle control. Pulse input for position change
     *
     * @param input button input
     */
    public void setToggle(boolean input) {
        toggle.set(input);
        if (toggle.get()) {
            setActuators(!this.state);
        }

    }

    /**
     *
     * @param state
     */
    public void setActuators(boolean state) {
        this.prevState = this.state;
        this.state = state;
        if (safety) {
            m_solA.set(this.state);
            m_solB.set(this.state);
        }
    }
    private long startTime = 0;

    private boolean safety = true;  // Intake safety flag, true to allow movement

    public void setIntakeSafety(boolean safety) {
        this.safety = safety;
    }

}
