package game.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyNet implements Runnable {
	
	private int port;
	private int readerIdleTimeSeconds = 10;
	private int writerIdleTimeSeconds = 10;
	private int allIdleTimeSeconds = 0;
	private int readSize = 2048;
	private int writeSize = 2048;
	
	public void bind() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.option(ChannelOption.SO_RCVBUF, readSize)
			.option(ChannelOption.SO_SNDBUF, writeSize)
			.childOption(ChannelOption.TCP_NODELAY, true)  //设置低延迟  禁用nagle算法
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast("idleStateHandle", new IdleStateHandler(
							readerIdleTimeSeconds, 
							writerIdleTimeSeconds, 
							allIdleTimeSeconds));
					sc.pipeline().addLast("decode", null);
					sc.pipeline().addLast("encode", null);
					sc.pipeline().addLast(new GameServerHandler());
				}
			});
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void run() {
		try {
			bind();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
