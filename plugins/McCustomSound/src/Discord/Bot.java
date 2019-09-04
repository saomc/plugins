package Discord;

import javax.security.auth.login.LoginException;

import minecraft.Plugin;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class Bot
{
	private JDA jda = null;
	
	public Bot(Plugin pl)
	{
		try
		{
			jda = new JDABuilder("NjE2NjQ0NzIyOTg3MTA2MzQz.XWwMqQ.ndt0dKC6qfbZc2yL56lA7s4_njM").build();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
	}
}
