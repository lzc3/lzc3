package com.lzc.chain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VerifyContext {

    List<String> errorList = new ArrayList<>();

    private boolean coherentNeed = true;

    private int index = 0;

    private Object value;

    public VerifyContext(Object value) {
        this.value = value;
    }

    public void addErrorMsg(String msg) {
        errorList.add(msg);
    }

    public void infoErrorMsg() {
        for (String msg : errorList) {
            System.out.println(msg);
        }
    }

    public void forbidCoherent() {
        this.coherentNeed = false;
    }

    /**
     * 校验不通过后是否继续
     * @return false 退出
     */
    public boolean coherentNecessary() {
        return coherentNeed;
    }


    public boolean continueChain() {
        return coherentNecessary();
    }

    public void doNext(Object value) {
        index++;
        this.value = value;
    }
}

