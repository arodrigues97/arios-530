package plugin.interaction.inter;

import org.arios.game.component.Component;
import org.arios.game.component.ComponentDefinition;
import org.arios.game.component.ComponentPlugin;
import org.arios.game.content.skill.free.crafting.armour.LeatherCrafting;
import org.arios.game.content.skill.free.crafting.armour.SoftCraftPulse;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.entity.player.link.RunScript;
import org.arios.game.node.item.Item;
import org.arios.plugin.Plugin;

/**
 * Represents the leather crafting interface.
 * @author 'Vexia
 * @version 1.0
 */
public final class LeatherCraftInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(154, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		int amount = 0;
		final LeatherCrafting.SoftLeather soft = LeatherCrafting.SoftLeather.forButton(button);
		if (soft == null) {
			return true;
		}
		switch (opcode) {
		case 155:
			amount = 1;
			break;
		case 196:
			amount = 5;
			break;
		case 124:
			amount = player.getInventory().getAmount(new Item(LeatherCrafting.LEATHER));
			break;
		case 199:
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					player.getPulseManager().run(new SoftCraftPulse(player, new Item(LeatherCrafting.LEATHER), soft, (int) getValue()));
					return true;
				}
			});
			player.getDialogueInterpreter().sendInput(false, "Enter amount:");
			return true;
		}
		player.getPulseManager().run(new SoftCraftPulse(player, null, soft, amount));
		return true;
	}
}
