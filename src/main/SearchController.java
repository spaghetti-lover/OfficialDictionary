package main;


import base.Word;import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import base.Trie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SearchController extends GeneralController implements Initializable {
    private ArrayList<Word> searchWordTemp = new ArrayList<>();
    private Trie trieVocab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguage();
        for (Word temp : getCurrentDic().getVocab()) {
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    public void setSearchListViewItem() {
        searchList.clear();
        if (searchField.getText().equals("")) {
            searchWordTemp.clear();
            searchWordTemp.addAll(getCurrentDic().getVocab());
        }
        for (Word temp : searchWordTemp) {
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    @FXML
    public void searchFieldAction() throws IOException {
        searchWordTemp.clear();
        searchList.clear();
        String word = searchField.getText();

        //System.out.println(getCurrentDic().getVocab().size());
        trieVocab = new Trie();
        trieVocab.addAll(getCurrentDic().getVocab());

        ArrayList<Word> wordsWithPrefix = trieVocab.getAllWordsWithPrefix(word);
        //  System.out.println(wordsWithPrefix.size());

        //  int index = getCurrentDic().binaryLookup(0, getCurrentDic().getVocab().size() - 1, word, getCurrentDic().getVocab());
        /*
        if (index < 0) {
            Spelling corrector = new Spelling("src/resource/vocab/spelling.txt");
            word = corrector.correct(word);
            index = getCurrentDic().binaryLookup(0, getCurrentDic().getVocab().size() -1, word, getCurrentDic().getVocab());
        }
         */
        // updateWordInListView(word,1, getCurrentDic().getVocab(), searchWordTemp);
        searchWordTemp  = wordsWithPrefix;
        setSearchListViewItem();
    }


    @FXML
    public void showDefinition() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        int index = Collections.binarySearch(getCurrentDic().getVocab(), new Word(spelling, null));
        String meaning = getCurrentDic().getVocab().get(index).getMeaning();
        definitionView.getEngine().loadContent(meaning, "text/html");
        if (Collections.binarySearch(getCurrentDic().getHistoryVocab(), new Word(spelling, null)) <= 0) {
            getCurrentDic().addWordToFile(spelling, meaning, getCurrentDic().getHISTORY_PATH());
        }
    }

    @FXML
    public void handleClickRemoveButton() {
        super.handleClickRemoveButton();
        searchList.clear();
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getVocab()) {
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    public void initSearchListView() {
        wordListView.getItems().clear();
        setSearchListViewItem();
        setLanguage();
    }
}