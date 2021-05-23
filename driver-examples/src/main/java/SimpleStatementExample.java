import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.uuid.Uuids;

import java.util.UUID;

public class SimpleStatementExample {
    public static void main(String[] args) {

        // ---------------------------------------------
        // Step1 - Connection (driver will read application.conf
        // ---------------------------------------------
        System.out.println("Initializing connection to ASTRA");
        try (CqlSession cqlSession = CqlSession.builder().build()) {
            System.out.println("+ Connection Successfully established.");

            // ---------------------------------------------
            // Step3 - Create a table
            // ---------------------------------------------
            cqlSession.execute("CREATE TABLE IF NOT EXISTS messages(\n" +
                    "   user_from text,\n" +
                    "   created timeuuid,\n" +
                    "   user_to text,\n" +
                    "   message text,\n" +
                    "   PRIMARY KEY((user_from), created)\n" +
                    ") WITH CLUSTERING ORDER BY (created ASC);");
            System.out.println("+ Table 'messages' has been created.");

            // ---------------------------------------------
            // Step4 - Insert messages
            // ---------------------------------------------
            UUID id1 = Uuids.timeBased();
            cqlSession.execute(SimpleStatement.builder(
                    "INSERT INTO messages (user_from, created, user_to, message) "
                            + "VALUES (?,?,?,?)")
                    .addPositionalValue("Astra")
                    .addPositionalValue(id1)
                    .addPositionalValue("You")
                    .addPositionalValue("Hello")
                    .build());
            System.out.println("+ Messages '"+ id1 + "' has been created.");
            UUID id2 = Uuids.timeBased();
            cqlSession.execute(SimpleStatement.builder(
                    "INSERT INTO messages (user_from, created, user_to, message) "
                            + "VALUES (?,?,?,?)")
                    .addPositionalValue("Astra")
                    .addPositionalValue(id2)
                    .addPositionalValue("You")
                    .addPositionalValue("World !")
                    .build());
            System.out.println("+ Messages '"+ id2 + "' has been created.");

            // ---------------------------------------------
            // Step5 - Read Messages
            // ---------------------------------------------
            System.out.println("+ Reading records from table:");
            ResultSet rs = cqlSession.execute(SimpleStatement.builder(
                    "SELECT * FROM messages "
                            + "WHERE user_from = ?")
                    .addPositionalValue("Astra").build());
            for(Row row:rs) {
                System.out.println("   " + row.getString("user_from") + " says '" +
                        row.getString("message") + "' to '" +
                        row.getString("user_to") + "'");
            }
            System.out.println("[OK] - End of Demo");

        }

    }


}
