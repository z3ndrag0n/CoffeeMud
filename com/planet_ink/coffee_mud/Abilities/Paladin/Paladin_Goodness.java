package com.planet_ink.coffee_mud.Abilities.Paladin;
import com.planet_ink.coffee_mud.Abilities.StdAbility;
import com.planet_ink.coffee_mud.Abilities.Prayers.Prayer;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Paladin_Goodness extends Paladin
{
	private boolean tickTock=false;
	public Paladin_Goodness()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="Paladin's Goodness";
		paladinsGroup=new Vector();
	}

	public Environmental newInstance()
	{
		return new Paladin_Goodness();
	}

	public boolean tick(int tickID)
	{
		if(!super.tick(tickID)) 
			return false;
		tickTock=!tickTock;
		if(tickTock)
		{
			MOB mob=(MOB)invoker;
			for(int m=0;m<mob.location().numInhabitants();m++)
			{
				MOB target=mob.location().fetchInhabitant(m);
				if((target!=null)
				&&(target.getAlignment()<350)
			    &&(profficiencyCheck(0,false))
				&&((paladinsGroup.contains(target))
					||((target.getVictim()==invoker)&&(target.rangeToTarget()==0))))
				{
					int harming=Dice.roll(1,15,0);
					if(target.getAlignment()<350)
						ExternalPlay.postDamage(invoker,target,this,harming,Affect.ACT_EYES|Affect.MASK_MALICIOUS|Affect.TYP_UNDEAD,Weapon.TYPE_BURSTING,"The aura of goodness around <S-NAME> <DAMAGE> <T-NAME>!");
				}
			}
		}
		return true;
	}

}
