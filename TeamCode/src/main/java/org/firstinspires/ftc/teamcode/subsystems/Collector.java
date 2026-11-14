package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Collector {
    private DcMotor intakeMotor = null;
    private CRServo leftflower = null;
    private CRServo rightflower = null;

    public boolean collectFloor;
    public boolean collectFlower;

    public Collector(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        leftflower = hardwareMap.get(CRServo.class, "left_intake_servo");
        rightflower = hardwareMap.get(CRServo.class, "right_intake_servo");

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        leftflower.setDirection(CRServo.Direction.FORWARD);
        rightflower.setDirection(CRServo.Direction.REVERSE);
    }
    public void setcollectFloor(boolean bool){
        this.collectFloor = bool;

    }
    public void setcollectFlower(boolean bool){
        this.collectFlower = bool;
    }
    public void OpModeUpdate() {
        if (collectFloor || collectFlower){
            intakeMotor.setPower(1.0);
            if (collectFlower){
                leftflower.setPower(1.0);
                rightflower.setPower(1.0);
            }
        } else {
            intakeMotor.setPower(0.0);
            leftflower.setPower(0.0);
            rightflower.setPower(0.0);
        }
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

    }

    public RunIntake runIntake(int duration) {
        return new RunIntake(duration);
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

    }

    public RunAllMotors runAllMotors(int duration) {
        return new RunAllMotors(duration);
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

    public RunFlowerServos runFlowerServos(int duration) {
        return new RunFlowerServos(duration);
    }


}

