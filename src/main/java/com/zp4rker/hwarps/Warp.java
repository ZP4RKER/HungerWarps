package com.zp4rker.hwarps;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.zp4rker.localdb.Column;
import com.zp4rker.localdb.DataType;

public class Warp {
	
	private String name;
	private World world;
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;
	
	public Warp(String name) {
		Column primaryKey = new Column("name", DataType.STRING, 0);
		primaryKey.setValue(name);
		if (HungerWarps.db.getTables().get(0).getExact(primaryKey) != null) {
			for (Column column : HungerWarps.db.getTables().get(0).getExact(primaryKey)) {
				switch(column.getName()) {
				case "name":
					this.name = (String) column.getValue();
					break;
				case "world":
					this.world = Bukkit.getWorld((String) column.getValue());
					break;
				case "x":
					this.x = Double.parseDouble(column.getValue() + "");
					break;
				case "y":
					this.y = Double.parseDouble(column.getValue() + "");
					break;
				case "z":
					this.z = Double.parseDouble(column.getValue() + "");
					break;
				case "yaw":
					this.yaw = (Float) column.getValue();
					break;
				case "pitch":
					this.pitch = (Float) column.getValue();
				}
			}
		}
	}
	
	private Warp(String name, Location location) {
		this.name = name;
		setLocation(location);
	}
	
	public static Warp createNew(String name, Location location) {
		return new Warp(name, location);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public Location getLocation() {
		return new Location(world, x, y , z, yaw, pitch);
	}
	
	public void setLocation(Location location) {
		this.world = location.getWorld();
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
		this.yaw = location.getYaw();
		this.pitch = location.getPitch();
	}
	
	public void saveToDatabase() {
		Column name = new Column("name", DataType.STRING);
		name.setValue(this.name);
		
		Column world = new Column("world", DataType.STRING);
		world.setValue(this.world.getName());
		
		Column x = new Column("x", DataType.FLOAT);
		x.setValue(this.x);
		
		Column y = new Column("y", DataType.FLOAT);
		y.setValue(this.y);
		
		Column z = new Column("z", DataType.FLOAT);
		z.setValue(this.z);
		
		Column yaw = new Column("yaw", DataType.FLOAT);
		yaw.setValue(this.yaw);
		
		Column pitch = new Column("pitch", DataType.FLOAT);
		pitch.setValue(this.pitch);
		
		if (HungerWarps.db.getTables().get(0).getExact(name) != null) {
			HungerWarps.db.getTables().get(0).update(name, Arrays.asList(world, x, y, z, yaw, pitch));
			System.out.println("Updated warp: " + this.name);
		} else {
			HungerWarps.db.getTables().get(0).insert(Arrays.asList(name, world, x, y, z, yaw, pitch));
			System.out.println("Added warp: " + this.name);
		}
	}
	
	public void delete() {
		Column primaryKey = new Column("name", DataType.STRING);
		primaryKey.setValue(name);
		HungerWarps.db.getTables().get(0).delete(primaryKey);
		System.out.println("Deleted warp: " + name);
	}
	
	public static boolean nameExists(String name) {
		Column primaryKey = new Column("name", DataType.STRING);
		primaryKey.setValue(name);
		return (HungerWarps.db.getTables().get(0).getExact(primaryKey).size() != 0);
	}

}
