package Menkrep.Model.Kartu;

import java.util.List;

public class KartuSpellPotion extends KartuSpell implements KartuSpellTemp{
    private int duration;
    private int attackModifier;
    private int healthModifier;

    public KartuSpellPotion() {
        super("-", "-", "POTION", 0, "-");
        this.duration = 0;
        this.attackModifier = 0;
        this.healthModifier = 0;
    }

    public KartuSpellPotion(String nama, String deskripsi, int mana, int duration, int attackModifier, int healthModifier, String imgPath) {
        super(nama, deskripsi, "POTION", mana, imgPath);
        this.duration = duration;
        this.attackModifier = attackModifier;
        this.healthModifier = healthModifier;
    }

    public KartuSpellPotion(List<String[]> reference, String nama) {
        super(nama, "-", "POTION", 0, "-");
        for (String[] ptn: reference) {
            if (ptn[1].equals(nama)) {
                this.setDeskripsi(ptn[2]);
                this.setMana(Integer.parseInt(ptn[6]));
                this.duration = Integer.parseInt(ptn[7]);
                this.attackModifier = Integer.parseInt(ptn[4]);
                this.healthModifier = Integer.parseInt(ptn[5]);
                this.setImgPath(ptn[3]);
            }
        }
    }

    public KartuSpellPotion(KartuSpellPotion other) {
        super(other);
        this.duration = other.getDuration();
        this.attackModifier = other.getAttackModifier();
        this.healthModifier = other.getHealthModifier();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void reduceDuration() {
        this.duration -= 1;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(int healthModifier) {
        this.healthModifier = healthModifier;
    }

    /* Meningkatkan atribut attack dan / atau health.
    Penambahan ini dapat bernilai 0 bahkan negatif.
    Penambahan health dilakukan secara bertumpuk, ????
    sehingga serangan musuh akan mengambil health dari efek potion terakhir terlebih dahulu
    sebelum health dari karakter atau efek potion sebelumnya.
    Pengurangan health dapat membunuh karakter.
    Pengurangan attack tidak akan membuat attack karakter kurang dari 0. */

    public void potion(KartuKarakter karakter) {
        int newAttack = karakter.getAttack() + this.attackModifier;
        int newHealth = karakter.getHealth() + this.healthModifier;
        if (newAttack < 0) {
            newAttack = 0;
        }
        karakter.setAttack(newAttack);
        karakter.setHealth(newHealth);

        // if duration is 0, potion hilang
        // if karakter.getHealth() = 0, karakter mati
    }

    @Override
    public String toString() {
        return "Mana = " + getMana() +
                "\nDuration=" + duration +
                "\nAtk=" + attackModifier +
                "\nHp=" + healthModifier;
    }

    @Override
    public String getDisplayString() {
        return String.format("ATK%+d/HP%+d", getAttackModifier(), getHealthModifier());
    }
}