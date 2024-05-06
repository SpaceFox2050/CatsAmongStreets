import ky.Asset;
import ky.Vector2D;

public class Sword extends DamageEntity {

    public Asset swordAsset;

    public Sword(Main main, Vector2D position, int player, double damage) {
        super(main, position, 60, 20, 3, player, damage, 500);

        if(getPlayer() == 1){
            swordAsset = new Asset(
                new String[] { "assets/characters/stabbyrobot/sword_p1.png",
                        "assets/characters/stabbyrobot/sword_boost_p1.png",
                        "assets/characters/stabbyrobot/sword_ult_p1.png" },
                new Vector2D(0, 0), 60, 20, 3);
        }else{
            swordAsset = new Asset(
                new String[] { "assets/characters/stabbyrobot/sword_p2.png",
                        "assets/characters/stabbyrobot/sword_boost_p2.png",
                        "assets/characters/stabbyrobot/sword_ult_p2.png" },
                new Vector2D(0, 0), 60, 20, 3);
        }

        swordAsset.setVisible(true);
        add(swordAsset);
        setBreaks(false);
        setVisible(true);
    }
}