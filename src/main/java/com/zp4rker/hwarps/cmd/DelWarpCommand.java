package com.zp4rker.hwarps.cmd;

import static com.zp4rker.hwarps.HungerWarps.db;
import static com.zp4rker.hwarps.HungerWarps.m;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.zp4rker.hwarps.Warp;
import com.zp4rker.localdb.Column;
import com.zp4rker.localdb.DataType;

public class DelWarpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("delwarp")) {
			// Check permissions
			if (!(sender.hasPermission("hungerwarps.delete"))) {
				sender.sendMessage(m("perms"));
				return true;
			}
			// Check arguments
			if (args.length != 1) {
				sender.sendMessage(m("args"));
				return false;
			}
			// Check if warp exists
			Column name = new Column("name", DataType.STRING);
			name.setValue(args[0]);
			if (db.getTables().get(0).getExact(name) != null) {
				new Warp(args[0]).delete();
				sender.sendMessage(m("delete warp", args[0]));
			} else {
				sender.sendMessage(m("warp noexist"));
			}
		}
		return true;
	}

}
