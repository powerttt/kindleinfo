package cn.kindleinfo.enums;

public interface IReadWeekEnum {

    String BASE_URL = "http://www.ireadweek.com";

    String CODE_BASE_BOOK="B";

    String CODE_BASE_AUTHOR="A";

    String CODE_BASE_LABEL="L";

    String CODE_BASE_TYPE="T";


    long CODE_COUNT = 10000;

    Integer PHOTO_BOOK_TYPE = 0;
    Integer PHOTO_AUTHOR_TYPE = 1;
    /**
     * 书的信息
     */
    Integer BOOK_NAME_INDEX = 0;
    Integer BOOK_AUTHOR_INDEX = 1;
    Integer BOOK_LABEL_INDEX = 2;
    Integer BOOK_SCORE_INDEX = 3;
    Integer BOOK_INTRODUCTION_INDEX = 6;


    /**
     * 下载地址
     */
    int DOWN_URL_BAI_DU_PAD_INDEX = 0;
    int DOWN_URL_WEI_YUN_INDEX = 1;
    int DOWN_URL_CT_INDEX = 2;

    /**
     * redis 缓存
     */
    String REDIS_KEY_SITE = "IReadWeek";
    int REDIS_KEY_SITE_INDEX = 0;
    String REDIS_KEY_BOOK_MAP = "bookMap";
    String REDIS_KEY_AUTHOR_MAP = "authorMap";
    String REDIS_KEY_LABEL_MAP = "bookLabelMap";

}
