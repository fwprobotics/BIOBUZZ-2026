package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

public class Robot {
    public Shooter shooter;
    public Collector collector;
    public PinpointDrive drive;

    public Robot(HardwareMap hardwareMap, Pose2d pose2d) {
        drive = new PinpointDrive(hardwareMap, pose2d);
        collector = new Collector(hardwareMap);
        shooter = new Shooter(hardwareMap);

    }

    public PinpointDrive getDrive() {
        return drive;
    }

    public Collector getCollector() {
        return collector;
    }
    public Shooter getShooter() {
        return shooter;
    }

}
