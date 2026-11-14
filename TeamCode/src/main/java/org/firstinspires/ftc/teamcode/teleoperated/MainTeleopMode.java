package org.firstinspires.ftc.teamcode.teleoperated;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name="MainTeleopMode", group="A")
public class MainTeleopMode extends OpMode {
    private Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, new Pose2d(0,0,0));
    }

    @Override
    public void loop() {
        PinpointDrive drive = robot.getDrive();
        Shooter shooter = robot.getShooter();
        Collector collector = robot.getCollector();

        drive.updatePoseEstimate();
        double heading = drive.pose.heading.toDouble();

        double forward = -gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;

        double rotX = forward * Math.cos(heading)
                + strafe * Math.sin(heading);

        double rotY = -forward * Math.sin(heading)
                + strafe * Math.cos(heading);

        drive.setDrivePowers(
                new PoseVelocity2d(
                        new Vector2d(rotX, rotY),
                        turn
                )
        );


        shooter.runPinwheel(gamepad1.y);
        shooter.setChargeShoot(gamepad1.right_trigger_pressed);
        shooter.setMaintainVelocity(gamepad1.right_bumper);
        collector.setcollectFloor(gamepad1.left_trigger_pressed);
        collector.setcollectFlower(gamepad1.left_bumper);

        if (gamepad1.start) {
            drive.pinpoint.resetYaw();
        }

        shooter.OpModeUpdate();
        collector.OpModeUpdate();
    }
}
