package com.mhontar.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
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
    private SelectElement languageSwitch;

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

        Element domSelect = DOM.getElementById("languageSwitcher");
        final ListBox listBox = ListBox.wrap(domSelect);
        listBox.setItemSelected(getLocaleName().equals(EN_US) ? 0 : 1,true);
        listBox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                setLocale(listBox.getSelectedIndex());
            }
        });


    }

    private void setLocale(int index) {
        String localeName = null;
        switch (index) {
            case 0:
                localeName = EN_US;
                break;
            case 1:
                localeName = FR_FR;
                break;

        }
        setLocaleCookie(localeName);
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
