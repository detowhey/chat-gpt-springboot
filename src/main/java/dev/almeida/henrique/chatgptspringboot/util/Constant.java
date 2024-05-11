package dev.almeida.henrique.chatgptspringboot.util;

public interface Constant {
    String OPENAPI_TOKEN = PropertiesResource.getProperty("chatKey");
    String ASSISTANT_ID = PropertiesResource.getProperty("assistantKey");
}
