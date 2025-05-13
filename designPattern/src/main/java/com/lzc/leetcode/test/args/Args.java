package com.lzc.leetcode.test.args;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Args {


    Object[] args;

    public Args(Object ...args) {
        this.args = args;
    }



}
