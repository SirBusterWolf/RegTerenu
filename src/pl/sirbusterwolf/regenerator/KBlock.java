package pl.sirbusterwolf.regenerator;

import org.bukkit.Location;
public class KBlock {
	 Location l;
	 int m;
	 Byte s;
	
	public KBlock(Location lf,int mf,Byte sf){
		this.l=lf;
		this.m=mf;
		this.s=sf;
	}
	public Location getL(){
		return this.l;
	}
	public int getM(){
		return this.m;
	}
	public Byte getB(){
		return this.s;
	}
}
