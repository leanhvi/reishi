package reshi.wordsegmenter;

import reishi.config.ReishiConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String regex = "[^zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOPaAeEoOuUiIdDyYáàạảãâấầậẩẫăắằặẳẵÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴéèẹẻẽêếềệểễÉÈẸẺẼÊẾỀỆỂỄóòọỏõôốồộổỗơớờợởỡÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠúùụủũưứừựửữÚÙỤỦŨƯỨỪỰỬỮíìịỉĩÍÌỊỈĨđĐýỳỵỷỹÝỲỴỶỸ0123456789.,]";
        String input = "​​BlackBerry Passport bản bạc (Silver Edition) có một số cải tiến về ngoại hình và tính năng so với Passport bảng thường nhưng không thật sự đáng kể. Nếu bạn đang sở hữu Passport thì không cần nâng cấp nhưng nếu đã chăm nhe từ lâu mà chưa mua thì phiên bản bạc này là bản hoàn thiện hơn rất nhiều. Dự kiến Passport Silver Edition sẽ được bán ở Việt Nam với giá 13.5 triệu đồng từ 24/8. ​BlackBerry Passport bác khác bản màu đen/đỏ những gì? Khung máy được làm bằng thép không rỉ, gia cố tốt hơn để bảo vệ khỏi những lức tác động từ bên ngoài Máy được thiết kế bo tròn hơn, dễ cầm hơn. Khi chạm vào những gờ hay cẩm máy bằng một tay bạn sẽ cảm nhận được điều này dễ nhất. Đây là cải tiến lớn thứ nhì. Bàn phím được thiết kế nhẹ hơn khá nhiều, bấm rất thích, nhất là khi nó cho cảm giác hơi nông hơn, vuốt qua lại giữa các phím dễ dàng hơn. Đây là cải tiến lớn nhất Mặt sau được làm nhám đi bằng cách thu nhỏ các vân lại, tăng mật độ để tay chúng ta đỡ trơn hơn khi cầm máy. Điểm này thì chắc bạn nào hay làm rơi điện thoại sẽ thích. Cụm camera được trang trí lại với viền tròn bạc bao quanh thay vì vuông như cũ. Viền này bảo đảm cho cụm ống kính của máy không bị trầy. Máy màu bạc Vậy nhược điểm là gì: Máy có giá mắc hơn khoảng 1 triệu đồng so với bản cũ (giá hiện tại, so với giá khi mới ra mắt thì vẫn rẻ hơn) Vẫn dùng CPU cũ, màn hình cũ (dù vẫn rất tốt)Hiện tại vẫn không rõ phiên bản mới có được cải tiến, sửa chữa một số lỗi trên phiên bản cũ hay chưa. Mình nghĩ là đã cải tiến được khá nhiều, chạy có cảm giác mượt hơn (OS 10.3.2) nhưng chưa thể xác nhận được gì cho đến khi có máy chính thức. Cấu hình BlackBerry Passport Silver Edition: Hệ điều hành: BlackBerry 10.3.2 Chip xử lý: Qualcomm Snapdragon 801, bốn nhân 2.2 GHz Màn hình: IPS 4.5\", 1440 x 1440, 453 ppi, Gorilla Glass 3 RAM: 3 GB Bộ nhớ trong: 32 GB Thẻ nhớ: microSD Camera: 13 MP, OIS, AF, LED Flash, quay phim 1080p@60fps Camera trước: 2 MP, 720p Wi-Fi ac, BT 4.0, A-GPS, GLONASS Kích thước: 128 x 90,3 x 9,3 mm Nặng: 196 gram Pin: 3450 mAh Mặt trước của máy được làm màu bạc, thép không rỉ gia cố cứng hơn phiên bản cũ Nhưng nhìn nhẹ nhàng hơn rất nhiều vì màu bạc chứ không phải màu đen Thế nhưng màu bạc lại làm lộ ra đường nối bàn phím và khung máy (có thể là do bản thử nghiệm, chưa rõ) Các bạn có thể thấy là các cạnh viền, các đường vuốt ở cạnh bên nhẹ nhàng hơn đáng kể, cầm đã hơn Tháo nắp ra bạn sẽ thấy khe cắm thẻ microSD và SIM Điểm này các bạn cần đợi một chút nhé, vì chưa rõ máy thương mại có hở nhẹ như vậy không Những đường vân đằng sau được làm nhỏ hơn, mật độ cao hơn, sờ vào thích hơn. Nhìn khá là hợp với cụm camera Cụm camera mới được thiết kế tròn hơn, bảo vệ bên trong tốt hơn ​  ";
        ReishiConfig config = new ReishiConfig();
        WordSegmenter ws = new WordSegmenter();
        
        System.out.println(ws.segmenting(input));
    }
}
