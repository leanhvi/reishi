/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.word2vector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import reishi.dataobjects.CrawlerDataObj;
import reishi.mysql.CrawlerData;
import reishi.objects.vocabulary.Vocabulary;
import reishi.objects.vocabulary.Word;

/**
 *
 * @author manhc
 */
public class Word2Vector {
    public static Vocabulary vocabulary = new Vocabulary();
    
    public void createVocabulary() throws ClassNotFoundException, SQLException {
        CrawlerData crawlerData = new CrawlerData();
        vocabulary.vocabulary = crawlerData.wordsGetAll();
        for (Word w : vocabulary.vocabulary) {
            vocabulary.wordsList.add(w.word);
        }
    }
    
    public void initVectorToDb() throws ClassNotFoundException, SQLException {
        CrawlerData crawlerData = new CrawlerData();
        for(int i = 0; i <= 21625; ++i) {
            CrawlerDataObj doc = crawlerData.crawlerDataGetById(i);
            
            if(doc.id > 0) {
                List<Word> words = new ArrayList<>();
                String[] wordsArray = doc.wordSegmented.trim().split(" ");
                for(int j = 0; j < wordsArray.length; ++j) {
                    if(wordsArray[j].trim().length() > 0) {                        
                        int p = containInList(words, wordsArray[j]);
                        if(p == -1) {
                            Word w = new Word();
                            w.count = 1;
                            w.word = wordsArray[j];
                            words.add(w);
                        }
                        else {
                            words.get(p).count++;
                        }
                    }
                }
                
                int maxTF = getMaxTF(words);
                
                for(int j = 0; j < words.size(); ++j) {
                    for(int k = 0; k < vocabulary.vocabulary.size(); ++k) {
                        if(words.get(j).word.equalsIgnoreCase(vocabulary.vocabulary.get(k).word)) {
                            //words.get(j).tf = (double)words.get(j).count / (double)maxTF;
                            words.get(j).tfidf = round((double)words.get(j).count / (double)maxTF * vocabulary.vocabulary.get(k).idf, 4);
                            words.get(j).id = vocabulary.vocabulary.get(k).id;
                            break;
                        }
                    }
                }
                
                Collections.sort(words);
                String vector = "";
                for(int j = 0; j < words.size(); ++j) {
                    if(words.get(j).id > 0) {
                        if(j < words.size() - 1) {
                            vector += words.get(j).id + ":" + words.get(j).tfidf + " ";
                        }
                        else {
                            vector += words.get(j).id + ":" + words.get(j).tfidf;
                        }
                    }
                }
                
                crawlerData.crawlerDataUpdateVector(i, vector);
                System.out.println("Id " + i + " done");
            }
        }
    }

    private int containInList(List<Word> words, String word) {
        if(words == null || words.isEmpty()) {
            return -1;
        }
        for(int i = 0; i < words.size(); ++i) {
            if(words.get(i).word.equalsIgnoreCase(word)) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxTF(List<Word> words) {
        int max = 0;
        for(int i = 0; i < words.size(); ++i) {
            if(words.get(i).count > max) {
                max = words.get(i).count;
            }
        }
        return max;
    }
    
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
