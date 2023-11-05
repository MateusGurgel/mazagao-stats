package org.jungles.mazagaostats.producers;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.bukkit.configuration.file.FileConfiguration;
import org.jungles.mazagaostats.ConfigManager;

import java.util.ArrayList;
import java.util.List;

public class RabbitMQ {

    static private RabbitMQ rabbitMQ;
    private final List<Channel> channels = new ArrayList<>();
    private Connection connection;


    private RabbitMQ(){
        ConnectionFactory factory = new ConnectionFactory();
        ConfigManager configManager = ConfigManager.getInstance();
        FileConfiguration config = configManager.getConfig();


        factory.setHost(config.getString("settings.rabbitmq.host"));
        factory.setUsername(config.getString("settings.rabbitmq.username"));
        factory.setPassword(config.getString("settings.rabbitmq.password"));

        try {
            this.connection = factory.newConnection();

        } catch (Exception e) {
            System.out.println("Não foi possivel conectar ao rabbitMQ " + e.getMessage());
        }
    }

    static public RabbitMQ getInstance(){
        if(rabbitMQ == null){
            rabbitMQ = new RabbitMQ();
        }

        return rabbitMQ;
    }

    public Channel getChannel(){

        try {
            Channel channel =  connection.createChannel();
            channels.add(channel);
            return connection.createChannel();
        }
        catch (Exception e){
            System.out.println("Não foi possivel criar um channel " + e.getMessage());
        }
        return null;
    }

    public void stop(){
        try{
            for (Channel channel:channels) {
                channel.close();
            }

            connection.close();
        }catch (Exception e){
            System.out.println("Não foi possivel finalizar as conexões " + e.getMessage());
        }
    }
}
