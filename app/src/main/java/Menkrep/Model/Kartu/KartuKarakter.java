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
    private ArrayList<KartuSpell> spell;
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
            }
        }
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(int attackUp) {
        this.attackUp = attackUp;
    }

    public int getHealthUp() {
        return healthUp;
    }

    public void setHealthUp(int healthUp) {
        this.healthUp = healthUp;
    }

    public void setImagePath(String path) {
        this.imgPath = path;
    }

    public String getImagePath(String path) {
        return this.imgPath;
    }

    public void naikLevel() {
        if (this.level > 0 && this.level <= 10) {
            this.level += 1;
            this.exp = 0;
            this.health += this.healthUp;
            this.attack += this.attackUp;
        }
    }

    public void naikExp() {
        if (this.level > 0 && this.level <= 10) {
            this.exp += 2;
            this.naikLevel();
        }
    }

    public void addSpell(KartuSpell spell) {
        this.spell.add(spell);
    }

    public void attack(KartuKarakter kartu) {
        if (this.getTipe() == "OVERWORLD") {
            if (kartu.getTipe() == "END") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe() == "NETHER") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        } else if (this.getTipe() == "NETHER") {
            if (kartu.getTipe() == "OVERWORLD") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe() == "END") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        } else if (this.getTipe() == "END") {
            if (kartu.getTipe() == "NETHER") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe() == "OVERWORLD") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        }
    }

}
