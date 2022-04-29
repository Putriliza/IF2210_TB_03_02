package Menkrep.Model.Kartu;

import org.checkerframework.checker.units.qual.A;

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
    private ArrayList<KartuSpell> activeSpells;
    private boolean doneAttack;

    private ArrayList<Integer> healthTemp;
    private ArrayList<Integer> attackTemp;
    private ArrayList<Integer> duration;
    private int swapDuration;

    public KartuKarakter(String nama, String deskripsi, String jenis, int exp, int level, int health,
            int attack, int attackUp, int healthUp, String imgPath, int mana) {
        super(nama, deskripsi, "KARAKTER", mana, imgPath);
        this.jenis = jenis;
        this.exp = exp;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.activeSpells = new ArrayList<KartuSpell>();
        this.healthTemp = new ArrayList<>();
        this.attackTemp = new ArrayList<>();
        this.duration = new ArrayList<>();
        this.doneAttack = false;
        this.doneAttack = false;
        this.swapDuration = 0;
        super.setImgPath(imgPath);
        super.setMana(mana);
    }

    public KartuKarakter(List<String[]> reference, String nama) {
        super(nama, "", "KARAKTER", 0, "-");
        for (String[] karakter : reference) {
            if (karakter[1].equals(nama)) {
                this.setDeskripsi(karakter[3]);
                this.jenis = karakter[2];
                this.exp = 0;
                this.level = 1;
                this.attack = Integer.parseInt(karakter[5]);
                this.health = Integer.parseInt(karakter[6]);
                this.attackUp = Integer.parseInt(karakter[8]);
                this.healthUp = Integer.parseInt(karakter[9]);
                this.activeSpells = new ArrayList<KartuSpell>();
                this.healthTemp = new ArrayList<>();
                this.attackTemp = new ArrayList<>();
                this.duration = new ArrayList<>();
                this.doneAttack = false;
                super.setImgPath(karakter[4]);
                super.setMana(Integer.parseInt(karakter[7]));
            }
        }
    }

    private void reduceHealthTemp(int damage) {
        if (healthTemp.size() > 0) {
            int sisa = damage;
            for (int i = healthTemp.size() - 1; i >= 0; i--) {
                if (sisa > 0 && duration.get(i) > 0) {
                    if (healthTemp.get(i) >= sisa) {
                        healthTemp.set(i, healthTemp.get(i) - sisa);
                        sisa = 0;
                    } else {
                        sisa -= healthTemp.get(i);
                        healthTemp.set(i, 0);
                    }
                }
            }
        }

    }

    public boolean reduceDuration() {
        if(duration==null || duration.size()==0){
            return false;
        }
        for (int i = duration.size() - 1; i >= 0; i--) {
            if (duration.get(i) > 1) {
                duration.set(i, duration.get(i) - 1);
            } else if (duration.get(i) == 1) {
                duration.set(i, 0);
                health -= healthTemp.get(i);
                attack -= attackTemp.get(i);
                if(health<=0){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> getHealthTemp() {
        return healthTemp;
    }

    public ArrayList<Integer> getAttackTemp() {
        return attackTemp;
    }

    public ArrayList<Integer> getDuration() {
        return duration;
    }

    public KartuKarakter(KartuKarakter other) {
        super(other);
        this.jenis = other.getJenis();
        this.exp = other.getExp();
        this.level = other.getLevel();
        this.health = other.getHealth();
        this.attack = other.getAttack();
        this.attackUp = other.getAttackUp();
        this.healthUp = other.getHealthUp();
        this.activeSpells = other.getActiveSpells();
        this.doneAttack = other.getDoneAttack();
        this.attackTemp = other.getAttackTemp();
        this.healthTemp = other.getHealthTemp();
        this.swapDuration = other.getSwapDuration();
        super.setImgPath(other.getImgPath());
        super.setMana(other.getMana());
    }

    public int getSwapDuration() {
        return swapDuration;
    }

    public void addSwapDuration(int newDur) {
        swapDuration += newDur;
    }

    public boolean reduceSwapDuration() {
        if (swapDuration > 0) {
            swapDuration -= 1;
            if(swapDuration==0){
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public void resetAttack() {
        this.doneAttack = false;
    }

    public void alrAttack() {
        this.doneAttack = true;
    }

    public boolean getDoneAttack() {
        return this.doneAttack;
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

    public int getMaxExp() {
        return (level * 2) - 1;
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
        // this.attackUp = this.getAttack() + ((this.getLevel() - 1) * attackUp);
        this.attackUp = attackUp;
    }

    // Health Up
    public int getHealthUp() {
        return healthUp;
    }

    public void setHealthUp(int healthUp) {
        // this.healthUp = this.getHealth() + ((this.getLevel() - 1) * healthUp);
        this.healthUp = healthUp;
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
            while (this.exp >= (2 * this.level) - 1) {
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
        int newHealth = kartu.getHealth();
        if (this.getJenis().equals("OVERWORLD")) {
            if (kartu.getJenis().equals("END")) {
                newHealth -= this.getAttack() * 2;
                kartu.reduceHealthTemp(this.getAttack() * 2);
            } else if (kartu.getJenis().equals("NETHER")) {
                newHealth -= this.getAttack() / 2;
                kartu.reduceHealthTemp(this.getAttack() / 2);
            } else {
                newHealth -= this.getAttack();
                kartu.reduceHealthTemp(this.getAttack());
            }
        } else if (this.getJenis().equals("NETHER")) {
            if (kartu.getJenis().equals("OVERWORLD")) {
                newHealth -= this.getAttack() * 2;
                kartu.reduceHealthTemp(this.getAttack() * 2);
            } else if (kartu.getJenis().equals("END")) {
                newHealth -= this.getAttack() / 2;
                kartu.reduceHealthTemp(this.getAttack() / 2);
            } else {
                newHealth -= this.getAttack();
                kartu.reduceHealthTemp(this.getAttack());
            }
        } else if (this.getJenis().equals("END")) {
            if (kartu.getJenis().equals("NETHER")) {
                newHealth -= this.getAttack() * 2;
                kartu.reduceHealthTemp(this.getAttack() * 2);
            } else if (kartu.getJenis().equals("OVERWORLD")) {
                newHealth -= this.getAttack() / 2;
                kartu.reduceHealthTemp(this.getAttack() / 2);
            } else {
                newHealth -= this.getAttack();
                kartu.reduceHealthTemp(this.getAttack());
            }
        }

        if (newHealth < 0) {
            newHealth = 0;
        }
        kartu.setHealth(newHealth);
    }

    @Override
    public String toString() {
        return "Type=" + jenis +
                "\nExp=" + exp +
                "\nLevel=" + level +
                "\nHealth=" + health +
                "\nAttack=" + attack +
                "\nAttackUp=" + attackUp +
                "\nHealthUp=" + healthUp;
    }

    @Override
    public String getDisplayString() {
        return String.format("ATK %d/HP %d", this.attack, this.health);
    }

}
