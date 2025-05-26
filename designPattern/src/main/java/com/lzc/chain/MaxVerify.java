package com.lzc.chain;

public class MaxVerify implements Verify{

    int max;

    public MaxVerify(int value) {
        max = value;
    }

    @Override
    public void verify(VerifyContext verifyContext) {
        Object value = verifyContext.getValue();
        if (value instanceof Integer) {
            Integer intValue = (Integer) value;
            if (intValue > max) {
//                verifyContext.forbidCoherent();
                verifyContext.addErrorMsg("大于最小值");
            }
        }
        verifyContext.doNext(101);
    }

}
