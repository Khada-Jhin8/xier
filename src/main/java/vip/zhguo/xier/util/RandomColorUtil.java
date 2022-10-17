package vip.zhguo.xier.util;

import vip.zhguo.xier.otherconfig.MsgColor;

/**
 * [随机获取颜色]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/10/17 10:49]
 */
public class RandomColorUtil {

    public static String getRandomColor() {
        //获取随机颜色对应值
        int randomNum = getRandom();
        MsgColor msgColor = null;
        switch (randomNum) {
            case 1:
                msgColor = MsgColor.ONE;
                break;
            case 2:
                msgColor = MsgColor.TWO;
                break;
            case 3:
                msgColor = MsgColor.THREE;
                break;
            case 4:
                msgColor = MsgColor.FOUR;
                break;
            case 5:
                msgColor = MsgColor.FIVE;
                break;
            case 6:
                msgColor = MsgColor.SIX;
                break;
            case 7:
                msgColor = MsgColor.SEVEN;
                break;
            case 8:
                msgColor = MsgColor.EIGHT;
                break;
            case 9:
                msgColor = MsgColor.NINE;
                break;
            case 10:
                msgColor = MsgColor.TEN;
                break;
        }
        return msgColor.getValue();
    }


    public static int getRandom() {
        double random = Math.random() * 10;
        return (int) random + 1;
    }

}
