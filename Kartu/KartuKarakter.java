class KartuKarakter extends Kartu {
    private String jenis;
    private int exp;
    private int level;
    private int health;
    private int attack;
    private int baseAttack;
    private int baseHealth;

    public KartuKarakter(String nama, String deskripsi, String tipe, String jenis, int exp, int level, int health,
            int attack, int baseAttack, int baseHealth) {
        super(nama, deskripsi, tipe);
        this.jenis = jenis;
        this.exp = exp;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.baseAttack = baseAttack;
        this.baseHealth = baseHealth;
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
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void attack(KartuKarakter kartu) {

    }

}
