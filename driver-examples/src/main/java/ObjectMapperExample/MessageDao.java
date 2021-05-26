package ObjectMapperExample;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;

@Dao
public interface MessageDao {

    @Insert
    void save(Messages message);

    @Query("SELECT * FROM ${keyspaceId}.${tableId} "
            + "WHERE user_from = :userFrom ")
    PagingIterable<Messages> retrieveAllMessagesByUser(String userFrom);
}
