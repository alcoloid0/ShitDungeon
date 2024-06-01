package omg.alcoloid.shitdungeon.pattern;

import omg.alcoloid.shitdungeon.block.OffsetBlock;
import omg.alcoloid.shitdungeon.util.RandomUtil;
import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.Location;
import org.bukkit.RegionAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BlockPattern extends Iterable<OffsetBlock> {
    static @NotNull BlockPattern filler(@NotNull BlockPattern.Filler filler) {
        return new BlockPatternImpl(filler);
    }

    static @NotNull BlockPattern pattern(@NotNull BlockPatterns pattern) {
        return pattern.getPattern();
    }

    static @NotNull BlockPattern random() {
        return pattern(RandomUtil.choice(BlockPatterns.values()));
    }

    static @NotNull BlockPatternBuilder builder() {
        return new BlockPatternBuilder();
    }

    void place(@NotNull RegionAccessor region, @NotNull Vec3i position);

    default void place(@NotNull Location location) {
        this.place(location.getWorld(), Vec3i.from(location.toVector()));
    }

    @NotNull
    BlockPattern rotate();

    interface Filler {
        void apply(List<OffsetBlock> blocks);
    }
}