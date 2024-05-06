import ky.Asset;
import ky.Vector2D;
public class NSword extends DamageEntity{
    public Asset ninjasword;
    
    public NSword(Main main, Vector2D position, int player){
        super(main, position, 60, 20, 2, player, 200);
        ninjasword = new Asset("assets/characters/stab/sword.png", new Vector2D(0,0), 4); 

        ninjasword.setVisible(true);
        add(ninjasword);
    }

}

