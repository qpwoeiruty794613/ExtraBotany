package com.meteor.extrabotany.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;

public class CommandAddShieldAmount extends CommandBase implements IShieldHandler{
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
    
	@Override
	public String getCommandName() {
		return "addShieldAmount";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException{
		if (args.length <=0)
        {
            throw new WrongUsageException("commands.ExtraBotany.addShieldAmount.desc", new Object[0]);
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
            
            addShieldAmount(i, player);
            sender.addChatMessage(new ChatComponentTranslation("commands.ExtraBotany.addShieldAmount.success", player.getDisplayName(), getShieldAmount((player))));
        }
	}
	
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "commands.ExtraBotany.addShieldAmount.desc";
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
	
	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
	}
}
