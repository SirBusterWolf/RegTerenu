package pl.sirbusterwolf.regenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.sirbusterwolf.regenerator.listener.onBlockExplode;

public class main extends JavaPlugin{
	static JavaPlugin core;
	static YamlConfiguration msg;
	static File m;
	static List<KBlock> blocksp = new ArrayList<KBlock>();
	static List<String> jakiebloki = new ArrayList<String>();
	static ConsoleCommandSender cons = Bukkit.getConsoleSender();
	
	static void loadconfig(){
		for(String s : msg.getStringList("data")){
			String[] spl = s.split(",");
			KBlock x = new KBlock(new Location(Bukkit.getWorld(core.getConfig().getString("swiat")),Integer.parseInt(spl[1]),Integer.parseInt(spl[2]),Integer.parseInt(spl[3])),Integer.parseInt(spl[0]),Byte.valueOf(spl[4]));
			blocksp.add(x);
		}
	}
	public void onEnable(){
		core=this;
		m=new File(core.getDataFolder(),"baza.yml");
		saveDefaultConfig();
		if(!m.exists()){
			core.saveResource("baza.yml", true);
		}
		
		msg = YamlConfiguration.loadConfiguration(m);
		jakiebloki=core.getConfig().getStringList("jakie");
		timer();
		loadconfig();
		cons.sendMessage("[Regenerator]§a§lPlugin zostal aktywowany");
		Bukkit.getPluginManager().registerEvents(new onBlockExplode(), this);
	}

	public static List<KBlock> getBloki(){
		return blocksp;
	}
	public static List<String> getBL(){
		return jakiebloki;
	}
	public JavaPlugin getCore(){
		return core;
	}
	 void timer(){
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					if(blocksp.size()>0){
						try{
							for(KBlock b : blocksp){
								blocksp.remove(b);
								Block bb = b.getL().getBlock();
								bb.setTypeIdAndData(b.getM(), b.getB(), true);
							}
						}catch(Exception e){
						}
					}
				}
				
			}, 20, (20*getConfig().getInt("Coile")));
		}
	 
	public void onDisable(){
		try{
			List<String> tymczas = new ArrayList<String>();
			for(KBlock b : blocksp){
				tymczas.add(b.getM()+","+(int) b.getL().getX()+","+(int)b.getL().getY()+","+(int)b.getL().getZ()+","+b.getB());
			}
			msg.set("data", tymczas);
			msg.save(m);
			cons.sendMessage("[Regenerator]§a§lConfigi zostaly poprawnie zapisane");
		}catch(Exception e ){
			cons.sendMessage("[Regenerator]§c§lWystapil blad podczas zapisywania configow");
		}
		cons.sendMessage("[Regenerator]§c§lPlugin zostal dezaktywowany");
	}
}
