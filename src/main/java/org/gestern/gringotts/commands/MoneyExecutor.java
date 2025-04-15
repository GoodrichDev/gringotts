package org.gestern.gringotts.commands;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.gestern.gringotts.Language;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Player commands.
 */
public class MoneyExecutor extends GringottsAbstractExecutor {
    private static final List<String> commands = Arrays.asList("");

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender       Source of the command
     * @param cmd          Command which was executed
     * @param commandLabel Alias of the command which was used
     * @param args         Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String commandLabel,
                             String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Language.LANG.playerOnly);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            // same as balance
            sendBalanceMessage(eco.player(player.getUniqueId()));

            return true;
        }

        if (args.length < 2) {
            player.sendMessage(ChatColor.GREEN + "You can only use /money");
            return true;
        }

        return false;
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Override
    public List<String> onTabComplete(CommandSender sender,
                                      Command command,
                                      String alias,
                                      String[] args) {
        String cmd = args[0].toLowerCase();

        if (args.length == 1) {
            return commands.stream()
                    .filter(com -> startsWithIgnoreCase(com, args[0]))
                    .collect(Collectors.toList());
        }

        return Lists.newArrayList();
    }
}
