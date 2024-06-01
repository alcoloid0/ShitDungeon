package omg.alcoloid.shitdungeon.pattern;

import omg.alcoloid.shitdungeon.block.OffsetBlock;
import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.RegionAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockPatternImpl implements BlockPattern {
    private final List<OffsetBlock> blocks;

    public BlockPatternImpl(@NotNull List<OffsetBlock> blocks) {
        this.blocks = blocks;
    }

    public BlockPatternImpl(@NotNull BlockPattern.Filler filler) {
        this.blocks = new ArrayList<>();
        filler.apply(this.blocks); // Вкуснятина!
    }

    @Override
    public void place(@NotNull RegionAccessor region, @NotNull Vec3i position) {
        this.blocks.forEach(block -> block.place(region, position));
    }

    @Override
    public @NotNull BlockPattern rotate() {
        this.blocks.forEach(block -> block.getOffsetVector().eulerRot90Y());
        return this;
    }

    @NotNull
    @Override
    public Iterator<OffsetBlock> iterator() {
        return this.blocks.iterator();
    }
}
