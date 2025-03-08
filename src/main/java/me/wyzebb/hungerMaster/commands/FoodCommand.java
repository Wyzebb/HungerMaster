package me.wyzebb.hungerMaster.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FoodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length != 2 && args.length != 3) {
            commandSender.sendMessage("§cUsage: /food <add/sub/set> <amount> [target]");
        } else {
            boolean self = args.length == 2;
            final int num = Integer.parseInt(args[1]);
            Player target;

            if (self) {
                if (commandSender instanceof Player player) {
                    target = player;
                } else {
                    commandSender.sendMessage("§cYou must specify a player!");
                    return true;
                }
            } else {
                target = Bukkit.getPlayer(args[2]);
            }

            if (target == null) {
                commandSender.sendMessage("§cThe specified player is not online!");
                return true;
            }

            if (args[0].equalsIgnoreCase("add")) {
                int current = target.getFoodLevel();

                int finalFood = current + num;

                target.setFoodLevel(clampFood(finalFood));

                if (commandSender == target) {
                    target.sendMessage("§aYou added " + num + " hunger points!");
                } else {
                    commandSender.sendMessage("§aYou added " + num + " hunger points to " + target.getName() + "!");
                    target.sendMessage("§aYou've had " + num + " hunger points added!");
                }
            } else if (args[0].equalsIgnoreCase("sub")) {
                int current = target.getFoodLevel();

                int finalFood = current - num;

                target.setFoodLevel(clampFood(finalFood));

                if (commandSender == target) {
                    target.sendMessage("§aYou subtracted " + num + " hunger points!");
                } else {
                    commandSender.sendMessage("§aYou subtracted " + num + " hunger points from " + target.getName() + "!");
                    target.sendMessage("§aYou've had " + num + " hunger points subtracted!");
                }
            } else if (args[0].equalsIgnoreCase("set")) {
                target.setFoodLevel(clampFood(num));

                if (commandSender == target) {
                    target.sendMessage("§aYou are now at " + num + " hunger points!");
                } else {
                    commandSender.sendMessage("§aYou set " + target.getName() + "'s hunger points to " + num + "!");
                    target.sendMessage("§aYou've had your hunger points set to " + num + "!");
                }
            } else {
                commandSender.sendMessage("§cInvalid subcommand. Use add, sub or set!");
                return true;
            }
        }

        return true;
    }

    private int clampFood(int food) {
        return Math.clamp(food, 0, 20);
    }
}
