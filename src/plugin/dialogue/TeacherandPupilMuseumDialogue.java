package plugin.dialogue;

import org.arios.game.content.dialogue.DialoguePlugin;
import org.arios.game.content.dialogue.FacialExpression;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;

/**
 * Handles the TeacherandPupilMuseumDialogue dialogue.
 * @author 'Vexia
 */
public class TeacherandPupilMuseumDialogue extends DialoguePlugin {

	public TeacherandPupilMuseumDialogue() {

	}

	public TeacherandPupilMuseumDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5947 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TeacherandPupilMuseumDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(5950, FacialExpression.NORMAL, "Stop pulling, we've plenty of time to see everything.");
		stage = 0;
		return true;
	}
}