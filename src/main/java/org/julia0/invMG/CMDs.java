package org.julia0.invMG;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;

public class CMDs implements CommandExecutor {

    public CMDs(Plugin plugin){
        Objects.requireNonNull(plugin.getCommand("inv")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("clear")){
            String playerName;
            try{
                playerName = args[1];
            }catch (IndexOutOfBoundsException e){
                sender.sendMessage("Ты не написал ник =(");
                return true;
            }

            Player player = Bukkit.getPlayer(playerName);
            if (player == null){
                sender.sendMessage("Такого игрока нету");
                return true;
            }

            InventoryManagement.clear(player);
            player.sendMessage("Вы очистили инвентарь игроку");
            return true;
        }
        else if (args[0].equalsIgnoreCase("add")){
            String playerName;
            String itemName;
            int amount;
            try {
                playerName = args[1];
                itemName = args[2];
                amount = Integer.parseInt(args[3]);
            }catch (IndexOutOfBoundsException | NumberFormatException e){
                sender.sendMessage("Укажи ник игрока, название предмета и количество");
                return true;
            }

            Player player = Bukkit.getPlayer(playerName);
            if (player == null){
                sender.sendMessage("Такого игрока нету");
                return true;
            }

            Material material = Material.getMaterial(itemName.toUpperCase(Locale.ROOT));
            if (material == null){
                sender.sendMessage("Такого предмета нету");
                return true;
            }

            ItemStack itemStack = new ItemStack(material, amount);
            InventoryManagement.addItem(player, itemStack);
            sender.sendMessage("Предмет(ы) успешно выдан(ы)");
            return true;
        }
        else if (args[0].equalsIgnoreCase("remove")){
            String playerName;
            String itemName;
            int amount;
            try {
                playerName = args[1];
                itemName = args[2];
                amount = Integer.parseInt(args[3]);
            }catch (IndexOutOfBoundsException | NumberFormatException e){
                sender.sendMessage("Укажи ник игрока, название предмета и количество");
                return true;
            }

            Player player = Bukkit.getPlayer(playerName);
            if (player == null){
                sender.sendMessage("Такого игрока нету");
                return true;
            }

            Material material = Material.getMaterial(itemName.toUpperCase(Locale.ROOT));
            if (material == null){
                sender.sendMessage("Такого предмета нету");
                return true;
            }

            ItemStack itemStack = new ItemStack(material, amount);
            InventoryManagement.removeItem(player, itemStack);
            sender.sendMessage("Предмет успешно удалён");
            return true;
        }

        return false;
    }
}
