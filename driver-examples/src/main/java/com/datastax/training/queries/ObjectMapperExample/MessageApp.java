package queries.ObjectMapperExample;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.uuid.Uuids;

public class MessageApp {

    public static void main(String[] args) {

        try (CqlSession session = CqlSession.builder().build()) {

            MessagesMapper mapper =
                    MessagesMapper.builder(session).withDefaultKeyspace("catalog").build();

            MessageDao messageDao = mapper.messageDao();

            Messages message = new Messages("Astra", Uuids.timeBased(), "You", "Hello Object Mapper");

            messageDao.save(message);

            messageDao.retrieveAllMessagesByUser("Astra").all()
                    .stream().map(Messages::getMessage)
                    .forEach(mes -> { System.out.println(mes); });
        }
    }
}
