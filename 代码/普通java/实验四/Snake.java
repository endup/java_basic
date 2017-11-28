
import java.awt.*;
import java.applet.*;

public class Snake extends Applet implements Runnable {
    Image dot[] = new Image[400];

    Image back;

    Image offI;

    Graphics offG;

    int x[] = new int[400];

    int y[] = new int[400];

    int rtemp = 1;

    int game = 1;

    int level;

    int z;

    int n;

    int count = 0;

    int score = 0;

    int add = 1;

    Button level1 = new Button("初级");

    Button level2 = new Button("中级");

    Button level3 = new Button("高级");

    Button level4 = new Button("专家级");

    String stemp;

    String s;

    String t;

    boolean go[] = new boolean[400];

    boolean left = false;

    boolean right = false;

    boolean up = false;

    boolean down = false;

    boolean started = false;

    //boolean me=false;

    Thread setTime;

    public void init() {
        add(level1);
        add(level2);
        add(level3);
        add(level4);
        setBackground(Color.black);
        back = getImage(getCodeBase(), "screan.gif");
        for (z = 0; z < 400; z++) {
            dot[z] = getImage(getCodeBase(), "dot.gif");
        }
    }

    public void update(Graphics g) {
        Dimension d = this.size();
        if (offI == null) {
            offI = createImage(d.width, d.height);
            offG = offI.getGraphics();
        }
        offG.clearRect(0, 0, d.width, d.height);
        paint(offG);
        g.drawImage(offI, 0, 0, null);
    }

    public void paint(Graphics g) {
        g.drawImage(back, 0, 0, this);
        g.setColor(Color.white);

        if (started) {
            g.setFont(new Font("TimesRoman", 1, 12));
            t = "分数 " + score + "";
            g.drawString(t, 75, 220);
        }

        if (game == 1) {
            g.setFont(new Font("TimesRoman", 1, 13));
            s = "游戏模式选择";
            g.drawString(s, 65, 30);

            level1.move(75, 50);
            level2.move(75, 90);
            level3.move(75, 130);
            level4.move(70, 170);
        }

        if ((game == 2) || (game == 3)) {
            if (!started) {
                g.setFont(new Font("TimesRoman", 1, 13));
                t = "用方向键移动!";
                g.drawString(t, 5, 215);
            }
            for (z = 0; z <= n; z++) {
                g.drawImage(dot[z], x[z], y[z], this);
            }
        }

        if (game == 3) {
            g.setFont(new Font("TimesRoman", 1, 13));
            s = "游戏结束！";
            g.drawString(s, 65, 60);
        }

    }

    public void run() {
        for (z = 4; z < 400; z++) {
            go[z] = false;
        }
        for (z = 0; z < 4; z++) {
            go[z] = true;
            x[z] = 91;
            y[z] = 91;
        }
        n = 3;
        game = 2;
        score = 0;
        level1.move(70, -100);
        level2.move(70, -100);
        level3.move(70, -100);
        level4.move(70, -100);
        left = false;
        right = false;
        up = false;
        down = false;
        locateRandom(4);

        while (true) {
            if (game == 2) {
                if ((x[0] == x[n]) && (y[0] == y[n])) {
                    go[n] = true;
                    locateRandom((n + 1));
                    score += add;
                }
                for (z = 399; z > 0; z--) {
                    if (go[z]) {
                        x[z] = x[(z - 1)];
                        y[z] = y[(z - 1)];
                        if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                            game = 3;
                        }
                    }
                }
                if (left) {
                    x[0] -= 10;
                }
                if (right) {
                    x[0] += 10;
                }
                if (up) {
                    y[0] -= 10;
                }
                if (down) {
                    y[0] += 10;
                }
            }

            if (y[0] > 191) {
                y[0] = 191;
                game = 3;
            }
            if (y[0] < 1) {
                y[0] = 1;
                game = 3;
            }
            if (x[0] > 191) {
                x[0] = 191;
                game = 3;
            }
            if (x[0] < 1) {
                x[0] = 1;
                game = 3;
            }

            if (game == 3) {
                if (count < (1500 / level)) {
                    count++;
                } else {
                    count = 0;
                    game = 1;
                    repaint();
                    setTime.stop();
                }
            }

            repaint();
            try {
                setTime.sleep(level);
            } catch (InterruptedException e) {
            }
        }
    }

    public void locateRandom(int turn) {
        rtemp = (int) (Math.random() * 20);
        x[turn] = ((rtemp * 10) + 1);
        rtemp = (int) (Math.random() * 20);
        y[turn] = ((rtemp * 10) + 1);
        n++;
    }

    public boolean keyDown(Event e, int key) {
        if ((key == Event.LEFT) && (!right)) {
            left = true;
            up = false;
            down = false;
            if (!started)
                started = true;
        }
        if ((key == Event.RIGHT) && (!left)) {
            right = true;
            up = false;
            down = false;
            if (!started)
                started = true;
        }
        if ((key == Event.UP) && (!down)) {
            up = true;
            right = false;
            left = false;
            if (!started)
                started = true;
        }
        if ((key == Event.DOWN) && (!up)) {
            down = true;
            right = false;
            left = false;
            if (!started)
                started = true;
        }
        return true;
    }

    public boolean action(Event event, Object obj) {
        stemp = (String) obj;

        if (stemp.equals("初级")) {
            add = 2;
            level = 100;
            setTime = new Thread(this);
            setTime.start();
            return true;
        }

        if (stemp.equals("中级")) {
            add = 5;
            level = 70;
            setTime = new Thread(this);
            setTime.start();
            return true;
        }

        if (stemp.equals("高级")) {
            add = 10;
            level = 40;
            setTime = new Thread(this);
            setTime.start();
            return true;
        }

        if (stemp.equals("专家级")) {
            add = 20;
            level = 20;
            setTime = new Thread(this);
            setTime.start();
            return true;
        }

        return false;
    }

}
