package com.massivecraft.factions.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.ArgSetting;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

public class CmdFactionsFlagList extends FactionsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsFlagList()
	{
		// Aliases
		this.addAliases("l", "list");
		
		// Args
		this.addArg(ArgSetting.getPage());
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.FLAG_LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		//Create messages
		List<String> messages = new ArrayList<String>();
		
		for (MFlag flag : MFlag.getAll())
		{
			if ( ! flag.isVisible() && ! msender.isUsingAdminMode()) continue;
			messages.add(flag.getStateDesc(false, false, true, true, true, false));
		}
		
		//Send messages
		sendMessage(Txt.getPage(messages, page, "Available Faction Flags", sender));
	}
	
}
