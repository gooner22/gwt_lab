package com.mhontar.gwt.client;

/**
 * Interface to represent the messages contained in resource bundle:
 * /Users/Maks/Projects/IYST/gwt/basic-project/target/basic-project-0.0.1-SNAPSHOT/WEB-INF/classes/com/mhontar/gwt/client/Messages.properties'.
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

    /**
     * Translated "Enter your name".
     *
     * @return translated "Enter your name"
     */
    @DefaultMessage("login")
    @Key("nameField")
    String nameField();

    @DefaultMessage("password")
    @Key("pwdField")
    String pwdField();

    @DefaultMessage("login failure")
    @Key("alert.login.failure")
    String loginFailure();

    @DefaultMessage("success")
    @Key("alert.login.success")
    String loginSuccess();

    /**
     * Translated "Send".
     *
     * @return translated "Send"
     */
    @DefaultMessage("Send")
    @Key("sendButton")
    String sendButton();
}
