package plugin.activity.guild;

import org.arios.cache.def.impl.ObjectDefinition;
import org.arios.game.content.global.action.ClimbActionHandler;
import org.arios.game.content.global.action.DoorActionHandler;
import org.arios.game.content.skill.Skills;
import org.arios.game.interaction.OptionHandler;
import org.arios.game.node.Node;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.object.GameObject;
import org.arios.game.world.map.Location;
import org.arios.game.world.update.flag.context.Animation;
import org.arios.plugin.Plugin;

/**
 * Represents the plugin used for the mining guild.
 * @author 'Vexia
 * @version 1.0
 */
public final class MiningGuildPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2113).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(30941).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(2112).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (option.equals("climb-down")) {
			if (player.getLocation().withinDistance(Location.create(3019, 3339, 0), 4)) {
				if (player.getSkills().getStaticLevel(Skills.MINING) < 60) {
					player.getDialogueInterpreter().open(382, NPC.create(382, Location.create(0, 0, 0)), 1);
					return true;
				}
				ClimbActionHandler.climb(player, new Animation(828), Location.create(3021, 9739, 0));
				return true;
			}
			ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			return true;
		}
		if (option.equals("open")) {
			if (player.getSkills().getStaticLevel(Skills.MINING) < 60) {
				player.getDialogueInterpreter().open(382, NPC.create(382, Location.create(0, 0, 0)), 1);
				return true;
			}
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
		}
		if (option.equals("climb-up")) {
			if (player.getLocation().withinDistance(new Location(3019, 9739, 0))) {
				ClimbActionHandler.climb(player, new Animation(828), new Location(3017, 3339, 0));
			} else {
				ClimbActionHandler.climbLadder(player, (GameObject) node, "climb-up");
			}
		}
		return true;
	}

}
