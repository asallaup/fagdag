package no.miles.switchex;

import javax.jms.*;
import java.lang.IllegalStateException;

public class SwichPatternMatching implements MessageListener {

    public void onMessageWithClassCast(Message message) {

        try {
            String body = null;
            if (message instanceof TextMessage) {
                body = extractBody((TextMessage) message);
            } else if (message instanceof MapMessage) {
                body = extractBody((MapMessage) message);
            } else if (message instanceof ObjectMessage) {
                body = extractBody((ObjectMessage) message);
            } else if (message instanceof StreamMessage) {
                body = extractBody((StreamMessage) message);
            } else if (message instanceof BytesMessage) {
                body = extractBody((BytesMessage) message);
            } else {
                throw new IllegalStateException("Unexpected value: " + message);
            }
            handleMessage(body);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void onMessageWithoutClassCast(Message message) {

        try {
            String body = null;
            if (message instanceof TextMessage textMessage ) {
                body = extractBody(textMessage);
            } else if (message instanceof MapMessage mapMessage) {
                body = extractBody(mapMessage);
            } else if (message instanceof ObjectMessage objectMessage) {
                body = extractBody(objectMessage);
            } else if (message instanceof StreamMessage streamMessage) {
                body = extractBody(streamMessage);
            } else if (message instanceof BytesMessage bytesMessage) {
                body = extractBody(bytesMessage);
            } else {
                throw new IllegalStateException("Unexpected value: " + message);
            }
            handleMessageOldFasion(body);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onMessage(Message message) {
        try {
            String body = switch ((Object) message) {
                case TextMessage textMessage -> extractBody(textMessage);
                case MapMessage mapMessage -> extractBody(mapMessage);
                case ObjectMessage objectMessage -> extractBody(objectMessage);
                case StreamMessage streamMessage -> extractBody(streamMessage);
                case BytesMessage bytesMessage -> extractBody(bytesMessage);
                default -> throw new IllegalStateException("Unexpected value: " + message);
            };
            handleMessage(body);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    private void handleMessageOldFasion(String body) {
        if ( body.isEmpty()){
            throw new IndexOutOfBoundsException();
        } else if (body.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")){
            iGotMail(body);
        } else {
            boringImplementation(body);
        }
    }

    private void handleMessage(String body) {
        int when = 5; // Legal when is not reserved her
        switch (body) {
            case String s when s.isEmpty() -> throw new IndexOutOfBoundsException();
            case String s when s.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n") -> iGotMail(s);
            default -> boringImplementation(body);
        }
    }


    private void boringImplementation(String body) {
    }

    private void iGotMail(String s) {

    }

    private String extractBody(BytesMessage bytesMessage) throws JMSException {
        return bytesMessage.getBody(String.class);
    }

    private String extractBody(StreamMessage streamMessage) throws JMSException {
        return streamMessage.getBody(String.class);
    }

    private String extractBody(ObjectMessage objectMessage) throws JMSException {
        return objectMessage.getBody(String.class);
    }

    private String extractBody(MapMessage mapMessage) throws JMSException {
        return mapMessage.getString("føkasføf");
    }

    private String extractBody(TextMessage textMessage) throws JMSException {
        return textMessage.getText();
    }
}
