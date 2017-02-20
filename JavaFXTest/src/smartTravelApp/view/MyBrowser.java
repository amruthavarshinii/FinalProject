/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */

package smartTravelApp.view;

import java.net.URL;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import smartTravelApp.controller.JavaScriptHandler;

public class MyBrowser extends Region {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    public MyBrowser() {
        webView.setMinSize(700, 340);
        final URL urlGoogleMaps = getClass().getResource("Map.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

        getChildren().add(webView);

        //setMember() should be called after the page has been loaded. The reason is, JavaScript world is recreated 
        //each time a new page is loaded. The newly created window object won't have any custom members installed. A 
        //fine place to call setMember() is from a listener attached to WebEngine.getLoadWorker().stateProperty().
        JSObject jsobj = (JSObject) webEngine.executeScript("window");
        jsobj.setMember("java", new JavaScriptHandler());
    }

}