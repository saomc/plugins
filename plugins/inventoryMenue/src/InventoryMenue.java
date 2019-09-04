import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryMenue extends JavaPlugin implements Listener
{
	private final Logger log = this.getLogger();

	private File currentJavaJarFile = new File(InventoryMenue.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	private String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
	private String path = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "inventoryMenue//");

	private File dirHome = new File(path);
	private File dirPatterns = new File(path + "patterns//");
	private File dirPlayers = new File(path + "players");
	private File config = new File(path + "config.yml");
	private ArrayList<File> patterns = new ArrayList<File>();
	private ArrayList<File> players = new ArrayList<File>();

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		
		if(!dirHome.exists())
		{
			dirHome.mkdir();
		}
		if(!dirPatterns.exists())
		{
			dirPatterns.mkdir();
		}
		else
		{
			File[] allSub = dirPatterns.listFiles();
			for(int i = 0; i < allSub.length; i++)
			{
				if(!allSub[i].isDirectory())
				{
					patterns.add(allSub[i]);
				}
			}
		}
		if(!dirPlayers.exists())
		{
			dirPlayers.mkdir();
		}
		else
		{
			File[] allSub = dirPlayers.listFiles();
			for(int i = 0; i < allSub.length; i++)
			{
				if(!allSub[i].isDirectory())
				{
					players.add(allSub[i]);
				}
			}
		}
		try
		{
			if(config.createNewFile())
			{
				log.info("Hi");
			}
		}
		catch(IOException e) {}

		log.info("loaded");
	}
	@Override
	public void onDisable()
	{
		log.info("bye");
	}
	@EventHandler
	public void openInventoryEvent(InventoryOpenEvent event)
	{
		HumanEntity e = event.getPlayer();
		Inventory i = event.getInventory();
		log.info("h");
		if(i.getType() == InventoryType.PLAYER)
		{
			log.info("hi");
		}
	}
}
