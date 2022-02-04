/************************ PROJECT DORCAS ************************/
/* Copyright (c) 2022 StuyPulse Robotics. All rights reserved.  */
/* This work is licensed under the terms of the MIT license.    */
/****************************************************************/

package com.stuypulse.robot.commands.climber;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.subsystems.Climber;

public class ClimberSlowMoveDownCommand extends ClimberMoveCommand {

    public ClimberSlowMoveDownCommand(Climber climber) {
        super(climber, Constants.ClimberSettings.CLIMBER_SLOW_SPEED, false);
    }
}
