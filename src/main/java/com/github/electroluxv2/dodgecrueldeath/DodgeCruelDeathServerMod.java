package com.github.electroluxv2.dodgecrueldeath;

import net.fabricmc.api.DedicatedServerModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DodgeCruelDeathServerMod implements DedicatedServerModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("DodgeCruelDeath");

	@Override
	public void onInitializeServer() {
		LOGGER.info("Hello Fabric world!");
	}
}