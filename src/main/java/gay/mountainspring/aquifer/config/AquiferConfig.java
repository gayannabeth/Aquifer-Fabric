package gay.mountainspring.aquifer.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import net.minecraft.text.Text;
import net.minecraft.util.JsonHelper;

public class AquiferConfig {
	private TagHandlingLevel tagHandlingLevel = TagHandlingLevel.FALLBACK_TO_VANILLA;
	
	private AquiferConfig(TagHandlingLevel level) {
		this.tagHandlingLevel = level;
	}
	
	private AquiferConfig() {}
	
	private static AquiferConfig INSTANCE = new AquiferConfig();
	
	public static AquiferConfig getInstance() {return INSTANCE;}
	
	public TagHandlingLevel getTagHandlingLevel() {return this.tagHandlingLevel;}
	
	public static void init() {
		File file = Paths.get("config/aquifer.json").toFile();
		Gson gson = Aquifer.GSON;
		
		try {
			if (!file.exists()) createNewConfigFile(file);
			
			FileReader fileReader = new FileReader(file);
			
			INSTANCE = gson.fromJson(fileReader, AquiferConfig.class);
			
			if (INSTANCE == null) INSTANCE = new AquiferConfig();
			
			fileReader.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		Aquifer.LOGGER.info("Tag handling level: ", INSTANCE.tagHandlingLevel);
	}
	
	public static void save() {
		File file = Paths.get("config/aquifer.json").toFile();
		Gson gson = Aquifer.GSON;
		
		try {
			if (!file.exists()) file.createNewFile();
			FileWriter fileWriter = new FileWriter(file, false);
			
			gson.toJson(INSTANCE, fileWriter);
			
			fileWriter.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private static void createNewConfigFile(File file) throws IOException {
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		Gson gson = Aquifer.GSON;
		gson.toJson(new AquiferConfig(), fileWriter);
		fileWriter.close();
	}
	
	public static ConfigBuilder getConfigBuilder() {
		ConfigBuilder builder = ConfigBuilder.create()
				.setTitle(Text.translatable(Aquifer.MOD_ID + ".config"))
				.setSavingRunnable(AquiferConfig::save);
		
		ConfigCategory cat = builder.getOrCreateCategory(Text.of("dummy"));
		
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		
		cat.addEntry(entryBuilder.startDropdownMenu(Text.translatable(Aquifer.MOD_ID + ".config.tag_handling_level"),
				DropdownMenuBuilder.TopCellElementBuilder.of(INSTANCE.tagHandlingLevel, str -> TagHandlingLevel.NAME_TO_LEVEL.getOrDefault(str, TagHandlingLevel.FALLBACK_TO_VANILLA)),
				DropdownMenuBuilder.CellCreatorBuilder.of(level -> Text.translatable(level.getTranslationKey())))
				.setDefaultValue(TagHandlingLevel.FALLBACK_TO_VANILLA)
				.setSelections(List.of(TagHandlingLevel.values()))
				.setSaveConsumer(level -> INSTANCE.tagHandlingLevel = level)
				.setTooltip(Text.translatable(Aquifer.MOD_ID + ".config.tag_handling_level.tooltip"))
				.setTooltipSupplier(level -> Optional.of(new Text[] {Text.translatable(level.getTooltipTranslationKey())}))
				.build());
		
		return builder;
	}
	
	private static final AquiferConfig.Serializer SERIALIZER = new AquiferConfig.Serializer();
	
	public static AquiferConfig.Serializer getSerializer() {
		return SERIALIZER;
	}
	
	private static class Serializer implements JsonSerializer<AquiferConfig>, JsonDeserializer<AquiferConfig>, InstanceCreator<AquiferConfig> {
		@Override
		public AquiferConfig createInstance(Type type) {
			return new AquiferConfig();
		}
		
		@Override
		public AquiferConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json.isJsonObject()) {
				JsonObject jsonObject = json.getAsJsonObject();
				TagHandlingLevel level = TagHandlingLevel.NAME_TO_LEVEL.getOrDefault(JsonHelper.getString(jsonObject, "tag_handling_level", "fallback_to_vanilla"), TagHandlingLevel.FALLBACK_TO_VANILLA);
				return new AquiferConfig(level);
			} else {
				throw new JsonParseException("Given json element is not a json object!");
			}
		}
		
		@Override
		public JsonElement serialize(AquiferConfig src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("tag_handling_level", src.tagHandlingLevel.asString());
			return jsonObject;
		}
	}
}