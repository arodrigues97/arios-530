package plugin.skill.herblore;

import org.arios.game.content.dialogue.SkillDialogueHandler;
import org.arios.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.arios.game.content.skill.member.herblore.GenericPotion;
import org.arios.game.content.skill.member.herblore.HerblorePulse;
import org.arios.game.content.skill.member.herblore.UnfinishedPotion;
import org.arios.game.interaction.NodeUsageEvent;
import org.arios.game.interaction.UseWithHandler;
import org.arios.game.node.entity.player.Player;
import org.arios.plugin.Plugin;

/**
 * Represents the plugin used to handle the creation of creation an unf-potion.
 * @author 'Vexia
 */
public final class UnfinishedPotionPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code UnfinishedPotionPlugin} {@code Object}.
	 */
	public UnfinishedPotionPlugin() {
		super(227, 5935);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (UnfinishedPotion potion : UnfinishedPotion.values()) {
			addHandler(potion.getIngredient().getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final UnfinishedPotion unf = UnfinishedPotion.forItem(event.getUsedItem(), event.getBaseItem());
		if (unf == null) {
			return false;
		}
		final GenericPotion potion = GenericPotion.transform(unf);
		final Player player = event.getPlayer();
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, potion.getProduct()) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new HerblorePulse(player, potion.getBase(), amount, potion));
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(potion.getBase());
			}

		};
		if (player.getInventory().getAmount(potion.getBase()) == 1) {
			handler.create(0, 1);
		} else {
			handler.open();
		}
		return true;
	}

}
