package com.ldnhat.embeddedserver.utils;

import com.ldnhat.embeddedserver.common.vo.PageInfo;
import com.ldnhat.embeddedserver.utils.constants.Constants;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {
    private static final Logger log = LoggerFactory.getLogger(AppUtils.class);

    /**
     * Convert LocalDateTime to milliseconds.
     *
     * @param dateTime date time value
     * @return milliseconds
     */
    public static Long convertLocalDateTimeToMilli(LocalDateTime dateTime) {
        if (null != dateTime) {
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        return 0L;
    }

    /**
     * Convert milliseconds to LocalDateTime.
     *
     * @param millis milliseconds
     * @return LocalDateTime instance
     */
    public static LocalDateTime convertMilliToLocalDateTime(Long millis) {
        if (null == millis) {
            return null;
        }
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Get current local date time millis
     *
     * @return milliseconds
     */
    public static Long getCurrentLocalDateTimeMillis() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Get current local date time millis
     *
     * @return milliseconds
     */
    public static String getCurrentLocalDateTime() {
        ZonedDateTime localDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    /**
     * Create paging with basic information
     *
     * @param <T>
     * @param page {@link Page} of T
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagingResponse(Page<T> page) {
        // page info
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setTotal(page.getTotalElements());
        pageInfo.setLimit(page.getSize());
        pageInfo.setPages(page.getTotalPages());
        pageInfo.setPage(page.getNumber() + 1);
        pageInfo.setResult(page.getContent());
        return pageInfo;
    }

    public static String generateTokenKey() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        return randomStringGenerator.generate(Constants.USER_TOKEN_KEY_LENGTH);
    }
}
