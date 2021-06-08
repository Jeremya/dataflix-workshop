package queries.ObjectMapperExample;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;

@Dao
public interface MessageDao {

    @Insert
    void save(Messages message);

    @Select
    PagingIterable<Messages> retrieveAllMessagesByUser(String userFrom);
}
