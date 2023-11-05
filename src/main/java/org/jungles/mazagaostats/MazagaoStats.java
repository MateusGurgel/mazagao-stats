package org.jungles.mazagaostats;

import org.bukkit.plugin.java.JavaPlugin;
import org.jungles.mazagaostats.producers.RabbitMQ;

public final class MazagaoStats extends JavaPlugin {

    RabbitMQ rabbitMQ;

    @Override
    public void onEnable() {
        ConfigManager configManager = ConfigManager.getInstance();
        configManager.setup(this);
        rabbitMQ = RabbitMQ.getInstance();
    }

    @Override
    public void onDisable() {
        rabbitMQ.stop();
    }
}
