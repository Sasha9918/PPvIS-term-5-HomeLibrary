package com.iit.ppvis.common.utils;

import com.iit.ppvis.model.enums.VisitorRole;
import lombok.experimental.UtilityClass;

import static com.iit.ppvis.common.utils.ExceptionUtils.forbiddenAccessException;
import static com.iit.ppvis.model.enums.VisitorRole.ROLE_VISITOR;

@UtilityClass
public class AccessUtils {

    public static void checkAccessLevel(VisitorRole role){
        if (role.equals(ROLE_VISITOR)){
            throw forbiddenAccessException("Must be owner to perform action");
        }
    }

}
