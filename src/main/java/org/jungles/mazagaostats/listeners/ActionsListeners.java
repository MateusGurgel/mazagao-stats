package org.jungles.mazagaostats.listeners;

import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jungles.mazagaostats.producers.EventsProducer;

import java.util.Collection;

public class ActionsListeners implements Listener {

    EventsProducer eventsProducer = EventsProducer.getInstance();

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event){
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if(killer != null){
            eventsProducer.sendKillEvent(killer.getName(), victim.getName());
        }
    }

    @EventHandler
    void onBlockBreakEvent(BlockBreakEvent event){
        Block block = event.getBlock();
        Player miner = event.getPlayer();
        ItemStack tool = miner.getInventory().getItemInMainHand();

        if(tool.containsEnchantment(Enchantment.SILK_TOUCH)){
            return;
        }

        eventsProducer.sendMiningEvent(miner.getName(), block.getType().toString());
    }

}
