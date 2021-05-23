package ObjectMapperExample;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Query;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface MessageDao {

    @Insert
    void save(Messages product);

    @Query("SELECT * FROM ${keyspaceId}.${tableId} "
            + "WHERE user_from = :userFrom ")
    PagingIterable<Messages> retrieveAllMessagesByUser(String userFrom);
}
