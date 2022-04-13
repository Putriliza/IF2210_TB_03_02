package Menkrep.Model.Reference;

import Menkrep.Util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Reference {
    private static final String CHARACTER_CSV_FILE_PATH = "data/character.csv";
    private static final String MORPH_CSV_FILE_PATH = "data/spell_morph.csv";
    private static final String PTN_CSV_FILE_PATH = "data/spell_ptn.csv";
    private static final String SWAP_CSV_FILE_PATH = "data/spell_swap.csv";
    private final List<String[]> karakter;
    private final List<String[]> morph;
    private final List<String[]> ptn;
    private final List<String[]> swap;

    public Reference() throws IOException {
        String cwd = System.getProperty("user.dir");
        String cardDirectory = "\\app\\src\\main\\resources\\Menkrep\\card\\";

        File characterCSVFile = new File(cwd + cardDirectory + CHARACTER_CSV_FILE_PATH);
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        this.karakter = characterReader.read();

        File morphCSVFile = new File(cwd + cardDirectory + MORPH_CSV_FILE_PATH);
        CSVReader morphReader = new CSVReader(morphCSVFile, "\t");
        morphReader.setSkipHeader(true);
        this.morph = morphReader.read();
//
        File ptnCSVFile = new File(cwd + cardDirectory + PTN_CSV_FILE_PATH);
        CSVReader ptnReader = new CSVReader(ptnCSVFile, "\t");
        ptnReader.setSkipHeader(true);
        this.ptn = ptnReader.read();

        File swapCSVFile = new File(cwd + cardDirectory + SWAP_CSV_FILE_PATH);
        CSVReader swapReader = new CSVReader(swapCSVFile, "\t");
        swapReader.setSkipHeader(true);
        this.swap = swapReader.read();
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
}
