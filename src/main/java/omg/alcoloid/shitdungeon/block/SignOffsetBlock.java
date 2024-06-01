package omg.alcoloid.shitdungeon.block;

import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.Material;
import org.bukkit.RegionAccessor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class SignOffsetBlock extends BaseOffsetBlock {
    private final String[] lines = new String[4];

    public SignOffsetBlock(@NotNull Material material, @NotNull Vec3i offset) {
        super(material, offset);
    }

    public String getLine(@Range(from = 0, to = 4) int index) {
        return this.lines[index];
    }

    public SignOffsetBlock setLine(@Range(from = 0, to = 4) int index, String line) {
        this.lines[index] = line;
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState place(@NotNull RegionAccessor region, @NotNull Vec3i position) {
        BlockState blockState = super.place(region, position);

        if (blockState instanceof Sign signBlock) {
            for (int i = 0; i < this.lines.length; i++) {
                signBlock.getSide(Side.FRONT).setLine(i, this.lines[i]);
                signBlock.update();
            }
        }

        return blockState;
    }
}
