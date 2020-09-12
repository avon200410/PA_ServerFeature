package me.miunapa.paserverfeature.item;

import org.bukkit.Material;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import me.miunapa.paserverfeature.SubFeature;
import net.md_5.bungee.api.ChatColor;

public class LoliSoup extends SubFeature implements Listener {

    public LoliSoup() {
        super("LoliSoup");
        pm.registerEvents(this, plugin);

    }

    public void onDisable() {

    }

    @EventHandler
    public void clickAnimal(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (player.getInventory().getItemInMainHand().getType() == Material.BEETROOT_SOUP) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getItemMeta().getDisplayName().equals("§6蘿莉濃湯")) {
                if (entity instanceof Breedable) {
                    Breedable age = (Breedable) entity;
                    if (age.getAge() >= 0) {
                        player.sendActionBar(ChatColor.RED + "不能用在成年動物上");
                        return;
                    }
                    if (age.getAge() < 0 && age.getAgeLock()) {
                        player.sendActionBar(ChatColor.RED + "它已經喝過了");
                        return;
                    }
                    age.setAgeLock(true);
                    item.setAmount(0);
                    player.sendActionBar(ChatColor.DARK_GREEN + "使用完成! 它永遠不會長大了");
                } else {
                    player.sendActionBar(ChatColor.RED + "不能用在這種生物上");
                }
            }
        }
    }
}
