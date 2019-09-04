import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class SwordArtOnline extends JavaPlugin implements Listener
{
	private final Logger log = this.getLogger();
	
	private File currentJavaJarFile = new File(SwordArtOnline.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	private String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
	private String path = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "sao//");

	private File dirHome = new File(path);
	private File dirPlayers = new File(path + "players");
	private File configFile = new File(path + "config.yml");
	private ArrayList<File> playerFiles = new ArrayList<File>();
	
	private final ScoreboardManager sm = Bukkit.getServer().getScoreboardManager();
	Scoreboard scoreBoard = sm.getMainScoreboard();
	
	@SuppressWarnings("serial")
	HashMap<String, Skill> stats = new HashMap<String, Skill>()
	{{
	//put("maxHealth", new MaxHealth(scoreBoard.getObjective("statsMaxHealth")));
	/*put("regeneration", scoreBoard.getObjective("statsRegeneration"));
	put("strength", scoreBoard.getObjective("statsStrength"));
	Objective statsForaging = scoreBoard.getObjective("statsForaging");
	Objective statsEndurance = scoreBoard.getObjective("statsEndurance");
	Objective statsAgility = scoreBoard.getObjective("statsAgility");*/
	}};
	Team pk = scoreBoard.getTeam("PK");
	Team pd = scoreBoard.getTeam("PD");
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		
		if(!dirHome.exists())
		{
			dirHome.mkdir();
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
					playerFiles.add(allSub[i]);
				}
			}
		}
		try
		{
			if(configFile.createNewFile())
			{
				log.info("Hi");
			}
		}
		catch(IOException e) {}
		
		log.info("link start");
	}
	@Override
	public void onDisable()
	{
		log.info("game over");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("gm") && sender instanceof Player && args.length == 1)
		{
			switch(args[0])
			{
			case "0":
				((Player) sender).chat("/gamemode survival");
			break;
			case "1":
				((Player) sender).chat("/gamemode creative");
			break;
			case "2":
				((Player) sender).chat("/gamemode adventure");
			break;
			case "3":
				((Player) sender).chat("/gamemode spectator");
			break;
			}
			return true;
		}
		return false;
	}
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		p.setHealthScaled(true);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onKill(PlayerDeathEvent e)
	{
		Player killed = e.getEntity();
		Entity killer = e.getEntity().getKiller();
		//TODO add PK status
		if(killer instanceof Player)
		{
			pk.addPlayer((Player)killer);
		}
	}
	@EventHandler
	public void playerDamageByEntityEvent(EntityDamageByEntityEvent event)
	{
		Entity e = event.getEntity();
		Entity damager = event.getDamager();
		if(e instanceof Player)
		{
			Player p = (Player) e;
			if(damager instanceof Player)
			{
				Player damagerP = (Player) damager;
				//TODO add PK status
			}
		}
	}
	@EventHandler
	public void playerDamageByBlockEvent(EntityDamageByBlockEvent event)
	{
		Entity e = event.getEntity();
		if(e instanceof Player)
		{
			Player p = (Player) e;
			
			//TODO add damage by block handling
			switch(event.getCause())
			{
			case BLOCK_EXPLOSION:
				break;
			case CONTACT:
				break;
			case CRAMMING:
				break;
			case CUSTOM:
				break;
			case DRAGON_BREATH:
				break;
			case DROWNING:
				break;
			case DRYOUT:
				break;
			case ENTITY_ATTACK:
				break;
			case ENTITY_EXPLOSION:
				break;
			case ENTITY_SWEEP_ATTACK:
				break;
			case FALL:
				break;
			case FALLING_BLOCK:
				break;
			case FIRE:
				break;
			case FIRE_TICK:
				break;
			case FLY_INTO_WALL:
				break;
			case HOT_FLOOR:
				break;
			case LAVA:
				break;
			case LIGHTNING:
				break;
			case MAGIC:
				break;
			case MELTING:
				break;
			case POISON:
				break;
			case PROJECTILE:
				break;
			case STARVATION:
				break;
			case SUFFOCATION:
				break;
			case SUICIDE:
				break;
			case THORNS:
				break;
			case VOID:
				break;
			case WITHER:
				break;
			default:
				break;
			}
		}
	}
	@EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event)
    {
		if(event.getRegainReason() == RegainReason.SATIATED)
		{
			event.setCancelled(true);
		}
    }
}