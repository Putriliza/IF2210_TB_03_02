package Menkrep.Model.Reference;

import Menkrep.Model.Kartu.*;
import Menkrep.Util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reference {
    private static Reference refInstance;

    private static final String CHARACTER_CSV_FILE_PATH = "data/character.csv";
    private static final String MORPH_CSV_FILE_PATH = "data/spell_morph.csv";
    private static final String PTN_CSV_FILE_PATH = "data/spell_ptn.csv";
    private static final String SWAP_CSV_FILE_PATH = "data/spell_swap.csv";
    private static final String LVL_CSV_FILE_PATH = "data/spell_lvl.csv";
    private final List<String[]> karakter;
    private final List<String[]> morph;
    private final List<String[]> ptn;
    private final List<String[]> swap;
    private final List<String[]> lvl;
    private final ArrayList<Kartu> referenceDeck;

    public static Reference getInstance() {
        if (refInstance == null) {
            try {
                refInstance = new Reference();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return refInstance;
    }

    public Reference() throws IOException {
        String cwd = System.getProperty("user.dir");
        String cardDirectory = "\\src\\main\\resources\\Menkrep\\card\\";

        File characterCSVFile = new File(cwd + cardDirectory + CHARACTER_CSV_FILE_PATH);
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        this.karakter = characterReader.read();

        File morphCSVFile = new File(cwd + cardDirectory + MORPH_CSV_FILE_PATH);
        CSVReader morphReader = new CSVReader(morphCSVFile, "\t");
        morphReader.setSkipHeader(true);
        this.morph = morphReader.read();

        File ptnCSVFile = new File(cwd + cardDirectory + PTN_CSV_FILE_PATH);
        CSVReader ptnReader = new CSVReader(ptnCSVFile, "\t");
        ptnReader.setSkipHeader(true);
        this.ptn = ptnReader.read();

        File swapCSVFile = new File(cwd + cardDirectory + SWAP_CSV_FILE_PATH);
        CSVReader swapReader = new CSVReader(swapCSVFile, "\t");
        swapReader.setSkipHeader(true);
        this.swap = swapReader.read();

        File lvlCSVFile = new File(cwd + cardDirectory + LVL_CSV_FILE_PATH);
        CSVReader lvlReader = new CSVReader(lvlCSVFile, "\t");
        lvlReader.setSkipHeader(true);
        this.lvl = lvlReader.read();

        this.referenceDeck = new ArrayList<>();
        for (String[] karakter : karakter) {
            this.referenceDeck.add(new KartuKarakter(getKarakter(), karakter[1]));
        }
        for (String[] morph : morph) {
            this.referenceDeck.add(new KartuSpellMorph(morph[1], morph[2], Integer.parseInt(morph[5]), morph[3], Integer.parseInt(morph[4])));
        }
        for (String[] potion : ptn) {
            this.referenceDeck.add(new KartuSpellPotion(getPtn(), potion[1]));
        }
        for (String[] swap : swap) {
            this.referenceDeck.add(new KartuSpellSwap(getSwap(), swap[1]));
        }
        for (String[] lvl : lvl) {
            this.referenceDeck.add(new KartuSpellLvl(getLvl(), lvl[1]));
        }
    }

    public List<String[]> getKarakter() {
        return karakter;
    }

    public List<String[]> getMorph() {
        return morph;
    }

    public List<String[]> getPtn() {
        return ptn;
    }

    public List<String[]> getSwap() {
        return swap;
    }

    public List<String[]> getLvl() {
        return lvl;
    }

    public ArrayList<Kartu> getReferenceDeck() {
        return referenceDeck;
    }
}
