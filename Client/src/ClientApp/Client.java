package ClientApp;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    static ObjectInputStream in;
    static ObjectOutputStream out;
    static InetAddress ia;
    public static final int port = 1238;
    static SocketChannel channel;
    static Object command, answer;
    static String userCommand;
    static ByteBuffer buffer = ByteBuffer.allocate(10000);

    public static void main(String[] args) {

        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Client app запущено \nСистема готова к работе. Для просмотра списка команд введите \"help\".");

        while (true) {
            try {
                userCommand = reader.readLine();
                ClientUserCommands.check(userCommand); // Проверка команды на валидность
                if (ClientUserCommands.getStatus()==1) {  // Если команда введена корректно
                    command = ClientUserCommands.getCommand(userCommand, reader);  // getCommand возвращает введенную пользователем команду в виде объекта
                    if (command != null) {
                        channel = SocketChannel.open(new InetSocketAddress(ia, port));
                        channel.configureBlocking(false);
                        sendCommand(command);
                        buffer.clear();
                        buffer.position(0);
                        buffer.limit(10000);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                        answer = getAnswer();

                        if ((command = ProcessingAnswer.print(answer, userCommand)) != null) {

                            out.close();
                            channel.close();
                            channel = SocketChannel.open(new InetSocketAddress(ia, port));
                            channel.configureBlocking(false);
                            sendCommand(command);
                            buffer.clear();
                            buffer.position(0);
                            buffer.limit(10000);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                            answer = getAnswer();

                            ProcessingAnswer.print(answer, userCommand);   // Получает на вход ответ сервера (getAnswer()) и обрабатывает его
                        }

                        out.close();
                        channel.close();
                    }
                }
            } catch (IOException e) {
                System.err.println("Сервер временно недоступен");
            }
            catch (ClassNotFoundException e) {
                System.err.println("Сервер временно недоступен");
            }
        }

    }

    /**
     * Отправлят команду на сервер
     * @param com отправляемая команда
     */

    public static void sendCommand (Object com) {
        try {
            if(channel == null){
                System.err.println("Соединение не создано");
                return;
            }
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteStream);
            out.writeObject(com);
            channel.write(ByteBuffer.wrap(byteStream.toByteArray()));

            System.out.println("Отправлено на сервер");
        } catch (IOException e) {
            System.err.println("Не удалось отправить на сервер");
        }
    }

    /**
     * Получает ответ от сервера
     * @return объект в виде обработанной команды
     * @throws IOException
     */
    public static Object getAnswer () throws IOException, ClassNotFoundException {
        if(channel == null){
            System.err.println("Соединение не создано");
            return null;
        }

        channel.read(buffer);

        try {
            ObjectInputStream objStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
            return objStream.readObject();
        }catch(StreamCorruptedException e){
            return null;
        }
    }
}