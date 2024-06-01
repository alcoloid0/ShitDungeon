package omg.alcoloid.shitdungeon.generate;

import omg.alcoloid.shitdungeon.pattern.BlockPattern;
import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

// Ебучий Засиратель Мира 9к
public final class BlockPatternPopulator extends BlockPopulator {
    @Override
    public void populate(@NotNull WorldInfo worldInfo,
                         @NotNull Random random,
                         int chunkX,
                         int chunkZ,
                         @NotNull LimitedRegion limitedRegion) {

        // Получаем случайный паттерн
        BlockPattern blockPattern = BlockPattern.random();

        // Если условие выполняется, то поворачиваем паттерн (для разнообразия)
        if (random.nextBoolean()) {
            blockPattern = blockPattern.rotate();
        }

        // Получаем центральные координаты
        int centerX = limitedRegion.getCenterBlockX();
        int centerZ = limitedRegion.getCenterBlockZ();
        int centerY = limitedRegion.getHighestBlockYAt(centerX, centerZ);

        // Размещаем наш паттерн в центре чанка
        blockPattern.place(limitedRegion, Vec3i.from(centerX, centerY, centerZ));
    }
}
