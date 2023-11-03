package org.jungles.mazagaostats.producers;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.jungles.mazagaostats.data.Mining;
import org.jungles.mazagaostats.data.Pvp;

import java.io.IOException;

public class EventsProducer {

    private String queueName = "events";
    private RabbitMQ rabbitMQ = RabbitMQ.getInstance();
    private static EventsProducer eventsProducer;
    private Channel channel;

    private EventsProducer(){
        try{
            channel = rabbitMQ.getChannel();
            channel.queueDeclare(queueName, true, false, false, null);
        }
        catch (Exception e){
            System.out.println("Algo deu errado na declaração da fila: " + e.getMessage());
        }
    }

    public static EventsProducer getInstance(){
        if(eventsProducer == null){
            eventsProducer = new EventsProducer();
        }

        return eventsProducer;
    }


    public void sendKillEvent(String killer, String victim){
        Pvp pvp = new Pvp(killer, victim);

        Gson gson = new Gson();
        send(gson.toJson(pvp));
    }

    public void sendMiningEvent(String miner, String ore){
        Mining mining = new Mining(miner, ore);

        Gson gson = new Gson();
        send(gson.toJson(mining));
    }

    private void send(String message){
        try {
            channel.basicPublish("", queueName, null, message.getBytes());
        } catch (IOException e) {
            System.out.println("Algo deu errado no envio da mensagem: " + e.getMessage());
        }
    }






}