
package my.package;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;


/** <pre>
MINECRAFT PLUGIN DEBUGGING CLASS

i wrote this class to debug my plugins, not so great but helps

to use it add these lines in your plugin main class:
<i>
// global scope
Debug debug;

// onEnable() method
debug = new Debug(this);

// onCommand() method, right after match your command and before anything else
if ( debug.check(sender, args) ) // types = CommandSender, String[]
  return true;

// in some overriden event method
debug.event(e); // e = related Event
</i>
then add the code you want to debug in command(), test() and event() methods

then compile your plugin and run with bukkit

to debug just enter these commands in minecraft chat console:
- to run explicit test: /yourCommand z
- to toggle interval test: /yourCommand zz

to devug the specified event just fire it, like moving (for PlayerMoveEvent) or something else
</pre> */

class Debug
{
	
	private Main plugin;
	private Set<Player> players = new HashSet<Player>();
	// add the declarations you want here
	
	
	Debug(Main plugin)
	{
		this.plugin = plugin;
		
		// initialize the properties you declared in global scope here
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				for (Player player : players)
					if ( player.isOnline() )
						test(player);
					else
						players.remove(player);
			}
		}.runTaskTimer(plugin, 1, 40); // <-- you can adjust the test interval here
	}
	
	
	boolean check(CommandSender sender, String[] args)
	{
		if ( args.length < 1 )
			return false;
		
		if ( args[0].equals("z") )
			return command(sender, args);
		
		if ( args[0].equals("zz") && sender instanceof Player )
			return toggle((Player) sender);
		
		return false;
	}
	
	
	private boolean toggle(Player player)
	{
		// you may not need to modify this method but others
		if ( players.contains(player) )
		{
			players.remove(player);
			player.sendMessage("--- TEST DISABLED ---");
		}
		else
		{
			players.add(player);
			player.sendMessage("--- TEST ENABLED ---");
		}
		return true;
	}

	
	private void test(Player player)
	{
		// add the test you want to run at intervals
		
	}
	
	
	boolean command(CommandSender sender, String[] args)
	{
		// process the command you want
		
		return true;
	}
	
	
	void event(Event e)
	{
		// process the event you want, remember to change class or cast it before
		
	}
}
