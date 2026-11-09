package org.firstinspires.ftc.teamcode.autonomus;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "First Auto", group = "Auto")
public final class FirstAuto extends LinearOpMode {
    private Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startingPose = new Pose2d(-12.5258,-61.1707,Math.toRadians(270));
        robot = new Robot(hardwareMap, startingPose);
        PinpointDrive drive = robot.getDrive();
        Shooter shooter = robot.getShooter();
        Collector collector = robot.getCollector();
        Action traj = drive.actionBuilder(startingPose)
                .splineTo(new Vector2d(-50.7805, -44.8976), Math.toRadians(90))
                .splineTo(new Vector2d(-53.561, 34.0683), Math.toRadians(89.9948))
                .build();
        waitForStart();

        Actions.runBlocking(new SequentialAction(
//                shooter.chargeAndShoot(1400),
//                shooter.chargeAndShoot(1400),
//                shooter.chargeAndShoot(1400),
//                shooter.chargeAndShoot(1400),
                traj
        ));





    }
}