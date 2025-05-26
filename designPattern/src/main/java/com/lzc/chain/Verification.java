package com.lzc.chain;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Verification {

    ValidatorChain buildValidatorChain(Field declaredField) {
        ValidatorChain validatorChain = new ValidatorChain();
        if (declaredField.isAnnotationPresent(Max.class)) {
            int value = declaredField.getAnnotation(Max.class).value();
            validatorChain.addLastVerify(new MaxVerify(value));
        }
        if (declaredField.isAnnotationPresent(Min.class)) {
            int value = declaredField.getAnnotation(Min.class).value();
            validatorChain.addLastVerify(new MinVerify(value));
        }
        return validatorChain;
    }



    @SneakyThrows
    void verification(Object value) {
        Class<?> valueClass = value.getClass();
        for (Field declaredField : valueClass.getDeclaredFields()) {
            declaredField.setAccessible(true);

            ValidatorChain validatorChain = buildValidatorChain(declaredField);
            validatorChain.doVerify(declaredField.get(value));
        }
    }

}
