import ky.Asset;
import ky.Vector2D;
public class NSword extends DamageEntity{
    public Asset ninjasword;
    
    public NSword(Main main, Vector2D position, int player){
        super(main, position, 60, 20, 2, player, 200);
        //temporary sword pic, not that we need a new sword pic anyway
        ninjasword = new Asset("assets/characters/sword_p1-01", new Vector2D(0,0), 4); 

        ninjasword.setVisible(true);
        add(ninjasword);
    }

}

