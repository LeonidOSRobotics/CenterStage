package org.firstinspires.ftc.teamcode;


/* Data type that represents the arm position (Encoder Value)
 and then a bucket position (Servo Location) */
public enum ArmBucketPosition {

    LOAD(0, 0.5),
    CARRY(0, 0.0),
    PREFLIP(254, 0.8),
    FLIP(254, 0.5);

    private final int armTicks;
    private final double bucketPos;
    ArmBucketPosition(int armTicks, double bucketPos) {
        this.armTicks = armTicks;
        this.bucketPos = bucketPos;
    }

    public int getArmTicks() {
        return armTicks;
    }

    public double getBucketPos() {
        return bucketPos;
    }
}
