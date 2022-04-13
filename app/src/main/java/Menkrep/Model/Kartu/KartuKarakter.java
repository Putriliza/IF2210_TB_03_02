package Menkrep.Model.Kartu;

public class KartuKarakter extends Kartu {
    private String jenis;
    private int exp;
    private int level;
    private int health;
    private int attack;
    private int attackUp;
    private int healthUp;

    public KartuKarakter(String nama, String deskripsi, String tipe, String jenis, int exp, int level, int health,
            int attack, int attackUp, int healthUp) {
        super(nama, deskripsi, tipe);
        this.jenis = jenis;
        this.exp = exp;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
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

    public void naikLevel() {
        if (this.level > 0 && this.level <= 10) {
            this.level += 1;
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

    public void attack(KartuKarakter kartu) {
        if (this.getTipe() == "Overworld") {
            if (kartu.getTipe() == "End") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe() == "Nether") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        } else if (this.getTipe() == "Nether") {
            if (kartu.getTipe() == "Overworld") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe() == "End") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        } else if (this.getTipe() == "End") {
            if (kartu.getTipe() == "Nether") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() * 2);
            } else if (kartu.getTipe() == "Overworld") {
                kartu.setHealth(kartu.getHealth() - this.getAttack() / 2);
            } else {
                kartu.setHealth(kartu.getHealth() - this.getAttack());
            }
        }
    }

}
