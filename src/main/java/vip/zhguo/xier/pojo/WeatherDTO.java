package vip.zhguo.xier.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WeatherDTO {
    //地理位置
    private String area_3;
    /*天气(白天)*/
    private String wtNm1;
     /*天气(夜间)*/
    private String wtNm2;
    /*温度(白天)*/
    private String wtTemp1;
    /*温度(夜间)*/
    private String wtTemp2;
    /*日出时间*/
    private String wtSunr;
    /*日落时间(24小时制)*/
    private String wtSuns;
}
