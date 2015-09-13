package reshi.wordsegmenter;

import jvnsegmenter.CRFSegmenter;
import reishi.config.ReishiConfig;

public class WordSegmenter {
    private static final String regex = "[^zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOPaAeEoOuUiIdDyYáàạảãâấầậẩẫăắằặẳẵÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴéèẹẻẽêếềệểễÉÈẸẺẼÊẾỀỆỂỄóòọỏõôốồộổỗơớờợởỡÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠúùụủũưứừựửữÚÙỤỦŨƯỨỪỰỬỮíìịỉĩÍÌỊỈĨđĐýỳỵỷỹÝỲỴỶỸ0123456789.,]";
    private static final CRFSegmenter segmenter = new CRFSegmenter(ReishiConfig.wordSegmentModelsDir);
    public static String segmenting(String input) {
        input = StandardString(input);
        return segmenter.segmenting(input).toLowerCase();			
    }
    
    private static String StandardString(String input) {
        String output = input.replaceAll(regex, " ");
        String regex2 = "[,.]";
        output = output.replaceAll(regex2, " . ").replaceAll("\\s+", " ");
        return output;
    }
}
