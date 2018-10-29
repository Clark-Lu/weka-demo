package com.weka.demo.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by changlu on 10/25/18.
 */
public class SocketHandler implements Runnable{

    private SocketChannel socketChannel;

    public SocketHandler(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int num;
            while ((num = socketChannel.read(buffer)) > 0){
                buffer.flip();

                byte[] bytes = new byte[num];
                buffer.get(bytes);

                String re = new String(bytes,"UTF-8");
                System.out.println("收到信息：" + re);

                ByteBuffer writeBuffer = ByteBuffer.wrap(("我已收到你的请求，请求内容是：" + re).getBytes());
                socketChannel.write(writeBuffer);

                buffer.clear();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
