package org.firstinspires.ftc.teamcode.teleoperated;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drivers.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

public class Robot {
    public Shooter shooter;
    public Collector collector;
    public PinpointDrive drive;
    public Robot(HardwareMap hardwareMap, Pose2d pose2d) {
        drive = new PinpointDrive(hardwareMap);

    }

    public PinpointDrive getDrive() {
        return drive;
    }

    public class drive{

    }

    }
    public Collector collector;

}
