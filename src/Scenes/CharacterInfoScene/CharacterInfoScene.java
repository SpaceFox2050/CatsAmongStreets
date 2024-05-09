import ky.Vector2D;
import ky.Text;
import java.awt.Color;
import java.awt.Font;

import ky.Asset;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class CharacterInfoScene extends Scene{
    
    private Text[] stab = new Text[12];
    private Text[] robot = new Text[12];
    private Text[] tank = new Text[12];
    private Text[] ninja = new Text[12];

    Asset logostab;
    Asset logorobot;
    Asset logotank;
    Asset logoninja;
    
    public CharacterInfoScene (Main main){
        super(main);
    }
    @Override
    public void initialize(){

        logorobot = new Asset("assets/characters/robot/robot.png",
                new Vector2D(width * 0.5 - 150, height * 0.9), 0);
        logostab = new Asset("assets/characters/stab/icon.png", new Vector2D(width * 0.5 - 500, height * 0.9),
                110, 30, 0);
        logotank = new Asset("assets/characters/tank/tank.png", new Vector2D(width * 0.5 + 150, height * 0.9),
                110, 110, 0);
        logoninja = new Asset("assets/characters/ninja/ninja.png", new Vector2D(width * 0.5 + 500, height * 0.9), 
                110, 110, 0);

        logorobot.setVisible(true);
        logostab.setVisible(true);
        logotank.setVisible(true);
        logoninja.setVisible(true);
        add(logorobot);
        add(logostab);
        add(logotank);
        add(logoninja);

        //initialize all text
        for(int x = 0; x < 12; x++){
            stab[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 - 500, 100 + x * 50),
            300,
            100,
            5);
            stab[x].setVisible(true);
            add(stab[x]);

            robot[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 - 150, 100 + x * 50),
            300,
            100,
            5);
            robot[x].setVisible(true);
            add(robot[x]);

            tank[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 + 150, 100 + x * 50),
            300,
            100,
            5);
            tank[x].setVisible(true);
            add(tank[x]);

            ninja[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 + 500, 100 + x * 50),
            300,
            100,
            5);
            ninja[x].setVisible(true);
            add(ninja[x]);
        }

        stab[0].setText("Health: 1700");
        stab[1].setText("Speed: 100 000");
        stab[2].setText("Jump: 1200");
        stab[3].setText("Hitbox: 150x275");
        stab[4].setText("Defense: 0.25");
        stab[5].setText("Damage: 175 (melee)");
        stab[6].setText("Ablt: dmg = 275");
        stab[7].setText("Ablt cd: 5 secs");
        stab[8].setText("Ablt dur: 2 secs");
        stab[9].setText("Ult: dmg = 400");
        stab[10].setText("Ult cd: 20 secs");
        stab[11].setText("Ult dur: 5 secs");

        robot[0].setText("Health: 1200");
        robot[1].setText("Speed: 100 000");
        robot[2].setText("Jump: 600");
        robot[3].setText("Hitbox: 150x200");
        robot[4].setText("Defense: 0.1");
        robot[5].setText("Damage: 150 (range; pierce)");
        robot[6].setText("Ablt: 500dmg rocket");
        robot[7].setText("Ablt cd: 6 secs");
        robot[9].setText("Ult: 4x250dmg homing rockets");
        robot[10].setText("Ult cd: 15 secs");

        tank[0].setText("Health: 1800");
        tank[1].setText("Speed: 40 000");
        tank[2].setText("Jump: 500");
        tank[3].setText("Hitbox: 256x256");
        tank[4].setText("Defense: 0.4");
        tank[5].setText("Damage: 250dmg/sec (saw)");
        tank[6].setText("Ablt: 450dmg/sec (flame)");
        tank[7].setText("Ablt cd: 4 secs");
        tank[8].setText("Ablt dur: 2.5 secs");
        tank[9].setText("Ult: defense = 0.8");
        tank[10].setText("Ult cd: 8 secs");
        tank[11].setText("Ult dur: 1.5 secs");

        ninja[0].setText("Health: 1000");
        ninja[1].setText("Speed: 150 000");
        ninja[2].setText("Jump: 800");
        ninja[3].setText("Hitbox: 150x200");
        ninja[4].setText("Defenese: 0");
        ninja[5].setText("Damage: 200 (melee)");
        ninja[6].setText("Ablt: gain 300 hp");
        ninja[7].setText("Ablt cd: 8 secs");
        ninja[9].setText("Ult: dash 700+dmg = 400");
        ninja[10].setText("Ult cd: 16 secs");
        ninja[11].setText("Ult dur: 4 secs");

        
    }   

    public void update(double deltaT, ArrayList<Integer> keyCodes){
        if(keyCodes.contains(KeyEvent.VK_ESCAPE)){
            main.setScene(1);
        }
    }


}
