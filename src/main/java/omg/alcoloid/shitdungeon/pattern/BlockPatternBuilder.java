package omg.alcoloid.shitdungeon.pattern;

import omg.alcoloid.shitdungeon.block.BaseOffsetBlock;
import omg.alcoloid.shitdungeon.block.OffsetBlock;
import omg.alcoloid.shitdungeon.util.Cuboid;
import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class BlockPatternBuilder {
    private final List<OffsetBlock> blocks;

    public BlockPatternBuilder() {
        this.blocks = new ArrayList<>();
    }

    public BlockPatternBuilder append(@NotNull Material material, Vec3i offset) {
        this.blocks.add(new BaseOffsetBlock(material, offset));
        return this;
    }

    public BlockPatternBuilder append(@NotNull OffsetBlock offsetBlock) {
        this.blocks.add(offsetBlock);
        return this;
    }

    public BlockPatternBuilder cuboid(@NotNull Material material, @NotNull Cuboid cuboid) {
        // Я долго думал, куда можно поместить эту хуйню: в этот класс или применить
        // шаблон проектирования "итератор" и заебенить в Cuboid.class. Но, как видишь...

        for (int x = cuboid.min().x(); x <= cuboid.max().x(); x++) {
            for (int y = cuboid.min().y(); y <= cuboid.max().y(); y++) {
                for (int z = cuboid.min().z(); z <= cuboid.max().z(); z++) {
                    this.append(material, Vec3i.from(x, y, z));
                }
            }
        }

        return this;
    }

    @Contract(value = " -> new", pure = true)
    public @NotNull BlockPattern build() {
        return new BlockPatternImpl(this.blocks);
    }
}
