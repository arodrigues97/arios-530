package plugin.skill.agility.shortcuts;

import org.arios.game.content.skill.member.agility.AgilityShortcut;
import org.arios.game.node.entity.impl.ForceMovement;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.object.GameObject;
import org.arios.game.world.map.Location;
import org.arios.game.world.update.flag.context.Animation;

/**
 * Handles a crumbling wall.
 * @author Vexia
 */
public class CrumblingWallShortcut extends AgilityShortcut {

	/**
	 * Represents the locations used in this force movement.
	 */
	private static final Location[] LOCATIONS = new Location[] { new Location(2936, 3355, 0), new Location(2934, 3355, 0) };

	/**
	 * Constructs a new {@Code CrumblingWallShortcut} {@Code
	 * Object}
	 */
	public CrumblingWallShortcut() {
		super(new int[] { 11844 }, 5, 0.0, "climb-over");
	}

	@Override
	public void run(Player player, GameObject object, String option, boolean failed) {
		ForceMovement.run(player, player.getLocation().getX() >= 2936 ? LOCATIONS[0] : LOCATIONS[1], player.getLocation().getX() >= 2936 ? LOCATIONS[1] : LOCATIONS[0], Animation.create(840), 10);
	}

}
