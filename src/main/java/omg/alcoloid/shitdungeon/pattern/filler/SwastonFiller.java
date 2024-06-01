package omg.alcoloid.shitdungeon.pattern.filler;

import omg.alcoloid.shitdungeon.block.BaseOffsetBlock;
import omg.alcoloid.shitdungeon.block.OffsetBlock;
import omg.alcoloid.shitdungeon.pattern.BlockPattern;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Ой, что-то жарковато стало. Пойду-ка подрублю вентиляторы...
public final class SwastonFiller implements BlockPattern.Filler {
    private final int height;
    private final int width;
    private final int halfWidth;
    private final int halfHeight;
    private final Material material;

    public SwastonFiller(Material material, int size) {
        this(material, size, size);
    }

    public SwastonFiller(Material material, int width, int height) {
        this.height = height;
        this.width = width;
        this.halfWidth = (width / 2);
        this.halfHeight = (height / 2);
        this.material = material;
    }

    @Override
    public void apply(List<OffsetBlock> blocks) {
        for (int y = 0; y < this.height; y++) {
            for (int x = -this.halfWidth; x <= this.halfWidth; x++) {
                blocks.add(this.getBlockByPosition(x, y));
            }
        }
    }

    @Contract("_, _ -> new")
    public @NotNull OffsetBlock getBlockByPosition(int x, int y) {
        if (y < this.halfHeight) { // нижняя часть свастики

            if (x < 0 && y == 0) {
                return new BaseOffsetBlock(this.material, x, y, 0);
            }

            if (x == this.halfWidth) {
                return new BaseOffsetBlock(this.material, x, y, 0);
            }

        } else if (y == this.halfHeight && x != 0) { // середина свастики
            // сплошная линия по вертикали
            return new BaseOffsetBlock(this.material, x, y, 0);
        } else { // верхняя часть свастики

            if (x > 0 && y == (this.height - 1)) {
                return new BaseOffsetBlock(this.material, x, y, 0);
            }

            if (x == -this.halfWidth) {
                return new BaseOffsetBlock(this.material, x, y, 0);
            }

        }

        if (x == 0) {
            // сплошная линия по горизонтали
            return new BaseOffsetBlock(this.material, x, y, 0);
        }

        return new BaseOffsetBlock(Material.AIR, x, y, 0);
    }
}

