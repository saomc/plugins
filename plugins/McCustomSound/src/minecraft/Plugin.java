package minecraft;

import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import Discord.Bot;

public class Plugin extends JavaPlugin implements Listener
{
	private final Logger log = this.getLogger();
	
	private final Bot bot = new Bot(this);

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		
		log.info("Wolle rose kaufen");
	}
	@Override
	public void onDisable()
	{
		log.info("-_-");
	}
}
