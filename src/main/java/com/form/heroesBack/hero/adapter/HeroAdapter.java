package com.form.heroesBack.hero.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.form.heroesBack.hero.entity.Hero;
import com.form.heroesBack.hero.entity.to.HeroEto;

public class HeroAdapter {
    
    /** 
     * @param hero
     * @return HeroEto
     */
    public static HeroEto toEto(Hero hero) {
        HeroEto heroEto = new HeroEto();
        heroEto.setFirstName(hero.getFirstName());
        heroEto.setHeroName(hero.getHeroName());
        heroEto.setHeroPower(hero.getHeroPower());
        heroEto.setLastName(hero.getLastName());
        heroEto.setId(hero.getId());
        return heroEto;
    }


    
    /** 
     * @param heroes
     * @return List<HeroEto>
     */
    public static List<HeroEto> toEto(Iterable<Hero> heroes) {
        List<HeroEto> listHero = new ArrayList<HeroEto>();
        
        Iterator iterator = heroes.iterator();
        while(iterator.hasNext()) {
            HeroEto heroEto = toEto((Hero)iterator.next());
            listHero.add(heroEto);
        }
        return listHero;
    }

}
