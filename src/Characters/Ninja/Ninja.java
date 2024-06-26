import java.util.ArrayList;

import ky.Asset;
import ky.AudioPlayer;
import ky.Vector2D;
public class Ninja extends Character {

    private NSword NSword;
    AudioPlayer swordswing;
    AudioPlayer healthsfx;
    AudioPlayer teleport;
    Direction lastDirection;
    private Asset healthup;
    boolean canReduceCD;

    double ultuptime;
    double offset = 0;
    double temp = 1000;

    public Ninja(Main main){
        super(new Vector2D(0, 0), 75, 125, 1000, main);
    }

    public Ninja(Vector2D position, Main main){
        super(position, 75, 125, 1000, main);
    }

    @Override
    public void initialize(){
        setStats("characterStats/Ninja.stats");

        curAbilityCooldown = abilityCooldown;
        curUltCooldown = ultimateCooldown;

        NSword = new NSword(main, new Vector2D(0, 0), getPlayer());
        NSword.setVisible(true);
        add(NSword);

        

        //rocket = new Rocket ("assets/characters/Ninja/freezerocket.png", new Vector2D(0, 0), getPlayer(), main, 200, 700);
        //add(rocket);
        //was originally gonna make a knockback rocket as ability but changed it to heal instead

        if(getPlayer() == 1){
            characterAsset = new Asset(new String[]{
                "assets/characters/ninja/ninja_p1.png",
                "assets/characters/ninja/ninja_mad_p1.png"
            }, new Vector2D(0, 0), 128, 128, 3);
        }else{
            characterAsset = new Asset(new String[]{
                "assets/characters/ninja/ninja_p2.png",
                "assets/characters/ninja/ninja_mad_p2.png"
            }, new Vector2D(0, 0), 128, 128, 3);
        }
        
        characterAsset.setVisible(true);
        add(characterAsset);
        healthup = new Asset("assets/characters/ninja/heal.png", new Vector2D(0, 0), 3);
        healthup.setVisible(false);
        add(healthup);
        characterAsset.flipHorizontal();
        lastDirection = Direction.RIGHT;

        swordswing = new AudioPlayer("assets/SFX/knife_swing.wav");
        healthsfx = new AudioPlayer("assets/SFX/healthup.wav");
        teleport = new AudioPlayer("assets/SFX/teleport.wav");

        abilityIcon = new Asset("assets/characters/ninja/heal.png", new Vector2D(0, 0), 130, 130, 3);
        ultIcon = new Asset("assets/characters/ninha/dash.png", new Vector2D(0, 0), 130, 130, 3);
    }

    @Override
    public void updateCharacter(double deltaT, ArrayList<Integer> keyCodes){

        if (!NSword.canDamage && canReduceCD) {
            curUltCooldown-=1.5;
            if (curUltCooldown < 0)
                curUltCooldown = 0;
            canReduceCD = false;
        }

        if(direction != lastDirection){
            NSword.NinjNSword.flipHorizontal();
            lastDirection = direction;
        }

        if(status == Status.ATTACKING){
            NSword.setPos(new Vector2D(offset * direction.getValue() + temp * deltaT * direction.getValue(), 0));
            NSword.addPos(this.getPos());
            offset += temp * deltaT;
            if(offset >= 120){
                offset = 120;
                temp = -500;
            }

            if(offset <= 0){
                offset = 0;
                temp = 500;
                status = Status.IDLE;
                NSword.canDamage = false;
                canReduceCD = false;
            }
        }else{
            NSword.setPos(this.getPos());
        }

        if(ultuptime > 0){
            ultuptime -= deltaT;
        }else if(NSword.getDamage() >= 350){
            NSword.setDamage(250);
            characterAsset.setImageIndex(0);
        }
    }

    @Override
    protected void basicAttack(){
        if(status == Status.IDLE){
            swordswing.reset();
            swordswing.play();
            status = Status.ATTACKING;
            canReduceCD = true;
            NSword.canDamage = true;
        }
    }

    @Override
    protected void basicAbility(){
        healthsfx.reset();
        healthsfx.play();
        HP+=300;
        if (HP > maxHP) {
            HP = maxHP;
        }
        
    }

    @Override
    protected void ultimate(){
        teleport.reset();
        teleport.play();
        ultuptime = 4;
        NSword.setDamage(400);
        characterAsset.setImageIndex(1);

        Character opp = this.getPlayer() == 1 ? main.player2 : main.player1;
        if (lastDirection == Direction.RIGHT) {
            if (opp.getX() > this.getX() && this.getX() + 700 > opp.getX()) { // if dashes throught, but doesnt check height
                opp.HP -= 600;
            }
            setPos(getX()+700, getY());
        } else if(lastDirection == Direction.LEFT) {
            if (opp.getX() < this.getX() && this.getX() - 700 < opp.getX()) {
                opp.HP -= 600;
            }
            setPos(getX()-700, getY());
        }
    }

}
