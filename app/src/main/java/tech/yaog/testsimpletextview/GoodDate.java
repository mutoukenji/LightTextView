package tech.yaog.testsimpletextview;

/**
 * 商品日期管理
 */
public class GoodDate {
    public long cargoId;//货道id
    private int year;//有效期-年
    private int month;//有效期-月
    private int day;//有效期-日
    private int minute;//有效期-分
    private int hour;//有效期-秒
    private int isInShelfLife;//是否设置了保质期,0 未设置  1 设置了多少天

    public GoodDate(long cargoId, int year, int month, int day, int minute, int hour,
                    int isInShelfLife) {
        this.cargoId = cargoId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.minute = minute;
        this.hour = hour;
        this.isInShelfLife = isInShelfLife;
    }

    public GoodDate() {
    }

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getIsInShelfLife() {
        return isInShelfLife;
    }

    public void setIsInShelfLife(int isInShelfLife) {
        this.isInShelfLife = isInShelfLife;
    }

    @Override
    public String toString() {
        return "GoodDate{" +
                "cargoId=" + cargoId +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", minute='" + minute + '\'' +
                ", hour='" + hour + '\'' +
                ", isInShelfLife=" + isInShelfLife +
                '}';
    }


}
