package configuration;

import configuration.enums.Platform;

class OperatingSystem {

    private static Platform currentOS = null;

    private static Platform detectOS()
    {
        if(currentOS == null)
        {
            String os = System.getProperty("os.name").toLowerCase();
            currentOS = Platform.unsupported;
            if(os.contains("win")) currentOS = Platform.Windows;
            if(os.contains("mac")) currentOS = Platform.Mac;
            if(os.contains("nux") || os.contains("nix")) currentOS = Platform.Unix;
        }
        return currentOS;
    }

    static boolean isWindows() {return (detectOS() == Platform.Windows);}
    static boolean isMac() {return (detectOS() == Platform.Mac);}
    static boolean isUnix() {return (detectOS() == Platform.Unix);}
}
