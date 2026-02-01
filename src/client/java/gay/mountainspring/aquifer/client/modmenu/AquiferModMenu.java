package gay.mountainspring.aquifer.client.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import gay.mountainspring.aquifer.config.AquiferConfig;

public class AquiferModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AquiferConfig.getConfigBuilder().setParentScreen(parent).build();
	}
}