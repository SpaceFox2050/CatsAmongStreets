import java.util.ArrayList;

import ky.Asset;
import ky.AudioPlayer;
import ky.Vector2D;

public class Stab extends Character {

    Sword sword;
    AudioPlayer knifeSound;

    public Stab(Main main) {
        super(new Vector2D(0, 0), 75, 140, 1700, main);
    }

    public Stab(Vector2D position, Main main) {
        super(position, 75, 140, 1700, main);
    }

    @Override
    public void initialize() {
        setStats("characterStats/stab.stats");
        sword = new Sword(main, new Vector2D(getX(), getY()), getPlayer(), 175);
        add(sword);

        if(getPlayer() == 1){
            abilityIcon = new Asset("assets/characters/stab/sword_boost_icon_p1.png", new Vector2D(0, 0), 4);
            ultIcon = new Asset("assets/characters/stab/sword_ult_icon_p1.png", new Vector2D(0, 0), 4);
        }else{
            abilityIcon = new Asset("assets/characters/stab/sword_boost_icon_p2.png", new Vector2D(0, 0), 4);
            ultIcon = new Asset("assets/characters/stab/sword_ult_icon_p2.png", new Vector2D(0, 0), 4);
        }
        
        
        

        knifeSound = new AudioPlayer("assets/SFX/knife_swing.wav");
        
        if(getPlayer() == 1){
            characterAsset = new Asset(new String[]{
                "assets/characters/stab/stab_p1.png",
                "assets/characters/stab/stab_p1.png"
            }, new Vector2D(0, 0), 70, 140, 3);
        }else{
            characterAsset = new Asset(new String[]{
                "assets/characters/stab/stab_p2.png",
                "assets/characters/stab/stab_p2.png"
            }, new Vector2D(0, 0), 70, 140, 3);
        }
        characterAsset.setVisible(true);
        add(characterAsset);
    }

    double tempOffSet = 0;
    double eee = 500;

    double abilityUpTime = 2;
    double ultimateUpTime = 5;

    Direction lastDirection = Direction.RIGHT;

    @Override
    public void updateCharacter(double deltaT, ArrayList<Integer> keyCodes) {

        if (direction != lastDirection) {
            sword.swordAsset.flipHorizontal();
            lastDirection = direction;
        }

        sword.knockbackDir = direction.getValue();

        if (sword.getDamage() == 275) {
            abilityUpTime -= deltaT;
            if (abilityUpTime <= 0) {
                sword.setDamage(175);
                sword.swordAsset.setImageIndex(0);
                abilityUpTime = 2;
            }
        }
        if (sword.getDamage() == 400) {
            ultimateUpTime -= deltaT;
            if (ultimateUpTime <= 0) {
                sword.setDamage(175);
                sword.setCollisionBoxDimensions(sword.getCollisionBox().width / 2, sword.getCollisionBox().height / 2);
                sword.swordAsset.setImageIndex(0);
                sword.swordAsset.rescale(0.5);
                ultimateUpTime = 5;
            }
        }

        if (status == Status.ATTACKING) {
            sword.setPos(new Vector2D(tempOffSet * direction.getValue() + eee * deltaT * direction.getValue(), 0));
            sword.addPos(this.getPos());
            tempOffSet += eee * deltaT;
            if (tempOffSet >= 120) {
                tempOffSet = 120;
                eee = -500;
            }
            if (tempOffSet <= 0) {
                tempOffSet = 0;
                eee = 500;
                status = Status.IDLE;
                sword.canDamage = false; // Reset the canDamage state so we can't just walk into the enemy after
                                         // swinging to hit them
            }
        } else {
            sword.setPos(this.getPos());
        }
    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            knifeSound.reset();
            knifeSound.play();
            status = Status.ATTACKING;
            sword.canDamage = true;
        }
    }

    @Override
    protected void basicAbility() {
        if (sword.getDamage() == 175) {
            sword.swordAsset.setImageIndex(1);
            abilityUpTime = 2;
            sword.setDamage(275);
        }
    }

    @Override
    protected void ultimate() {
        ultimateCooldown = 20;
        sword.setCollisionBoxDimensions(sword.getCollisionBox().width * 2, sword.getCollisionBox().height * 2);
        sword.swordAsset.rescale(2);
        sword.swordAsset.setImageIndex(2);
        sword.setDamage(400);
        ultimateUpTime = 5;
    }

}
