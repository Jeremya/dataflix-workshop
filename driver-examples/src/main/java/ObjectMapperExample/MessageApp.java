package ObjectMapperExample;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageApp {

    public static void main(String[] args) {

        try (CqlSession session = CqlSession.builder().build()) {

            MessagesMapper mapper =
                    MessagesMapper.builder(session).withDefaultKeyspace("catalog").build();

            MessageDao messageDao = mapper.messageDao();

            Messages message = new Messages("Astra", Uuids.timeBased(), "You", "Hello");

            messageDao.save(message);

            messageDao.retrieveAllMessagesByUser("Astra").all()
                    .stream().map(Messages::getMessage)
                    .forEach(mes -> { System.out.println(mes); });
        }
    }
}
