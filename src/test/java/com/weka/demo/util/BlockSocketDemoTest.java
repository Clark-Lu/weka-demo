package com.weka.demo.util;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by changlu on 10/25/18.
 */
public class BlockSocketDemoTest {

    @Test
    public void testSendMessage() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));

        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello World".getBytes());
        socketChannel.write(byteBuffer);

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int num;
        if ((num = socketChannel.read(readBuffer)) > 0){
            readBuffer.flip();

            byte[] re = new byte[num];

            readBuffer.get(re);

            String result = new String(re,"UTF-8");
            System.out.println("返回值：" + result);
        }
    }

}
