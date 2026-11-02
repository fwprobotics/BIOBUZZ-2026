package org.firstinspires.ftc.teamcode.autonomus;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drivers.GoBildaPinpointDriver;

public class BasicAutoMode {

}

public class Robot {
    public class Collector{
        private DcMotor intakeMotor = null;
        private CRServo leftflower = null;
        private CRServo rightflower = null;
        public Collector(HardwareMap hardwareMap) {
            intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
            leftflower = hardwareMap.get(CRServo.class, "left_intake_servo");
            rightflower = hardwareMap.get(CRServo.class, "right_intake_servo");
        }
        private void initCollectorConfig(){
            intakeMotor.setDirection(DcMotor.Direction.FORWARD);
            leftflower.setDirection(CRServo.Direction.FORWARD);
            rightflower.setDirection(CRServo.Direction.FORWARD);
        }
        public class RunIntake implements Action {
            private SleepAction timer;

            public RunIntake(int duration) {
                timer = new SleepAction(duration);
            }

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                intakeMotor.setPower(0.7);
                if (timer.run(telemetryPacket)) {
                    return true;
                }
                intakeMotor.setPower(0.0);
                return false;
            }

            public class RunAllMotors implements Action {
                private SleepAction timer;

                public RunAllMotors(int duration) {
                    timer = new SleepAction(duration);
                }

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    intakeMotor.setPower(0.7);
                    leftflower.setPower(0.7);
                    rightflower.setPower(0.7);
                    if (timer.run(telemetryPacket)) {
                        return true;
                    }
                    intakeMotor.setPower(0.0);
                    leftflower.setPower(0.0);
                    rightflower.setPower(0.0);
                    return false;
                }

                public class RunFlowerServos implements Action {
                    private SleepAction timer;

                    public RunFlowerServos(int duration) {
                        timer = new SleepAction(duration);
                    }

                    @Override
                    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                        leftflower.setPower(0.7);
                        rightflower.setPower(0.7);
                        if (timer.run(telemetryPacket)) {
                            return true;
                        }
                        leftflower.setPower(0.0);
                        rightflower.setPower(0.0);
                        return false;
                    }
                }
            }
        }
    }
    public class Shooter{
        private DcMotor flywheelMotor = null;
        private CRServo feederservo = null;
        public Shooter(HardwareMap hardwareMap) {
            flywheelMotor = hardwareMap.get(DcMotor.class, "flywheel_motor");
            feederservo = hardwareMap.get(CRServo.class, "intake_servo");
        }
        private void initShooterConfig() {
            feederservo.setDirection(DcMotor.Direction.REVERSE);
            flywheelMotor.setDirection(CRServo.Direction.FORWARD);
        }
        public class MaintainVelocityofFlywheelMotor implements Action{
            private SleepAction timer;
            public MaintainVelocityofFlywheelMotor(int duration){
                timer = new SleepAction(duration);
            }
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                flywheelMotor.setPower(0.7);
                if (timer.run(telemetryPacket)){
                    return true;
                }
                flywheelMotor.setPower(0.0);
                return false;
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
    }
}
