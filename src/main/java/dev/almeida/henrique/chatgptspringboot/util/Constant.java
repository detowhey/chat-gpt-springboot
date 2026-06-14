package dev.almeida.henrique.chatgptspringboot.util;

public class Constant {

    private Constant() {
    }

    public static final String OPENAPI_TOKEN = PropertiesResource.getProperty("chatKey");
    public static final String ASSISTANT_ID = PropertiesResource.getProperty("assistantKey");
    public static final String CODE_200 = "200";
    public static final String CODE_201 = "201";
}
