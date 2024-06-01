package omg.alcoloid.shitdungeon;

import omg.alcoloid.shitdungeon.generate.BlockPatternPopulator;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class ShitDungeon extends JavaPlugin implements Listener {
    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        // Я сначала подумал добавить конфигурацию, но потом передумал
        // Черт!

        // Плагин - неоптимизированное днище, т.к. все паттерны
        // не кэшируются и создаются по новой при генерации очередного
        // чанка

        // И да, как же без этого
        List<String> authors = this.getDescription().getAuthors();

        // Выводить всякую хуйню при включении плагина - это традиция
        if (!authors.isEmpty()) {
            String authorString = String.join(", ", authors);

            // Уровень английского: -999
            this.getLogger().info("Plugin developers: " + authorString);
            this.getLogger().warning("I repeat, plugin developers: " + authorString);
            this.getLogger().info("Are you okay?");
        }
    }

    @EventHandler
    public void onWorldInit(@NotNull WorldInitEvent event) {
        event.getWorld().getPopulators().add(new BlockPatternPopulator());
    }
}
