/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.objects.vocabulary;

/**
 *
 * @author manhc
 */
public class Word implements Comparable<Word> {
    public String word;
    public double idf;
    public int id;
    public int count;
    public double tfidf;
    public double tf;

    @Override
    public int compareTo(Word o) {
        if (this.id < o.id){
            return -1;
        }else{
            return 1;
        }
    }
}
