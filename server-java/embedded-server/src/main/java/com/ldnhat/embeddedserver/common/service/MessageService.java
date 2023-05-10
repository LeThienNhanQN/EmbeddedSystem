package com.ldnhat.embeddedserver.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageService {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    MessageService(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.messageSource = resourceBundleMessageSource;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, String langKey) {
        Locale locale = langKey != null ? Locale.forLanguageTag(langKey) : Locale.US;
        return messageSource.getMessage(code, null, locale);
    }

    public String buildMessages(String messageKey) {
        return String.format(this.getMessage(messageKey), null, LocaleContextHolder.getLocale());
    }
}
