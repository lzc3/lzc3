package com.lzc.generic;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        FormComponent<SingleTextComponent> formComponent = new FormComponent<SingleTextComponent>();
        formComponent.setComponent(new SingleTextComponent());
        SingleTextComponent singleTextComponent = formComponent.getComponent();

    }

}
