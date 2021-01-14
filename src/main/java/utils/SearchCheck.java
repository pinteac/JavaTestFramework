package utils;

public class SearchCheck {

    private SearchCheck(){}

    private static SearchCheck instance = null;

    public static SearchCheck getInstance()
    {
        if(instance == null)
            instance = new SearchCheck();
        return instance;
    }

    public String searchedLocation, checkOutDate, checkInDate, numberOfGuests;
}
