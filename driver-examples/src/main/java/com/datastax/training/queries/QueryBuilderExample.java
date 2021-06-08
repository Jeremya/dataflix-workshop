package queries;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;

import java.util.UUID;


public class QueryBuilderExample {
    public static void main(String[] args) {

        // ---------------------------------------------
        // Step1 - Connection (driver will read application.conf
        // ---------------------------------------------
        System.out.println("Initializing connection to ASTRA");
        try (CqlSession cqlSession = CqlSession.builder().build()) {
            System.out.println("+ Connection Successfully established.");

            // ---------------------------------------------
            // Step2 - Create a table
            // ---------------------------------------------
            cqlSession.execute(SchemaBuilder.createTable("messages")
                    .ifNotExists()
                    .withPartitionKey("user_from", DataTypes.TEXT)
                    .withClusteringColumn("created", DataTypes.TIMEUUID)
                    .withColumn("user_to", DataTypes.TEXT)
                    .withColumn("message", DataTypes.TEXT)
                    .build());
            System.out.println("+ Table 'messages' has been created.");

            // ---------------------------------------------
            // Step3 - Insert messages
            // ---------------------------------------------
            UUID id1 = Uuids.timeBased();
            cqlSession.execute(QueryBuilder.insertInto("messages")
                .value("user_from", QueryBuilder.literal("Astra"))
                .value("created", QueryBuilder.literal(id1))
                .value("user_to", QueryBuilder.literal("You"))
                .value("message", QueryBuilder.literal("Hello"))
                    .build());
            System.out.println("+ Messages '"+ id1 + "' has been created.");

            UUID id2 = Uuids.timeBased();
            cqlSession.execute(QueryBuilder.insertInto("messages")
                    .value("user_from", QueryBuilder.literal("Astra"))
                    .value("created", QueryBuilder.literal(id2))
                    .value("user_to", QueryBuilder.literal("You"))
                    .value("message", QueryBuilder.literal("World !"))
                    .build());
            System.out.println("+ Messages '"+ id2 + "' has been created.");

            // ---------------------------------------------
            // Step4 - Read Messages
            // ---------------------------------------------
            System.out.println("+ Reading records from table:");

            ResultSet rs = cqlSession.execute(QueryBuilder.selectFrom("messages")
                    .all()
                    .whereColumn("user_from")
                            .isEqualTo(QueryBuilder.literal("Astra"))
                    .build());

            for(Row row:rs) {
                System.out.println("   " + row.getString("user_from") + " says '" +
                        row.getString("message") + "' to '" +
                        row.getString("user_to") + "'");
            }
            System.out.println("[OK] - End of Demo");

        }

    }


}
