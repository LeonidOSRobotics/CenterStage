package org.firstinspires.ftc.teamcode;


/* Data type that represents the arm position (Encoder Value)
 and then a bucket position (Servo Location) */
public enum ArmBucketPosition {

    LOAD(0, 0.6),
    CARRY(30, 0.74),
    TRAVEL(140, 0.45),
    PREFLIP(650, 0.05),
    FLIP(650, 0.4),
    POSTFLIP(0, 0.8);

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
