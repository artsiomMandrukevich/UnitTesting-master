package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public String getPrefixByDate() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("ddMMyy_HHmmss");
        return formatForDateNow.format(date);
    }
}
