package omg.alcoloid.shitdungeon.util;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

// Простейшая реализация трехмерного целочисленного вектора
public final class Vec3i implements Cloneable {
    public static final Vec3i ZERO = new Vec3i(0, 0, 0);

    private int x;
    private int y;
    private int z;

    private Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static @NotNull Vec3i from(int x, int y, int z) {
        return new Vec3i(x, y, z);
    }

    public static @NotNull Vec3i from(@NotNull Vector vec) {
        return new Vec3i(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
    }

    public @NotNull Vec3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public @NotNull Vec3i add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public @NotNull Vec3i add(@NotNull Vec3i vec3i) {
        return this.add(vec3i.x, vec3i.y, vec3i.z);
    }

    public @NotNull Vec3i sub(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public @NotNull Vec3i sub(@NotNull Vec3i vec3i) {
        return this.sub(vec3i.x, vec3i.y, vec3i.z);
    }

    public @NotNull Vec3i eulerRot90Y() {
        // Чтобы повернуть трехмерный вектор по оси Y, мы должны выполнить следующее:

        //    | cos θ    0   sin θ| |x|   | x cos θ + z sin θ|   |x'|
        //    |   0      1       0| |y| = |         y        | = |y'|
        //    |−sin θ    0   cos θ| |z|   |−x sin θ + z cos θ|   |z'|

        // Произведение матрицы углового поворота и трехмерного вектора,
        // даст координаты точки в подвижной системе координат после поворота

        // Подробнее:
        // https://en.wikipedia.org/wiki/Euler_angles
        // https://stackoverflow.com/a/14609567

        //                  +------------+---------+
        //                  |   sin 90   |    1    |
        //                  |   cos 90   |    0    |
        //                  +------------+---------+

        //    | x cos 90 + z sin 90|   | z|
        //    |          y         | = | y|
        //    |−x sin 90 + z cos 90|   |-x|

        return this.set(this.z, this.y, -this.x);
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public int z() {
        return this.z;
    }

    @Override
    public Vec3i clone() {
        try {
            return (Vec3i) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
