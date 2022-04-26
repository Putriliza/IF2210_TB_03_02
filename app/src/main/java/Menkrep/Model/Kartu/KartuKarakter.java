package Menkrep.Model.Kartu;

import java.util.List;
import java.util.ArrayList;

public class KartuKarakter extends Kartu {
    private String jenis; // OVERWORLD END NETHER
    private int exp;
    private int level;
    private int health;
    private int attack;
    private int attackUp;
    private int healthUp;
    private String imgPath;
    private ArrayList<KartuSpell> activeSpells;
    // private List<String> karakter;

    public KartuKarakter(String nama, String deskripsi, String jenis, int exp, int level, int health,
            int attack, int attackUp, int healthUp, String imgPath) {
        super(nama, deskripsi, "KARAKTER");
        this.jenis = jenis;
        this.exp = exp;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.imgPath = imgPath;
        this.activeSpells = new ArrayList<KartuSpell>();
        super.setImgPath(imgPath);
    }

    public KartuKarakter(List<String[]> reference, String nama) {
        super(nama, "", "KARAKTER");
        for (String[] karakter : reference) {
            if (karakter[1].equals(nama)) {
                this.setDeskripsi(karakter[3]);
                this.jenis = karakter[2];
                this.exp = 0;
                this.level = 1;
                this.attack = Integer.parseInt(karakter[5]);
                this.health = Integer.parseInt(karakter[6]);
                this.attackUp = Integer.parseInt(karakter[8]);
                this.healthUp = Integer.parseInt(karakter[8]);
                this.imgPath = karakter[7];
                this.activeSpells = new ArrayList<KartuSpell>();
            }
        }
    }

    // Jenis
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    // Exp
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    // Level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (this.health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    // Attack
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    // Attack Up
    public int getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(int attackUp) {
        this.attackUp = this.getAttack() + ((this.getLevel() - 1) * attackUp);
        // if (this.level == 10) {
        // this.attackUp = 0;
        // } else {
        // this.attackUp = attackUp;
        // }
    }

    // Health Up
    public int getHealthUp() {
        return healthUp;
    }

    public void setHealthUp(int healthUp) {
        this.healthUp = this.getHealth() + ((this.getLevel() - 1) * healthUp);
        // this.healthUp = healthUp;
    }

    // Img Path
    public void setImagePath(String path) {
        this.imgPath = path;
    }

    public String getImagePath(String path) {
        return this.imgPath;
    }

    // Active Spell
    public ArrayList<KartuSpell> getActiveSpells() {
        return activeSpells;
    }

    public void setActiveSpells(ArrayList<KartuSpell> activeSpells) {
        this.activeSpells = activeSpells;
    }

    // Naik Level
    public void naikLevel() {
        if (this.level > 0 && this.level <= 10) {
            while (this.exp >= 2 * this.level - 1) {
                this.exp -= 2 * this.level - 1;
                this.level += 1;
                this.health += this.healthUp;
                this.attack += this.attackUp;
            }
        }
    }

    // Menambahkan Exp
    public void naikExp(int n) {
        if (this.level > 0 && this.level <= 10) {
            this.exp += n;
            this.naikLevel();
        }
    }

    public void addSpell(KartuSpell spell) {
        this.activeSpells.add(spell);
    }

    public void removeSpell(KartuSpell spell) {
        this.activeSpells.remove(spell);
    }

    public void attack(KartuKarakter kartu) {
        if (this.getTipe().equals("OVERWORLD")) {
            if (kartu.getTipe().equals("END")) {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe().equals("NETHER")) {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        } else if (this.getTipe().equals("NETHER")) {
            if (kartu.getTipe().equals("OVERWORLD")) {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe().equals("END")) {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        } else if (this.getTipe().equals("END")) {
            if (kartu.getTipe().equals("NETHER")) {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe().equals("OVERWORLD")) {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        }
    }

}
