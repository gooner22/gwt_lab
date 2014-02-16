package com.mhontar.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.mhontar.gwt.model.User;

import java.util.Date;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BasicProject implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";
    public static final String EN_US = "en_US";
    public static final String FR_FR = "fr_FR";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


    private final Messages messages = GWT.create(Messages.class);
    private Button sendButton;
    private TextBox nameField;
    private PasswordTextBox pwdField;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        sendButton = new Button(messages.sendButton());
        nameField = new TextBox();
        pwdField = new PasswordTextBox();

        nameField.getElement().setAttribute("placeholder", messages.nameField());
        pwdField.getElement().setAttribute("placeholder", messages.pwdField());


        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("pwdFieldContainer").add(pwdField);
        RootPanel.get("sendButtonContainer").add(sendButton);

        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                swapLocales();
                User user = null;
                try {

                    greetingService.loginUser(nameField.getText(), pwdField.getText(), new AsyncCallback<User>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            alertMessage(messages.loginFailure());
                        }

                        @Override
                        public void onSuccess(User user) {
                            if(user != null){
                                alertMessage("Hi, " + user.getUsername());
                            }else{
                                alertMessage(messages.loginFailure());
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();


    }

    private void swapLocales() {
        String localeName = getLocaleName();
        ;
        setLocaleCookie(localeName.equals(FR_FR) ? EN_US : FR_FR);
    }

    private void alertMessage(String alertMessage) {
        com.google.gwt.user.client.Window.alert(alertMessage);
    }


    private String getLocaleCookie(){
        final String cookieName = LocaleInfo.getLocaleCookieName();
        return cookieName;
    }
    private String getLocaleName(){

        return Cookies.getCookie(getLocaleCookie());
    }

    private void setLocaleCookie( String locale )
    {
        final String localeCookie = getLocaleCookie();
        if ( localeCookie != null )
        {
            Date expires = new Date();
            expires.setYear( expires.getYear() + 1 );
            Cookies.setCookie(localeCookie, locale, expires);
        }
//        if ( !control )
//        {
            com.google.gwt.user.client.Window.Location.reload();
//        }
    }

}
