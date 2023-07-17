package me.reimnop.d4f;

import me.reimnop.d4f.console.ConsoleChannelHandler;
import me.reimnop.d4f.customevents.CustomEvents;
import me.reimnop.d4f.customevents.actions.ModActions;
import me.reimnop.d4f.commands.ModCommands;
import me.reimnop.d4f.listeners.*;
import me.reimnop.d4f.exceptions.GuildException;
import me.reimnop.d4f.utils.Utils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Discord4Fabric implements ModInitializer {
    public static final String MODID = "d4f";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static final Config CONFIG = new Config();
    public static Discord DISCORD;
    public static AccountLinking ACCOUNT_LINKING;
    public static CustomEvents CUSTOM_EVENTS;

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    public static void kickForUnlinkedAccount(ServerPlayerEntity player) {
        UUID uuid = player.getUuid();
        ACCOUNT_LINKING.tryQueueForLinking(uuid);
        String code = ACCOUNT_LINKING.getCode(uuid);

        MutableText reason = Text.empty()
                        .append(Text.literal("Чтобы зайти на сервер, вам нужно привязать аккаунт Discord к аккаунту Minecraft!\n"))
                        .append(Text.literal("Ваш код привязки: "))
                        .append(Text.literal(code)
                                .formatted(Formatting.RED, Formatting.UNDERLINE))
                        .append(Text.literal("\nДля привязки аккаунта Discord к аккаунту Minecraft, отправьте данный код в ЛС боту"));

        player.networkHandler.disconnect(reason);
    }

    @Override
    public void onInitialize() {
        try {
            if (tryInitConfig()) {
                initDiscord();
                EventRedirect.init();
                ModActions.init();
                initCustomEvents();
                ModCommands.init();
            }
        } catch (LoginException e) {
            LOGGER.error("Не удалось соединиться с ботом! Пожалуйста, обновите конфигурацию и перезапустите сервер");
        } catch (Exception e) {
            Utils.logException(e);
        }
    }

    private boolean tryInitConfig() throws IOException {
        File file = new File(Utils.getConfigPath());
        if (!file.exists()) {
            CONFIG.writeConfig(file);
            LOGGER.error(
                    String.format(
                            "Файл конфигурации не найден! Новый файл сгенерирован по пути: %s",
                            file.getAbsolutePath()
                    )
            );
            LOGGER.error("Пожалуйста, обновите конфигурацию и перезапустите сервер");
            return false;
        }

        CONFIG.readConfig(file);
        return true;
    }

    private void initDiscord() throws LoginException, GuildException, InterruptedException, IOException {
        ACCOUNT_LINKING = new AccountLinking();

        File file = new File(Utils.getUserdataPath());
        if (file.exists()) {
            ACCOUNT_LINKING.read(file);
        }

        DISCORD = new Discord(CONFIG);
        DISCORD.initCache();

        // init console
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ConsoleMessageListener consoleMessageListener = new ConsoleMessageListener();
        consoleMessageListener.start();
        ctx.getRootLogger().addAppender(consoleMessageListener);
        ctx.updateLoggers();

        MinecraftEventListeners.init(DISCORD, ACCOUNT_LINKING, CONFIG);
        DiscordCommandProcessor.init(CONFIG);
        ConsoleChannelHandler.init(CONFIG, DISCORD);
    }

    private void initCustomEvents() {
        CUSTOM_EVENTS = new CustomEvents();
        CustomEventsHandler.init(CONFIG, CUSTOM_EVENTS);

        try {
            File file = new File(Utils.getCustomEventsPath());
            if (file.exists()) {
                CUSTOM_EVENTS.read(file);
            } else {
                // Generate empty events file for the user
                if (file.createNewFile()) {
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write("{}");
                    }
                }
                LOGGER.warn("Не найден файл событий! Новый файл сгенерирован");
            }
        } catch (IOException e) {
            Utils.logException(e);
        }
    }
}
