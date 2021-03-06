package plugin.interaction.item.withitem;

import org.arios.game.interaction.NodeUsageEvent;
import org.arios.game.interaction.UseWithHandler;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.item.Item;
import org.arios.game.node.object.GameObject;
import org.arios.game.node.object.ObjectBuilder;
import org.arios.plugin.Plugin;

/**
 * Represents the plugin used to handle the usage of a chest key on the chest.
 * @author 'Vexia
 * @version 1.0
 */
public final class ChestKeyPlugin extends UseWithHandler {

	/**
	 * Represents the chest key item.
	 */
	private static final Item CHEST_KEY = new Item(432);

	/**
	 * Represents the pirate message item.
	 */
	private static final Item PIRATE_MESSAGE = new Item(433);

	/**
	 * Constructs a new {@code ChestKeyPlugin} {@code Object}.
	 */
	public ChestKeyPlugin() {
		super(432);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(2079, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getInventory().remove(CHEST_KEY)) {
			ObjectBuilder.replace((GameObject) event.getUsedWith(), ((GameObject) event.getUsedWith()).transform(2080), 3);
			player.getInventory().add(PIRATE_MESSAGE);
			player.getPacketDispatch().sendMessage("You unlock the chest.");
			player.getPacketDispatch().sendMessage("All that's in the chest is a message...");
			player.getPacketDispatch().sendMessage("You take the message from the chest.");
		}
		return true;
	}

}
