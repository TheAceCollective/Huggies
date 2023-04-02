package theacecollective.huggies.commandexecutors;

import theacecollective.huggies.Huggies;
import theacecollective.huggies.Utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AbstractCommandExecutor implements CommandExecutor {

    private final ConfigurationSection EasterEggs;
    private final String ReceiverNotOnlineError, SenderNotAPlayerError, MessageToSender, MessageToReceiver;

    private static String FormatMessage(String message, Player sender, Player receiver) {
        message = message.replaceAll("%sender%", sender.getDisplayName());
        if (receiver != null) message = message.replaceAll("%receiver%", receiver.getDisplayName());

        return message;
    }

    protected AbstractCommandExecutor(Huggies huggiesPlugin, String easterEggsKey, String receiverNotOnlineError, String senderNotAPlayerError, String messageToSender, String messageToReceiver) {
        EasterEggs = huggiesPlugin.getConfig().getConfigurationSection(easterEggsKey);

        ReceiverNotOnlineError = receiverNotOnlineError;
        SenderNotAPlayerError = senderNotAPlayerError;
        MessageToSender = messageToSender;
        MessageToReceiver = messageToReceiver;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        Player player = (Player) sender;
        if (player == null) return Utils.CommandExecutorError(sender, SenderNotAPlayerError);

        if (EasterEggs.contains(args[0].toLowerCase())) {
            List<String> availableEasterEggs = EasterEggs.getStringList(args[0].toLowerCase());
            player.sendMessage(ChatColor.GREEN + FormatMessage(availableEasterEggs.get((int) Math.floor(Math.random() * availableEasterEggs.size())), player, null));
            return true;
        }

        Player affected = Bukkit.getPlayer(args[0]);
        if (affected == null || !player.canSee(affected)) return Utils.CommandExecutorError(sender, ReceiverNotOnlineError);

        player.sendMessage(FormatMessage(MessageToSender, player, affected));
        affected.sendMessage(FormatMessage(MessageToReceiver, player, affected));

        return true;
    }

}
