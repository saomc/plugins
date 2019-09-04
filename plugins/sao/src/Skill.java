import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

public abstract class Skill
{
	private final Player p;
	private final Objective o;

	public Skill(Player p, Objective o)
	{
		this.p = p;
		this.o = o;
		//TODO everything
	}
	public abstract boolean equip();
	public abstract boolean unequip();
}
