package com.lzc.chain;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain {


    List<Verify> verifyList = new ArrayList<>();

    public void addLastVerify(Verify verify) {
        verifyList.add(verify);
    }

    void doVerify(Object value) {
        VerifyContext verifyContext = new VerifyContext(value);
        for (int i = 0; i < verifyList.size(); i++) {
            int index = verifyContext.getIndex();
            if (i != index) {
                break;
            }
            Verify verify = verifyList.get(index);
            verify.verify(verifyContext);

            if (!verifyContext.continueChain()) {
                break;
            }
        }
        verifyContext.infoErrorMsg();
    }

}
