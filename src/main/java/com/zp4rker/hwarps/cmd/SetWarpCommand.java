package com.zp4rker.hwarps.cmd;

import static com.zp4rker.hwarps.HungerWarps.db;
import static com.zp4rker.hwarps.HungerWarps.m;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zp4rker.hwarps.Warp;
import com.zp4rker.localdb.Column;
import com.zp4rker.localdb.DataType;

public class SetWarpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("setwarp")) {
			// Check if player
			if (!(sender instanceof Player)) {
				sender.sendMessage(m("nonplayer"));
				return true;
			}
			// Check permissions
			if (!(sender.hasPermission("hungerwarps.setwarp"))) {
				sender.sendMessage(m("perms"));
				return true;
			}
			// Check arguments
			if (args.length != 1) {
				sender.sendMessage(m("args"));
				return false;
			}
			// Cast sender to player
			Player player = (Player) sender;
			
			Column name = new Column("name", DataType.STRING);
			name.setValue(args[0]);
			player.setFoodLevel(player.getFoodLevel() - 10);
			if (db.getTables().get(0).getExact(name) == null) {
				player.sendMessage(m("create warp", args[0]));
				Warp.createNew(args[0], player.getLocation()).saveToDatabase();
			} else {
				player.sendMessage(m("update warp", args[0]));
				Warp warp = new Warp(args[0]);
				warp.setLocation(player.getLocation());
				warp.saveToDatabase();
			}
		}
		return true;
	}

}
