package gay.mountainspring.aquifer.advancement;

import java.util.List;
import java.util.Map;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;

public class AdvancementUtil {
	private AdvancementUtil() {}
	
	@SuppressWarnings("removal")
	public static Advancement.Builder unbuild(Advancement advancement) {
		Advancement.Builder builder = advancement.sendsTelemetryEvent() ? Advancement.Builder.create() : Advancement.Builder.createUntelemetered();
		
		if (advancement.parent().isPresent())
			builder.parent(advancement.parent().get());
		
		if (advancement.display().isPresent())
			builder.display(advancement.display().get());
		
		builder.rewards(advancement.rewards());
		
		for (Map.Entry<String, AdvancementCriterion<?>> criterionEntry : advancement.criteria().entrySet()) {
			builder.criterion(criterionEntry.getKey(), criterionEntry.getValue());
		}
		
		List<List<String>> requirements = advancement.requirements().requirements();
		
		if (requirements.size() == 1) {
			if (requirements.get(0).size() > 1) {
				builder.criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
			}
		} else {
			for (List<String> sublist : requirements) {
				if (sublist.size() > 1) {
					builder.requirements(advancement.requirements());
					break;
				}
			}
		}
		
		return builder;
	}
}