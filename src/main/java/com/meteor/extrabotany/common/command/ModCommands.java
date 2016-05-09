package com.meteor.extrabotany.common.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ModCommands {
    public ModCommands(FMLServerStartingEvent event)
    {
    	event.registerServerCommand(new CommandGetShieldAmount());
    	event.registerServerCommand(new CommandSetShieldAmount());
    	event.registerServerCommand(new CommandAddShieldAmount());
    }
}
