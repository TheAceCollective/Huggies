package theacecollective.huggies;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils {

    public static String FormatMessage(String message, boolean ok) {
        if (ok) return ChatColor.GREEN + message;
        else return ChatColor.RED + message;
    }

    public static boolean CommandExecutorError(CommandSender sender, String message) {
        sender.sendMessage(FormatMessage(message, false));
        return true;
    }

}
