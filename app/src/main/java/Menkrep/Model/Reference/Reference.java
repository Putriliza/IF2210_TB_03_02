package Menkrep.Model.Reference;

import Menkrep.Util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Reference {
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String MORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
    private static final String PTN_CSV_FILE_PATH = "card/data/spell_ptn.csv";
    private static final String SWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";
    private final List<String[]> karakter;
    private final List<String[]> morph;
    private final List<String[]> ptn;
    private final List<String[]> swap;

    public Reference() throws IOException, URISyntaxException {
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        this.karakter = characterReader.read();

        File morphCSVFile = new File(getClass().getResource(MORPH_CSV_FILE_PATH).toURI());
        CSVReader morphReader = new CSVReader(morphCSVFile, "\t");
        morphReader.setSkipHeader(true);
        this.morph = morphReader.read();

        File ptnCSVFile = new File(getClass().getResource(PTN_CSV_FILE_PATH).toURI());
        CSVReader ptnReader = new CSVReader(ptnCSVFile, "\t");
        ptnReader.setSkipHeader(true);
        this.ptn = ptnReader.read();

        File swapCSVFile = new File(getClass().getResource(SWAP_CSV_FILE_PATH).toURI());
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
