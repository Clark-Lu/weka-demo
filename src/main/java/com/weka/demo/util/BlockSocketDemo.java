package com.weka.demo.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by changlu on 10/25/18.
 */
public class BlockSocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();

            SocketHandler handler = new SocketHandler(socketChannel);

            new Thread(handler).start();
        }
    }
}


