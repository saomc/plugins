import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

public class MaxHealth extends Skill
{
	public MaxHealth(Player p, Objective o)
	{
		super(p, o);
	}

	@Override
	public boolean equip()
	{
		
		return true;
	}

	@Override
	public boolean unequip()
	{
		return true;
	}

}
