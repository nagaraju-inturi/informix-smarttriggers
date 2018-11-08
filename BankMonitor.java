import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.informix.smartTrigger.IfmxSmartTriggerCallback;
import com.informix.smartTrigger.IfxSmartTrigger;

public class BankMonitor implements IfmxSmartTriggerCallback {
public static void main(String[] args) throws SQLException {
        IfxSmartTrigger trigger = new IfxSmartTrigger("jdbc:informix-sqli://demo:9088/sysadmin:user=informix;password=in4mix");
        trigger.timeout(5).label("bank_alert");  //optional parameters
        trigger.addTrigger("account", "informix", "bank", 
                "SELECT * FROM account WHERE balance < 0", new BankMonitor());
        trigger.watch();
}
@Override
public void notify(String json) {
        System.out.println("Bank Account Ping!");
        if(json.contains("ifx_isTimeout")) {
                        System.out.println("-- No balance issues");
        }
        else {
                        System.out.println("-- Bank Account Alert detected!");
                        System.out.println("   " + json);
        }
}
}
