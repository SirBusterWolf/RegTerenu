package pl.sirbusterwolf.regenerator.listener;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import pl.sirbusterwolf.regenerator.KBlock;
import pl.sirbusterwolf.regenerator.main;

public class onBlockExplode implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onexplode(EntityExplodeEvent e){
		if(!e.isCancelled()){
			if(FunnyGuilds.czyTerenGildi(e.getLocation())){
				 List<Block> blocks = e.blockList();
				 	if (!blocks.isEmpty()) {
				 		for (Block bb : blocks){
				 			if(main.getBL().contains(bb.getTypeId()+":"+bb.getData())){
					 			main.getBloki().add(new KBlock(bb.getLocation(),bb.getTypeId(),bb.getData()));
				 			}
					     }
					}
			}
		}
	}
}
