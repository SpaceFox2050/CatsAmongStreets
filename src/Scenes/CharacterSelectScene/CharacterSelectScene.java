import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class CharacterSelectScene extends Scene {

    SelectionCursor p1Cursor;
    SelectionCursor p2Cursor;

    Asset robot;
    Asset stab;
    Asset tank;
    Asset ninja;

    public CharacterSelectScene(Main main) {
        super(main);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (p1Cursor.selected && p2Cursor.selected) {
            main.setScene(3);
        }

        if (keyCodes.contains(KeyEvent.VK_ESCAPE)) {
            main.setScene(1);
        }
    }

    @Override
    public void initialize() {
        p1Cursor = new SelectionCursor("assets/misc/p1cursor.png", new Vector2D(width * 0.5 - 110, height * 0.75), 4, 1,
                main);
        p2Cursor = new SelectionCursor("assets/misc/p2cursor.png", new Vector2D(width * 0.5 + 110, height * 0.75), 4, 2,
                main);
        robot = new Asset("assets/characters/robot/robot.png",
                new Vector2D(width * 0.5 - 330, height * 0.75), 0);
        stab = new Asset("assets/characters/stab/stab.png", new Vector2D(width * 0.5 - 110, height * 0.75),
                110, 30, 0);
        p1Cursor.setVisible(true);
        tank = new Asset("assets/characters/tank/tank.png", new Vector2D(width * 0.5 + 110, height * 0.75),
                110, 110, 0);
        ninja = new Asset("assets/characters/ninja/ninja.png", new Vector2D(width * 0.5 + 330, height * 0.75),
                110, 110, 0);
        p2Cursor.setVisible(true);
        robot.setVisible(true);
        stab.setVisible(true);
        tank.setVisible(true);
        ninja.setVisible(true);
        add(p1Cursor);
        add(p2Cursor);
        add(robot);
        add(stab);
        add(tank);
        add(ninja);

    }

}