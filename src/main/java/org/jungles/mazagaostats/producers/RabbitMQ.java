package org.jungles.mazagaostats.producers;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.ArrayList;
import java.util.List;

public class RabbitMQ {

    static private RabbitMQ rabbitMQ;
    final private String host = "192.168.1.90";
    final private String user = "minecraftServer";
    final private String password = "root";

    private final List<Channel> channels = new ArrayList<>();
    private Connection connection;


    private RabbitMQ(){
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        factory.setUsername(user);
        factory.setPassword(password);

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
