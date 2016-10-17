package com.zp4rker.hwarps;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

import com.zp4rker.localdb.*;

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
	}
	
	public void onDisable() {
		// onDisable code
	}

}
