package com.zp4rker.hwarps;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.zp4rker.hwarps.cmd.DelWarpCommand;
import com.zp4rker.hwarps.cmd.SetWarpCommand;
import com.zp4rker.hwarps.cmd.WarpCommand;
import com.zp4rker.localdb.Column;
import com.zp4rker.localdb.DataType;
import com.zp4rker.localdb.Database;
import com.zp4rker.localdb.Table;

public class HungerWarps extends JavaPlugin {
	
	public static Database db;
	
	public void onEnable() {
		// Setup Database
		getLogger().info("Setting up database!");
		Column name = new Column("name", DataType.STRING);
		Column world = new Column("world", DataType.STRING);
		Column x = new Column("x", DataType.FLOAT);
		Column y = new Column("x", DataType.FLOAT);
		Column z = new Column("x", DataType.FLOAT);
		Column yaw = new Column("x", DataType.FLOAT);
		Column pitch = new Column("x", DataType.FLOAT);
		Table table = new Table("Warps", Arrays.asList(name, world, x, y, z, yaw, pitch));
		db = new Database(this, "Warps", table);
		if (db.getTables().get(0).getPrimaryKey().getName().equalsIgnoreCase("name")) {
			getLogger().info("Database setup!");
		}
		// Register commands
		getCommand("setwarp").setExecutor(new SetWarpCommand());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("delwarp").setExecutor(new DelWarpCommand());
	}
	
	public void onDisable() {
		// onDisable code
	}
	
	public static String m(String... args) {
		switch(args[0].toLowerCase()) {
		case "nonplayer":
			return trans("&4You must be a player!");
		case "perms":
			return trans("&4You do not have permission to perform that!");
		case "args":
			return trans("&cInvalid Arguments!");
		case "create warp":
			if (args.length == 2) {
				return trans("&6Created warp &2" + args[1] + "&6!");
			}
		case "update warp":
			if (args.length == 2) {
				return trans("&6Updated warp &2" + args[1] + "&6!");
			}
		case "delete warp":
			if (args.length == 2) {
				return trans("&6Deleted warp &2" + args[1] + "&6!");
			}
		case "warp":
			return trans("&6Teleporting...");
		case "warp noexist":
			return trans("&4That warp does not exist!");
		}
		return "";
	}
	
	private static String trans(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

}
