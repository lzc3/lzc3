package com.lzc.chain;

/**
 * 责任链模式
 */
public class Main {

    public static void main(String[] args) {
        User user = new User(101);
        Verification verification = new Verification();
        verification.verification(user);
    }

}
