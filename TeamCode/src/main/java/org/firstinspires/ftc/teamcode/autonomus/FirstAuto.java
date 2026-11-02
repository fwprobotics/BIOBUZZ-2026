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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public final class FirstAuto extends LinearOpMode {
    private Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, new Pose2d(0,0,0));
        PinpointDrive drive = robot.getDrive();
        Shooter shooter = robot.getShooter();
        Collector collector = robot.getCollector();
        waitForStart();

       Actions.runBlocking(new SequentialAction(
               new shooter.ChargeAndShoot(1450);

       ))



    }
}