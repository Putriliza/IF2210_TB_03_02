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
    private int healthTemp;
    private int attackTemp;
    private String imgPath;
    private ArrayList<KartuSpell> activeSpells;
    private boolean doneAttack;

    private int swapDuration = -1;

    public KartuKarakter(String nama, String deskripsi, String jenis, int exp, int level, int health,
        int attack, int attackUp, int healthUp, String imgPath, int mana) {
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
        this.doneAttack=false;
        this.attackTemp = 0;
        this.healthTemp = 0;
        super.setImgPath(imgPath);
        super.setMana(mana);
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
                this.healthUp = Integer.parseInt(karakter[9]);
                this.imgPath = karakter[7];
                this.activeSpells = new ArrayList<KartuSpell>();
                this.doneAttack=false;
                super.setImgPath(karakter[4]);
                super.setMana(Integer.parseInt(karakter[7]));
            }
        }
    }

    public int getSwapDuration(){
        return swapDuration;
    }

    public void addSwapDuration(int newDur){
        if(newDur==0){
            swapDuration=0;
        } else{
            if(swapDuration==-1){
                swapDuration=0;
            }
            swapDuration += newDur;
        }
    }

    public void reduceSwapDuration(){
        if(swapDuration!=0){
            if(swapDuration>1){
                swapDuration -= 1;
            } else{
                swapDuration=-1;
            }
        }
    }

    public void resetAttack(){
        this.doneAttack=false;
    }
    public void alrAttack(){
        this.doneAttack=true;
    }
    public boolean getDoneAttack(){
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

    public int getMaxExp(){return (level*2)-1;}

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
        return attack + this.attackTemp;
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
        return healthUp + this.healthTemp;
    }

    public void setHealthUp(int healthUp) {
        this.healthUp = this.getHealth() + ((this.getLevel() - 1) * healthUp);
        // this.healthUp = healthUp;
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
            } else if (kartu.getJenis().equals("NETHER")) {
                newHealth -= this.getAttack() / 2;
            } else {
                newHealth -= this.getAttack();
            }
        } else if (this.getJenis().equals("NETHER")) {
            if (kartu.getJenis().equals("OVERWORLD")) {
                newHealth -= this.getAttack() * 2;
            } else if (kartu.getJenis().equals("END")) {
                newHealth -= this.getAttack() / 2;
            } else {
                newHealth -= this.getAttack();
            }
        } else if (this.getJenis().equals("END")) {
            if (kartu.getJenis().equals("NETHER")) {
                newHealth -= this.getAttack() * 2;
            } else if (kartu.getJenis().equals("OVERWORLD")) {
                newHealth -= this.getAttack() / 2;
            } else {
                newHealth -= this.getAttack();
            }
        }

        if(newHealth<0){
            newHealth = 0;
        }
        kartu.setHealth(newHealth);
    }

    public int getAttackTemp() {
        return this.attackTemp;
    }

    public int getHealthTemp() {
        return this.healthTemp;
    }

    public void setAttackTemp(int attack) {
        this.attackTemp = attack;
    }

    public void setHealthTemp(int health) {
        this.healthTemp = health;
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

}
