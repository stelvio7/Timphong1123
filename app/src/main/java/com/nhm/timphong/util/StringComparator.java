package com.nhm.timphong.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Created by AppDev on 2015-07-07.
 */
// Comparator의 구현체 입니다. 핵심은 이 것이지요.
// 꼼꼼히 분석해 보시기 바랍니다. 도움 되실거에요~ ^^;
public final class StringComparator implements Comparator
{
    private static final String REGEX_KO       = "^[ㄱ-힣].*";
    private static final String REGEX_EN_UPPER = "^[A-Z].*";
    private static final String REGEX_EN_LOWER = "^[a-z].*";
    private static final String REGEX_NUMERIC  = "^[0-9].*";
    private static final String REGEX_ETC      = "^[^ㄱ-힣A-Za-z0-9].*";

    private static final String[] REGEX_ARRAY = {
            REGEX_NUMERIC, REGEX_EN_UPPER, REGEX_ETC, REGEX_EN_LOWER, REGEX_KO
    };

    public static final int FIRST_LETTER_IS_KOREAN;
    public static final int FIRST_LETTER_IS_EN_UPPER;
    public static final int FIRST_LETTER_IS_EN_LOWER;
    public static final int FIRST_LETTER_IS_NUMERIC;
    public static final int FIRST_LETTER_IS_ETC;

    static {
        Arrays.sort(REGEX_ARRAY);

        FIRST_LETTER_IS_KOREAN   = Arrays.binarySearch(REGEX_ARRAY, REGEX_KO);
        FIRST_LETTER_IS_EN_UPPER = Arrays.binarySearch(REGEX_ARRAY, REGEX_EN_UPPER);
        FIRST_LETTER_IS_EN_LOWER = Arrays.binarySearch(REGEX_ARRAY, REGEX_EN_LOWER);
        FIRST_LETTER_IS_NUMERIC  = Arrays.binarySearch(REGEX_ARRAY, REGEX_NUMERIC);
        FIRST_LETTER_IS_ETC      = Arrays.binarySearch(REGEX_ARRAY, REGEX_ETC);
    }

    public static final int SORT_ORDER_ASC  = 1;
    public static final int SORT_ORDER_DESC = -1;

    private int      mOrderBy      = SORT_ORDER_ASC;
    private String[] mSortSequency = REGEX_ARRAY;

    public void setOrderByASC()
    {
        this.mOrderBy = SORT_ORDER_ASC;
    }

    public void setOrderByDESC()
    {
        this.mOrderBy = SORT_ORDER_DESC;
    }

    public void setSortSequency(final int first, final int second, final int third, final int fourth, final int fifth)
    {
        setSortSequency(new int[] {first, second, third, fourth, fifth});
    }

    public void setSortSequency(final int[] sortSequency)
    {
        if(sortSequency.length != REGEX_ARRAY.length) {
            throw new IllegalArgumentException();
        }

        int[] forCheck = new int[REGEX_ARRAY.length];
        String[] temp = new String[REGEX_ARRAY.length];

        for(int i = 0 ; i < REGEX_ARRAY.length ; i++) {
            try {
                if(forCheck[sortSequency[i]] == -1) {
                    throw new IllegalArgumentException("정렬 요소가 중복 설정 되었습니다.");
                }

                temp[i] = REGEX_ARRAY[sortSequency[i]];

                forCheck[sortSequency[i]] = -1;
            }
            catch(Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        mSortSequency = temp;
    }

    // @Override
    public int compare(Object o1, Object o2)
    {
        String compareSource = ((String) o1);
        String compareTarget = ((String) o2);

        int sourceType = -1, targetType = -1;

        // 비교하려고 하는 두 문자가 공백으로 시작하는 경우의 처리
        if(Pattern.matches("^[\\p{Space}].*", compareSource) && Pattern.matches("^[\\p{Space}].*", compareTarget)) {
            compareSource = compareSource.trim();
            compareTarget = compareTarget.trim();
        }

        for(int i = 0 ; i < mSortSequency.length ; i++) {
            if(Pattern.matches(mSortSequency[i], compareSource)) {
                sourceType = i;
                break;
            }
        }

        for(int i = 0 ; i < mSortSequency.length ; i++) {
            if(Pattern.matches(mSortSequency[i], compareTarget)) {
                targetType = i;
                break;
            }
        }
        int result = sourceType - targetType;
        return result == 0 ? compareSource.compareTo(compareTarget) * mOrderBy : result;
    }
}
