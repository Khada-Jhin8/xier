package vip.zhguo.xier.otherconfig;

public enum MsgColor {

    ONE( "#FFFF99"),
    TWO("#FFCCCC"),
    THREE("#FFCC99"),
    FOUR("#CCCCFF"),
    FIVE("#99CC99"),
    SIX("#669966"),
    SEVEN("#993366"),
    EIGHT("#66CCCC"),
    NINE("#6666FF"),
    TEN("#FFCC99"),
    ;


    private final String value;

    MsgColor( String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
