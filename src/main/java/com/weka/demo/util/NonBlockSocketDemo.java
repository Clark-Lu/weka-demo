package com.weka.demo.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by changlu on 10/25/18.
 */
public class NonBlockSocketDemo {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(8080));

        server.configureBlocking(false);

        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int readyChannels = selector.select();
            if (readyChannels == 0){
                continue;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);
                } else if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int num = socketChannel.read(readBuffer);
                    if (num > 0){
                        System.out.println("收到数据：" + new String(readBuffer.array()).trim());

                        ByteBuffer writeBuffer = ByteBuffer.wrap("Hello client".getBytes());
                        socketChannel.write(writeBuffer);
                    }else if(num == -1){
                        socketChannel.close();
                    }
                }
            }
        }
    }
}

