package queries.ObjectMapperExample;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.util.UUID;

@Entity
public class Messages {

    @PartitionKey(1)
    private String userFrom;

    @ClusteringColumn(1)
    private UUID created;

    private String userTo;

    private String message;

    public Messages() {
    }

    public Messages(String userFrom, UUID created, String userTo, String message) {
        this.userFrom = userFrom;
        this.created = created;
        this.userTo = userTo;
        this.message = message;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public UUID getCreated() {
        return created;
    }

    public void setCreated(UUID created) {
        this.created = created;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "userFrom='" + userFrom + '\'' +
                ", created=" + created +
                ", userTo='" + userTo + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
