package omg.alcoloid.shitdungeon.block;

import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.Material;
import org.bukkit.RegionAccessor;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;

public class BaseOffsetBlock implements OffsetBlock {
    protected final Material material;
    protected final Vec3i offset;

    public BaseOffsetBlock(@NotNull Material material, int x, int y, int z) {
        this(material, Vec3i.from(x, y, z));
    }

    public BaseOffsetBlock(@NotNull Material material, @NotNull Vec3i offset) {
        if (!material.isBlock()) {
            throw new InvalidParameterException(material.name() + " is not block");
        }

        this.offset = offset;
        this.material = material;
    }

    @Override
    public @NotNull BlockState place(@NotNull RegionAccessor region, @NotNull Vec3i position) {
        Vec3i offset = position.clone().add(this.offset);
        region.setType(offset.x(), offset.y(), offset.z(), material);
        return region.getBlockState(offset.x(), offset.y(), offset.z());
    }

    @Override
    public @NotNull Vec3i getOffsetVector() {
        return this.offset;
    }

    @Override
    public @NotNull Material getMaterial() {
        return this.material;
    }
}
