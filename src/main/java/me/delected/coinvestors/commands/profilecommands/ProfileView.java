//package me.delected.coinvestors.commands.profilecommands;
//
//import me.delected.coinvestors.color.ChatUtils;
//import me.delected.coinvestors.commands.SubCommand;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.command.ConsoleCommandSender;
//import org.bukkit.entity.Player;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class ProfileView implements SubCommand {
//    @Override
//    public String getPrimaryName() { return "view"; }
//
//    @Override
//    public String getDescription() { return ChatUtils.color("&fview - &7View a player's profile, or view your own!"); }
//
//    @Override
//    public List<String> getAliases() {
//        return Arrays.asList("view", "see", "check", "show");
//    }
//
//    @Override
//    public String getUsage() { return " (optional: username)"; }
//
//    @Override
//    public String getPermission() { return "profile.view"; }
//
//    @Override
//    public boolean canConsoleRun() { return true; }
//
//
//    public boolean run(CommandSender sender, String[] args) {
//        if (args.length == 1) {
//            if (sender instanceof ConsoleCommandSender) {
//                sender.sendMessage(ChatColor.RED + "Only players can see their own profile, please add a name to see.");
//                return true;
//            }
//            Player p = (Player) sender;
//            if (!YamlUtils.hasProfile(p)) {
//                p.sendMessage(ChatColor.RED + "You do not have a profile! Create one with /profile create");
//                return true;
//            }
//
//            displayProfile(p, p.getName());
//            return true;
//
//        } else if (args.length == 2) {
//            displayProfile(sender, args[1]);
//            return true;
//        } else {
//            sendIncorrectArgMsg(sender, "Profile", "View");
//            return true;
//        }
//    }
//
//    @Override
//    public List<String> getTabCompletions(CommandSender sender, String[] args) {
//        if (args.length == 2) {
//            return getOnlinePlayersList();
//        }
//        return null;
//    }
//
//    private boolean displayProfile(CommandSender sender, String target) {
//        Player targeted = Bukkit.getPlayer(target);
//        if (targeted == null || !targeted.isOnline()) {
//            sender.sendMessage(ChatColor.RED + "This player does not exist, or is not online!");
//            return true;
//        }
//        sender.sendMessage(ChatUtils.color("&8&m----------&8[ &f" + target + "'s &9Profile &8]&m----------"));
//        sender.sendMessage(ChatColor.GREEN + "Your crypto wallet addresses:");
//        sender.sendMessage(ChatUtils.color("&8&m-------------------------------------------"));
//
//        return true;
//    }
//}
