package plugin.dialogue;

import org.arios.game.content.dialogue.DialoguePlugin;
import org.arios.game.content.dialogue.FacialExpression;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;

/**
 * Rerpesents the dialogue plugin used for the celburg npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class CeolburgDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CeolburgDialogue} {@code Object}.
	 */
	public CeolburgDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CeolburgDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CeolburgDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CeolburgDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NORMAL, "Hi!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Trolls!");
			stage = 1;
			break;
		case 1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1089 };
	}
}