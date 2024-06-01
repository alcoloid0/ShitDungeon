package omg.alcoloid.shitdungeon.pattern;

import omg.alcoloid.shitdungeon.block.OffsetBlock;
import omg.alcoloid.shitdungeon.block.SignOffsetBlock;
import omg.alcoloid.shitdungeon.pattern.filler.SwastonFiller;
import omg.alcoloid.shitdungeon.util.Cuboid;
import omg.alcoloid.shitdungeon.util.RandomUtil;
import omg.alcoloid.shitdungeon.util.Vec3i;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public enum BlockPatterns {
    PENIS { // Половой мужской орган
        @Override
        public BlockPattern getPattern() {
            // ТОДО: Написать филлер с регулируемым размером
            return BlockPattern.builder()
                    .append(Material.RED_WOOL, Vec3i.from(1, 0, 0))
                    .append(Material.RED_WOOL, Vec3i.from(-1, 0, 0))
                    .append(Material.RED_WOOL, Vec3i.from(0, 0, 0))
                    .append(Material.RED_WOOL, Vec3i.from(0, 1, 0))
                    .append(Material.RED_WOOL, Vec3i.from(0, 2, 0))
                    .append(Material.PINK_WOOL, Vec3i.from(0, 3, 0))
                    .build();
        }
    },
    BEACON { // Маяк
        private static final List<Material> STAINED_GLASS_LIST = List.of(
                Material.BLUE_STAINED_GLASS,
                Material.CYAN_STAINED_GLASS,
                Material.GREEN_STAINED_GLASS,
                Material.RED_STAINED_GLASS
        );

        @Override
        public BlockPattern getPattern() {
            return BlockPattern.builder()
                    .cuboid(Material.DIAMOND_BLOCK, Cuboid.from(-3, 0, -3, 3, 0, 3))
                    .cuboid(Material.DIAMOND_BLOCK, Cuboid.from(-2, 1, -2, 2, 1, 2))
                    .cuboid(Material.DIAMOND_BLOCK, Cuboid.from(-1, 2, -1, 1, 2, 1))
                    .append(Material.BEACON, Vec3i.from(0, 3, 0))
                    .append(RandomUtil.choice(STAINED_GLASS_LIST), Vec3i.from(0, 4, 0))
                    .build();
        }
    },
    POST { // Столб
        private static final List<Material> POST_BASE_LIST = List.of(
                Material.DIRT,
                Material.COBBLESTONE,
                Material.OAK_PLANKS
        );

        @Override
        public BlockPattern getPattern() {
            return BlockPattern.builder()
                    .cuboid(RandomUtil.choice(POST_BASE_LIST), Cuboid.from(0, 0, 0, 0, 50, 0))
                    .append(Material.TORCH, Vec3i.from(0, 51, 0))
                    .build();
        }
    },
    SWASTON { // Свастон
        @Override
        public BlockPattern getPattern() {
            return BlockPattern.filler(new SwastonFiller(
                    RandomUtil.choice(Material.GRAY_WOOL, Material.WHITE_WOOL),
                    RandomUtil.randomInt(5, 20)));
        }
    },
    COPPER_BULL { // Медный бык
        @Override
        public BlockPattern getPattern() {
            return BlockPattern.builder()
                    // Туловище
                    .cuboid(Material.COPPER_BLOCK, Cuboid.from(-3, 1, -1, 2, 3, 2))
                    // Голова
                    .cuboid(Material.COPPER_BLOCK, Cuboid.from(3, 2, 0, 5, 4, 1))
                    // Конечности
                    .append(Material.COPPER_BLOCK, Vec3i.from(-3, 0, -1))
                    .append(Material.COPPER_BLOCK, Vec3i.from(-3, 0, 2))
                    .append(Material.COPPER_BLOCK, Vec3i.from(2, 0, -1))
                    .append(Material.COPPER_BLOCK, Vec3i.from(2, 0, 2))
                    // Уши
                    .append(Material.COPPER_BLOCK, Vec3i.from(4, 4, -1))
                    .append(Material.COPPER_BLOCK, Vec3i.from(4, 4, 2))
//                  .append(YouTube.HOWDY_HO, Vec3i.from(-1, 2, 0))
                    .build();
        }
    },
    SIGN { // Табличка
        private static final Map<String, String> TEXT_LIST = Map.of(
                "1488", "卐卐卐卐卐卐",
                "Почему?", "А я ебу что ли?",
                "Это сложно?", "Нет, просто",
                "ПАЙТОН", "JAVA, НАХУЙ!"
        );

        @Override
        public BlockPattern getPattern() {
            Map.Entry<String, String> entry = RandomUtil.choice(TEXT_LIST.entrySet());

            OffsetBlock offsetBlock = new SignOffsetBlock(Material.OAK_SIGN, Vec3i.ZERO)
                    .setLine(1, entry.getKey())
                    .setLine(2, entry.getValue());

            return BlockPattern.builder().append(offsetBlock).build();
        }
    };

    public abstract BlockPattern getPattern();
}
