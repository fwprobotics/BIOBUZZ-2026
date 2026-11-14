package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.ImageDecoder;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Shooter {
    private DcMotorEx flywheelMotor = null;
    private CRServo feederservo = null;
    public boolean chargeShoot;
    public boolean maintainVelocity;
    public boolean runPinwheel;
    public int setVelocity = 1400;
    public int tolerance = 20;

    public Shooter(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheel_motor");
        feederservo = hardwareMap.get(CRServo.class, "intake_servo");

        feederservo.setDirection(CRServo.Direction.REVERSE);
        flywheelMotor.setDirection(DcMotorEx.Direction.FORWARD);
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(2.3, 0.25, 0.0, 11.1));
    }

    public void setChargeShoot(boolean bool){
        this.chargeShoot = bool;

    }
    public void setMaintainVelocity(boolean bool){
        this.maintainVelocity = bool;
    }
    public void runPinwheel(boolean bool){
        this.runPinwheel = bool;
    }
    public void OpModeUpdate() {
        if (runPinwheel) {
            feederservo.setPower(1.0);
        }

        if (chargeShoot || maintainVelocity){
            flywheelMotor.setVelocity(1400);
            if (chargeShoot) {
                if ((Math.abs(flywheelMotor.getVelocity() - setVelocity)) <= tolerance) {
                    feederservo.setPower(1.0);
                }
                if ((feederservo.getPower() == 1.0) && (Math.abs(flywheelMotor.getVelocity()) - setVelocity) >= 300) {
                    feederservo.setPower(0.0);
                }
            }
        } else {
            if (!runPinwheel) {
                feederservo.setPower(0.0);
            }
            flywheelMotor.setVelocity(0.0);
        }
    }

    public class MaintainVelocityOfFlywheelMotor implements Action {
        private SleepAction timer;

        public MaintainVelocityOfFlywheelMotor(int duration) {
            timer = new SleepAction(duration);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            flywheelMotor.setPower(0.7);
            if (timer.run(telemetryPacket)) {
                return true;
            }
            flywheelMotor.setPower(0.0);
            return false;
        }
    }

    public MaintainVelocityOfFlywheelMotor maintainVelocityOfFlywheelMotor(int duration) {
        return new MaintainVelocityOfFlywheelMotor(duration);
    }

    public class RunFeederServo implements Action {
        private SleepAction timer;

        public RunFeederServo(int duration) {
            timer = new SleepAction(duration);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            feederservo.setPower(0.7);
            if (timer.run(telemetryPacket)) {
                return true;
            }
            feederservo.setPower(0.0);
            return false;
        }

    }

    public RunFeederServo runFeederServo(int duration) {
        return new RunFeederServo(duration);
    }

    public class ChargeAndShoot implements Action {
        private SleepAction timeout;
        private double speed;
        private double tolerance = 20.0;

        public ChargeAndShoot(double speed, int maxTime) {
            this.speed = speed;
            timeout = new SleepAction(maxTime);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            telemetryPacket.put("Velocity:", flywheelMotor.getVelocity());
            if (!timeout.run(telemetryPacket)) {
                return false;
            }

            flywheelMotor.setVelocity(speed);
            if (Math.abs(flywheelMotor.getVelocity() - speed) < tolerance) {
                feederservo.setPower(1.0);
            }

            if (feederservo.getPower() == 1.0) {
                if (Math.abs(flywheelMotor.getVelocity() - speed) > 300) {
                    feederservo.setPower(0.0);
                    flywheelMotor.setPower(0.0);
                    return false;
                }
                return true;
            }
            return true;

        }
    }

    public Shooter.ChargeAndShoot chargeAndShoot(double speed, int maxTime) {
        return new Shooter.ChargeAndShoot(speed, maxTime);
    }


}
