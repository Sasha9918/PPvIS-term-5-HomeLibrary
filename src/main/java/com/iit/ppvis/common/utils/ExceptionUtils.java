package com.iit.ppvis.common.utils;

import com.iit.ppvis.common.exceptions.EntityAlreadyExistsException;
import com.iit.ppvis.common.exceptions.EntityNotFoundException;
import com.iit.ppvis.common.exceptions.ForbiddenAccessException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionUtils {

    public static EntityNotFoundException entityNotFoundException(String string) {
        return new EntityNotFoundException(string);
    }

    public static ForbiddenAccessException forbiddenAccessException(String string) {
        return new ForbiddenAccessException(string);
    }

    public static EntityAlreadyExistsException entityAlreadyExistsException(String string) {
        return new EntityAlreadyExistsException(string);
    }

}
