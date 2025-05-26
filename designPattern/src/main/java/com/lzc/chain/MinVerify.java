package com.lzc.chain;

public class MinVerify implements Verify{

    int min;

    public MinVerify(int value) {
        min = value;
    }

    @Override
    public void verify(VerifyContext verifyContext) {
        Object value = verifyContext.getValue();
        if (value instanceof Integer) {
            Integer intValue = (Integer) value;
            if (intValue < min) {
                verifyContext.addErrorMsg("小于最小值");
            }
        }
        verifyContext.doNext(value);
    }

}
