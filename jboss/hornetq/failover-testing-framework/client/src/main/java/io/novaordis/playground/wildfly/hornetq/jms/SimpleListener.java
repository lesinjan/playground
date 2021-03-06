/*
 * Copyright (c) 2015 Nova Ordis LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.novaordis.playground.wildfly.hornetq.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleListener implements MessageListener
{
    // Constants -------------------------------------------------------------------------------------------------------

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    private AtomicLong messageReceived;
    private boolean statsOnly;

    // Constructors ----------------------------------------------------------------------------------------------------

    public SimpleListener(AtomicLong messageReceived, boolean statsOnly)
    {
        this.messageReceived = messageReceived;
        this.statsOnly = statsOnly;
    }

    // MessageListener implementation ----------------------------------------------------------------------------------

    @Override
    public void onMessage(Message message)
    {
        messageReceived.incrementAndGet();
        if (statsOnly) {
            return;
        }

        String line = message.toString();

        try {
            if (message instanceof TextMessage) {
                line += " " + ((TextMessage) message).getText();
            }
        }
        catch (JMSException e) {
            line += " failure to retrieve the text: " + e.getMessage();
        }

        System.out.println(line);
    }

    // Public ----------------------------------------------------------------------------------------------------------

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
