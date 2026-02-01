package gay.mountainspring.aquifer.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

public class AquiferTextureMaps {
	private AquiferTextureMaps() {}
	
	public static TextureMap anvil(Block anvil) {
		return TextureMap.all(anvil);
	}
	
	public static TextureMap anvil(Block top, Block body) {
		return anvil(TextureMap.getSubId(top, "_top"), TextureMap.getId(body));
	}
	
	public static TextureMap anvil(Identifier top, Identifier body) {
		return new TextureMap().put(TextureKey.TOP, top).put(AquiferTextureKeys.BODY, body);
	}
	
	public static TextureMap cauldron(Block cauldron) {
		return cauldron(TextureMap.getId(cauldron));
	}
	
	public static TextureMap cauldron(Identifier cauldron) {
		return new TextureMap()
				.put(TextureKey.BOTTOM, cauldron.withSuffixedPath("_bottom"))
				.put(TextureKey.INSIDE, cauldron.withSuffixedPath("_inner"))
				.put(TextureKey.SIDE, cauldron.withSuffixedPath("_side"))
				.put(TextureKey.TOP, cauldron.withSuffixedPath("_top"));
	}
	
	public static TextureMap cauldron(Block empty, Block contents) {
		return cauldron(TextureMap.getId(empty), TextureMap.getId(contents));
	}
	
	public static TextureMap cauldron(Block empty, Block contents, String contentsSuffix) {
		return cauldron(TextureMap.getId(empty), TextureMap.getSubId(contents, contentsSuffix));
	}
	
	public static TextureMap cauldron(Identifier empty, Identifier contents) {
		return cauldron(empty).put(TextureKey.CONTENT, contents);
	}
	
	public static TextureMap composter(Block composter) {
		return new TextureMap()
				.put(TextureKey.BOTTOM, TextureMap.getSubId(composter, "_bottom"))
				.copy(TextureKey.BOTTOM, TextureKey.INSIDE)
				.put(TextureKey.SIDE, TextureMap.getSubId(composter, "_side"))
				.put(TextureKey.TOP, TextureMap.getSubId(composter, "_top"));
	}
	
	public static TextureMap lectern(Block lectern, Block bottom) {
		return lectern(lectern, TextureMap.getId(bottom));
	}
	
	public static TextureMap lectern(Block lectern, Identifier bottom) {
		return new TextureMap()
				.put(AquiferTextureKeys.BASE, TextureMap.getSubId(lectern, "_base"))
				.put(TextureKey.BOTTOM, bottom)
				.put(TextureKey.FRONT, TextureMap.getSubId(lectern, "_front"))
				.put(TextureKey.SIDE, TextureMap.getSubId(lectern, "_sides"))
				.put(TextureKey.TOP, TextureMap.getSubId(lectern, "_top"));
	}
}