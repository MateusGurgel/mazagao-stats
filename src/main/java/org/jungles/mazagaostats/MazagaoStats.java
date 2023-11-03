package org.jungles.mazagaostats;

import org.bukkit.plugin.java.JavaPlugin;
import org.jungles.mazagaostats.listeners.ActionsListeners;
import org.jungles.mazagaostats.producers.EventsProducer;
import org.jungles.mazagaostats.producers.RabbitMQ;

public final class MazagaoStats extends JavaPlugin {

    RabbitMQ rabbitMQ = RabbitMQ.getInstance();

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents( new ActionsListeners(), this);
    }

    @Override
    public void onDisable() {
        rabbitMQ.stop();
    }
}
