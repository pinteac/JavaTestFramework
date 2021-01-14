package utils;

import org.openqa.selenium.WebElement;

public class PropertyCheck {

    private PropertyCheck(){}

    private static PropertyCheck instance = null;

    public static PropertyCheck getInstance()
    {
        if(instance == null)
            instance = new PropertyCheck();
        return instance;
    }

    public WebElement PROPERTY_BUTTON;
    public String PROPERTY_NAME;
    public String PROPERTY_TYPE;
    public String PROPERTY_RATING;
    public String PROPERTY_PRICE_PER_NIGHT;

    public void setPROPERTY_NAME(String property_name){ PROPERTY_NAME = property_name; }


    public void setPROPERTY_BUTTON(WebElement webElement) { PROPERTY_BUTTON = webElement; }

}
