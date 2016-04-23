package com.meteor.extrabotany.common.commands;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ModCommands {
    public ModCommands(FMLServerStartingEvent event)
    {
    	event.registerServerCommand(new CommandGetShieldAmount());
    	event.registerServerCommand(new CommandSetShieldAmount());
    	event.registerServerCommand(new CommandAddShieldAmount());
    }
}
