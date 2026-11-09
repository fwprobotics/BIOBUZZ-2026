package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Shooter{
    private DcMotorEx flywheelMotor = null;
    private CRServo feederservo = null;
    public Shooter(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheel_motor");
        feederservo = hardwareMap.get(CRServo.class, "intake_servo");

        feederservo.setDirection(CRServo.Direction.REVERSE);
        flywheelMotor.setDirection(DcMotorEx.Direction.FORWARD);
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(2.0, 0.3, 0.0, 11.1));
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

    public class ChargeAndShoot implements Action {
            private double speed;
            private double tolerance = 20.0;
            public ChargeAndShoot(double speed){
                this.speed = speed;

            }

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                telemetryPacket.put("Velocity:", flywheelMotor.getVelocity());
                flywheelMotor.setVelocity(speed);
                if (Math.abs(flywheelMotor.getVelocity() - speed) < tolerance) {
                    feederservo.setPower(1.0);
                }

                if (feederservo.getPower()==1.0){
                    if (Math.abs(flywheelMotor.getVelocity() - speed) >300) {
                        feederservo.setPower(0.0);
                        flywheelMotor.setPower(0.0);
                        return false;
                    }
                    return true;
                }
                return true;

            }
        }
        public Shooter.ChargeAndShoot chargeAndShoot(double speed){
            return new Shooter.ChargeAndShoot(speed);
        }


}
