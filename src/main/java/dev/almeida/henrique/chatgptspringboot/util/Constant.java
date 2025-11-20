package dev.almeida.henrique.chatgptspringboot.util;

public class Constant {

    private Constant() {
    }

    public static final String OPENAPI_TOKEN = PropertiesResource.getProperty("chatKey");
    public static final String ASSISTANT_ID = PropertiesResource.getProperty("assistantKey");
}
