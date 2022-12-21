package helpers;

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserAccount;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Helper {

    private static List<UserAccount> userAccounts = Lists.newArrayList();

    static {
        try {
            userAccounts = new ObjectMapper()
                    .readValue(Helper.class.getResourceAsStream("/dataset/testAccounts.json")
                            , new TypeReference<>() {
                            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPrefixByDate() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("ddMMyy_HHmmss");
        return formatForDateNow.format(date);
    }

    public static UserAccount getUserAccountByIndex(int index) {
        return userAccounts.get(index);
    }
}
