package omg.alcoloid.shitdungeon.util;

import org.jetbrains.annotations.NotNull;

public final class Cuboid {
    private final Vec3i min;
    private final Vec3i max;

    private Cuboid(@NotNull Vec3i pos1, @NotNull Vec3i pos2) {
        int xMax = Math.max(pos1.x(), pos2.x());
        int xMin = Math.min(pos1.x(), pos2.x());
        int yMax = Math.max(pos1.y(), pos2.y());
        int yMin = Math.min(pos1.y(), pos2.y());
        int zMax = Math.max(pos1.z(), pos2.z());
        int zMin = Math.min(pos1.z(), pos2.z());

        this.min = Vec3i.from(xMin, yMin, zMin);
        this.max = Vec3i.from(xMax, yMax, zMax);
    }

    public static @NotNull Cuboid from(@NotNull Vec3i pos1, @NotNull Vec3i pos2) {
        return new Cuboid(pos1, pos2);
    }

    public static @NotNull Cuboid from(int x0, int y0, int z0, int x1, int y1, int z1) {
        return from(Vec3i.from(x0, y0, z0), Vec3i.from(x1, y1, z1));
    }

    public Vec3i min() {
        return this.min;
    }

    public Vec3i max() {
        return this.max;
    }

    // Добавил метод для разнообразия. Не обессудьте
    public @NotNull Vec3i size() {
        return this.max.clone().sub(this.min).add(1, 1, 1);
    }
}
