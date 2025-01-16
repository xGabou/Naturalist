package com.starfish_studios.naturalist.common.entity.core.ai.navigation;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class SmartBodyHelper extends BodyRotationControl {
    private static final int HISTORY_SIZE = 10;
    private static final double MOVE_THRESHOLD = 2.5e-7;
    private static final float BODY_LAG_MOVING = 0.3F;
    private static final float HEAD_LAG = 0.2F;
    private static final float BODY_LAG_STILL = 0.05F;
    private static final float BODY_MAX = 45F;
    private static final float HEAD_MAX = 22.5F;

    private final double[] histPosX = new double[HISTORY_SIZE];
    private final double[] histPosZ = new double[HISTORY_SIZE];
    private final Mob entity;

    public SmartBodyHelper(Mob entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void clientTick() {
        for (int i = HISTORY_SIZE - 1; i > 0; i--) {
            histPosX[i] = histPosX[i - 1];
            histPosZ[i] = histPosZ[i - 1];
        }
        histPosX[0] = entity.getX();
        histPosZ[0] = entity.getZ();

        double dx = avgDelta(histPosX);
        double dz = avgDelta(histPosZ);
        double distSq = dx * dx + dz * dz;

        if (entity.getTarget() != null) {
            entity.yBodyRot = entity.yBodyRotO;
            entity.yHeadRot = entity.yBodyRot;
        } else if (distSq > MOVE_THRESHOLD) {
            float moveAngle = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
            entity.yBodyRot = approachAngle(entity.yBodyRot, moveAngle, BODY_LAG_MOVING, BODY_MAX);
            entity.yHeadRot = approachAngle(entity.yHeadRot, entity.yBodyRot, HEAD_LAG, HEAD_MAX);
        } else {
            entity.yBodyRot = approachAngle(entity.yBodyRot, entity.yHeadRot, BODY_LAG_STILL, BODY_MAX);
        }
    }


    private double avgDelta(double[] arr) {
        return mean(arr, 0) - mean(arr, HISTORY_SIZE / 2);
    }

    private double mean(double[] arr, int start) {
        double s = 0;
        int half = HISTORY_SIZE / 2;
        for (int i = 0; i < half; i++) {
            s += arr[start + i];
        }
        return s / half;
    }

    private static float approachAngle(float current, float target, float factor, float maxDelta) {
        float d = Mth.wrapDegrees(target - current);
        if (d < -maxDelta) d = -maxDelta;
        else if (d > maxDelta) d = maxDelta;
        return current + d * factor;
    }
}
