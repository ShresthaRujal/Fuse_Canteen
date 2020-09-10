package com.fuse.canteen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CustomMessageSource {

    private final MessageSource messageSource;

    @Autowired
    public CustomMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String code) {
        return messageSource.getMessage(code,null,getCurrentLocale());
    }

    public String get(String code,Object... objects) {
        return messageSource.getMessage(code,objects,getCurrentLocale());
    }
    private Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }
}