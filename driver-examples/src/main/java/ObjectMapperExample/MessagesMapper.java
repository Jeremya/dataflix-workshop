package ObjectMapperExample;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface MessagesMapper {
        @DaoFactory
        MessageDao messageDao();

        static MapperBuilder<MessagesMapper> builder(CqlSession session) {
                return new MessagesMapperBuilder(session);
        }
}

