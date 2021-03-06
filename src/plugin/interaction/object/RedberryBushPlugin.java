package plugin.interaction.object;

import org.arios.cache.def.impl.ObjectDefinition;
import org.arios.game.interaction.OptionHandler;
import org.arios.game.node.Node;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.item.Item;
import org.arios.game.node.object.GameObject;
import org.arios.game.node.object.ObjectBuilder;
import org.arios.game.world.update.flag.context.Animation;
import org.arios.plugin.Plugin;

/**
 * Represents the plugin used to handle picking from a red berry bush.
 * @author 'Vexia
 */
public class RedberryBushPlugin extends OptionHandler {

	/**
	 * Represents the red berries item.
	 */
	private final Item RED_BERRIES = new Item(1951);

	/**
	 * Represents the picking berries animation.
	 */
	private final Animation ANIMATION = new Animation(2282);

	/**
	 * Represents the counter.
	 */
	private int counter = 0;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(23628).getConfigurations().put("option:pick-from", this);
		ObjectDefinition.forId(23629).getConfigurations().put("option:pick-from", this);
		ObjectDefinition.forId(23630).getConfigurations().put("option:pick-from", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (((GameObject) node).getId() == 23630) {
			player.getPacketDispatch().sendMessage("There are no berries left on this bush.");
			player.getPacketDispatch().sendMessage("More berries will grow soon.");
			return true;
		}
		if (!player.getInventory().add(RED_BERRIES)) {
			player.getPacketDispatch().sendMessage("Your inventory is too full to pick the berries from the bush.");
			return true;
		}
		player.lock(4);
		player.animate(ANIMATION);
		if (counter == 2) {
			ObjectBuilder.replace(((GameObject) node), new GameObject(23630, node.getLocation()), 30);
			counter = 0;
			return true;
		}
		counter++;
		return true;
	}

}
