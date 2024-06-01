package omg.alcoloid.shitdungeon.block;

import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.Material;
import org.bukkit.RegionAccessor;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

// интерфейс для работы с блоками с относительной позицией
public interface OffsetBlock {
    // Размещает блок в region относительно position позиции
    BlockState place(@NotNull RegionAccessor region, @NotNull Vec3i position);

    // Возвращает вектор смещения для данного блока
    @NotNull
    Vec3i getOffsetVector();

    @NotNull
    Material getMaterial();
}