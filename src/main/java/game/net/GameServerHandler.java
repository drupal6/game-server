package game.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class GameServerHandler implements ChannelInboundHandler {

	@Override
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		System.out.println("handlerAdded");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
		System.out.println("handlerRemoved");
	}

	@Override
	public void channelActive(ChannelHandlerContext arg0) throws Exception {
		System.out.println("channelActive");
	}

	@Override
	public void channelInactive(ChannelHandlerContext arg0) throws Exception {
		System.out.println("channelInactive");
	}

	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
		System.out.println("channelRead");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext arg0) throws Exception {
		System.out.println("channelReadComplete");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext arg0) throws Exception {
		System.out.println("channelRegistered");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext arg0) throws Exception {
		System.out.println("channelUnregistered");
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext arg0) throws Exception {
		System.out.println("channelWritabilityChanged");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) throws Exception {
		System.out.println("exceptionCaught");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext arg0, Object arg1) throws Exception {
		System.out.println("userEventTriggered");
		if(arg1 instanceof IdleStateEvent) {
			System.out.println("IdleStateEvent");
		}else {
			arg0.fireUserEventTriggered(arg1);
		}
	}

}
