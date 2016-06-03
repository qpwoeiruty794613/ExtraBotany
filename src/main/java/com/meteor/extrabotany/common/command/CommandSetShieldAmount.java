package com.meteor.extrabotany.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

import com.meteor.extrabotany.common.handler.PropertyHandler;

public class CommandSetShieldAmount extends CommandBase{
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
    
	@Override
	public String getCommandName() {
		return "setShieldAmount";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException{
		if (args.length <=0)
        {
            throw new WrongUsageException("commands.ExtraBotany.setShieldAmount.desc", new Object[0]);
        }
        else
        {
        	EntityPlayer player;

            if (args.length > 1)
            {
            	player = getPlayer(sender, args[0]);
            }
            else
            {
                player = getCommandSenderAsPlayer(sender);
            }
            
            String s = args[1];
            int i = parseInt(sender, s);
            
            PropertyHandler.setShieldAmount(i, player);
            sender.addChatMessage(new ChatComponentTranslation("commands.ExtraBotany.setShieldAmount.success", player.getDisplayName(), PropertyHandler.getShieldAmount(player)));
        }
	}
	
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "commands.ExtraBotany.setShieldAmount.desc";
	}
	
    @Override
    public List<?> addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        if (args.length == 1)
        {
            String[] names = MinecraftServer.getServer().getAllUsernames();
            return CommandBase.getListOfStringsMatchingLastWord(args, names);
        }
        return null;
    }
}
