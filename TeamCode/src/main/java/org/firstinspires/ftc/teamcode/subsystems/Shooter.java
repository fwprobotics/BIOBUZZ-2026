package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    public class MaintainVelocityofFlywheelMotor implements Action {
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
