/************************ PROJECT DORCAS ************************/
/* Copyright (c) 2022 StuyPulse Robotics. All rights reserved.  */
/* This work is licensed under the terms of the MIT license.    */
/****************************************************************/

package com.stuypulse.robot;

import com.stuypulse.stuylib.network.SmartBoolean;
import com.stuypulse.stuylib.network.SmartNumber;

import com.stuypulse.robot.util.UnknownPorts;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import java.nio.file.Path;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static)k. Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public interface Constants {

    Path DEPLOY_DIRECTORY = Filesystem.getDeployDirectory().toPath();

    SmartBoolean DEBUG_MODE = new SmartBoolean("Debug Mode", true);

    public interface Ports {

        public interface Gamepad {
            int DRIVER = 0;
            int OPERATOR = 1;
            int DEBUGGER = 2;
        }

        public interface Climber {
            int MOTOR = 50;

            int SOLENOID_LONG = UnknownPorts.getFakeSolenoid();
            int SOLENOID_SHORT = UnknownPorts.getFakeSolenoid();
            int SOLENOID_STOPPER = UnknownPorts.getFakeSolenoid();

            int BOTTOM_LIMIT_SWITCH = UnknownPorts.getFakeSensor();
            int TOP_LIMIT_SWITCH = UnknownPorts.getFakeSensor();
        }

        I2C.Port COLOR_SENSOR = I2C.Port.kOnboard;

        public interface Conveyor {
            int GANDALF_MOTOR = 30;
            int TOP_BELT_MOTOR = 31;

            int GANDALF_IR_SENSOR = UnknownPorts.getFakeSensor();
            int TOP_BELT_IR_SENSOR = UnknownPorts.getFakeSensor();
        }

        public interface Drivetrain {
            int LEFT_TOP = 10;
            int LEFT_MIDDLE = 11;
            int LEFT_BOTTOM = 12;

            int RIGHT_TOP = 13;
            int RIGHT_MIDDLE = 14;
            int RIGHT_BOTTOM = 15;

            int GEAR_SHIFT_A = UnknownPorts.getFakeSolenoid();
            int GEAR_SHIFT_B = UnknownPorts.getFakeSolenoid();

            interface Encoders {
                int LEFT_A = UnknownPorts.getFakeSensor();
                int LEFT_B = UnknownPorts.getFakeSensor();

                int RIGHT_A = UnknownPorts.getFakeSensor();
                int RIGHT_B = UnknownPorts.getFakeSensor();
            }
        }

        public interface Intake {
            int MOTOR = 40;

            int SOLENOID_A = UnknownPorts.getFakeSolenoid();
            int SOLENOID_B = UnknownPorts.getFakeSolenoid();
        }

        public interface LEDController {
            int PWM_PORT = 0;
        }

        public interface Shooter {
            int SHOOTER = 20;
            int SHOOTER_FOLLOWER = 21;
            int FEEDER = 22;

            int HOOD_SOLENOID = UnknownPorts.getFakeSolenoid();
        }
    }

    public interface ClimberSettings {
        SmartNumber CLIMBER_DEFAULT_SPEED = new SmartNumber("Climber/Default Speed", 1.0);
        SmartNumber CLIMBER_SLOW_SPEED = new SmartNumber("Climber/Slow Speed", 0.2);

        SmartNumber CLIMBER_DELAY = new SmartNumber("Climber/Delay", 0.1);

        boolean MOTOR_INVERTED = false;
    }

    public interface ColorSensorSettings {
        public interface BallColor {
            Color RED = new Color(0.5432, 0.3401, 0.1169);
            Color BLUE = new Color(0.1826, 0.42505, 0.3982);
        }

        SmartNumber MAX_PROXIMITY = new SmartNumber("Color Sensor/Max Proximity", 100);
    }

    public interface ConveyorSettings {
        SmartNumber TOP_BELT_SPEED = new SmartNumber("Conveyor/Top Belt Speed", 0.6);
        SmartNumber ACCEPT_SPEED = new SmartNumber("Conveyor/Accept Speed", 0.6);
        SmartNumber REJECT_SPEED = new SmartNumber("Conveyor/Reject Speed", -0.6);

        SmartBoolean DISABLE_IR_SENSOR = new SmartBoolean("Conveyor/Disable IR Sensor", false);
    }

    public interface DrivetrainSettings {
        // If speed is below this, use quick turn
        SmartNumber BASE_TURNING_SPEED = new SmartNumber("Driver Settings/Base Turn Speed", 0.4);

        // Low Pass Filter and deadband for Driver Controls
        SmartNumber SPEED_DEADBAND = new SmartNumber("Driver Settings/Speed Deadband", 0.05);
        SmartNumber ANGLE_DEADBAND = new SmartNumber("Driver Settings/Turn Deadband", 0.05);

        SmartNumber SPEED_POWER = new SmartNumber("Driver Settings/Speed Power", 1.0);
        SmartNumber ANGLE_POWER = new SmartNumber("Driver Settings/Turn Power", 1.0);

        SmartNumber SPEED_FILTER = new SmartNumber("Driver Settings/Speed Filtering", 0.2);
        SmartNumber ANGLE_FILTER = new SmartNumber("Driver Settings/Turn Filtering", 0.01);

        // Current Limit for the motors
        int CURRENT_LIMIT_AMPS = 30;

        // If the motors are inverted
        boolean IS_INVERTED = true;

        // Width of the robot
        double TRACK_WIDTH = Units.inchesToMeters(26.9); // SEAN PROMISED !

        boolean USING_GRAYHILLS = true;
        boolean USING_GYRO = true;

        public interface Motion {

            DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH);

            SimpleMotorFeedforward MOTOR_FEED_FORWARD =
                    new SimpleMotorFeedforward(FeedForward.kS, FeedForward.kV, FeedForward.kA);

            double MAX_VELOCITY = Units.feetToMeters(16.0);
            double MAX_ACCELERATION = Units.feetToMeters(8.0);

            public interface FeedForward {
                double kS = 0; // TODO: characterize
                double kV = 0; // TODO: characterize
                double kA = 0; // TODO: characterize
            }

            public interface PID {
                double kP = 0; // TODO: characterize
                double kI = 0; // TODO: characterize
                double kD = 0; // TODO: characterize
            }
        }

        public interface Odometry {
            Translation2d STARTING_TRANSLATION = new Translation2d();
            Rotation2d STARTING_ANGLE = new Rotation2d();

            Pose2d STARTING_POSITION = new Pose2d(STARTING_TRANSLATION, STARTING_ANGLE);
        }

        // Encoder Constants
        public interface Encoders {

            public interface GearRatio {

                public interface Stages {
                    double INITIAL_STAGE = (11.0 / 50.0);

                    double HIGH_GEAR_STAGE = (50.0 / 34.0);
                    double LOW_GEAR_STAGE = (24.0 / 60.0);

                    double THIRD_STAGE = (34.0 / 50.0);

                    double EXTERNAL_STAGE = (1.0 / 1.0);

                    double GRAYHILL_STAGE = (12.0 / 36.0);
                }

                double LOW_GEAR_NEO_TO_WHEEL =
                        Stages.INITIAL_STAGE
                                * Stages.LOW_GEAR_STAGE
                                * Stages.THIRD_STAGE
                                * Stages.EXTERNAL_STAGE;

                double HIGH_GEAR_NEO_TO_WHEEL =
                        Stages.INITIAL_STAGE
                                * Stages.HIGH_GEAR_STAGE
                                * Stages.THIRD_STAGE
                                * Stages.EXTERNAL_STAGE;

                double GRAYHILL_TO_WHEEL = Stages.GRAYHILL_STAGE * Stages.EXTERNAL_STAGE;
            }

            double WHEEL_DIAMETER = Units.inchesToMeters(4);
            double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

            double LOW_GEAR_DISTANCE_PER_ROTATION =
                    WHEEL_CIRCUMFERENCE * GearRatio.LOW_GEAR_NEO_TO_WHEEL;
            double HIGH_GEAR_DISTANCE_PER_ROTATION =
                    WHEEL_CIRCUMFERENCE * GearRatio.HIGH_GEAR_NEO_TO_WHEEL;

            double GRAYHILL_PULSES_PER_REVOLUTION = 256;
            double GRAYHILL_DISTANCE_PER_PULSE =
                    (WHEEL_CIRCUMFERENCE / GRAYHILL_PULSES_PER_REVOLUTION)
                            * GearRatio.GRAYHILL_TO_WHEEL;
        }
    }

    public interface IntakeSettings {
        // TODO: test with intake
        SmartNumber MOTOR_SPEED = new SmartNumber("Intake/Motor Speed", 0.8);
    }

    public interface LEDSettings {
        double MANUAL_UPDATE_TIME = 0.65;

        double BLINK_TIME = 0.5;
    }

    public interface ShooterSettings {
        // smart number so we can tweak it
        SmartNumber STARTUP_DELAY = new SmartNumber("Shooter/Startup Delay", 1);


        SmartNumber RING_RPM = new SmartNumber("Shooter/Ring RPM", 3900);
        SmartNumber FENDER_RPM = new SmartNumber("Shooter/Fender RPM", 3000);
        SmartNumber FEEDER_MULTIPLER = new SmartNumber("Shooter/Feeder Multipler", 1.0);

        public interface ShooterPID {
            double kP = 0.0;
            double kI = 0.0;
            double kD = 0.0;
            double kF = 0.0;
        }

        public interface FeederPID {
            double kP = 0.0;
            double kI = 0.0;
            double kD = 0.0;
            double kF = 0.0;
        }
    }
}
