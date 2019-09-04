import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class AlfheimOnline extends JavaPlugin implements Listener
{
	private final Logger log = this.getLogger();
	
	private final ScoreboardManager sm = Bukkit.getServer().getScoreboardManager();
	Scoreboard scoreBoard = sm.getMainScoreboard();
	Objective fuel = scoreBoard.getObjective("fuel");
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		
		log.info("link start");
	}
	@Override
	public void onDisable()
	{
		log.info("game over");
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		p.setAllowFlight(true);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onGamemodeChangesEvent(PlayerGameModeChangeEvent event)
	{
		Player p = event.getPlayer();
		//TODO not working (Event called but flight not enabled in gm 0 && 2)
		p.setAllowFlight(true);
	}
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent event)
	{
		Player p = event.getPlayer();
		Float fd = p.getFallDistance();
		boolean og = p.isOnGround();
		if(og && fd > 3)
		{
			if(!p.getLocation().getBlock().isLiquid())
			{
				p.damage(Math.round(fd) - 3);
				Bukkit.getPluginManager().callEvent(new EntityDamageEvent(p, DamageCause.FALL, 100));
			}
		}
	}
	@EventHandler
	public void playerToggelFlightEvent(PlayerToggleFlightEvent event)
	{
		Player p = event.getPlayer();
		boolean isFlying = event.isFlying();
		@SuppressWarnings("deprecation")
		Score f = fuel.getScore(p);
		if(isFlying)
		{
			if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.ELYTRA)
			{
				if(f.getScore() > 200)
				{
					p.setFlying(true);
					return;
				}
				p.setFlying(false);
				event.setCancelled(true);
				return;
			}
			p.setFlying(false);
			event.setCancelled(true);
			return;
		}
	}
	//TODO add stop flying on removing elytra || empty fuel
}
