package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserAccount;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Helper {

    public String getPrefixByDate() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("ddMMyy_HHmmss");
        return formatForDateNow.format(date);
    }

    @SneakyThrows
    public static List<UserAccount> getUserAccounts() {
        return new ObjectMapper()
                .readValue(Helper.class.getResourceAsStream("/dataset/testAccounts.json")
                        , new TypeReference<>() {
                        });
    }

    public UserAccount getUserAccountByIndex(int index) {
        List<UserAccount> userAccount = getUserAccounts();
        return userAccount.get(index);
    }
}
