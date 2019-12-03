package com.johnnyclark.threaddumpparser.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
class ThdStackParserTest {

  @Test
  void parse() {
    ThdStackParser parser = new ThdStackParser();
    String paragraph = "\"ActiveMQ Transport: tcp:///127.0.0.1:51576@61616\" #17372 daemon prio=5 os_prio=0 tid=0x00007f3308263000 nid=0x2023 runnable [0x00007f3244f51000]\n" +
        "   java.lang.Thread.State: RUNNABLE\n" +
        "\tat java.net.SocketInputStream.socketRead0(Native Method)\n" +
        "\tat java.net.SocketInputStream.socketRead(SocketInputStream.java:116)\n" +
        "\tat java.net.SocketInputStream.read(SocketInputStream.java:171)\n" +
        "\tat java.net.SocketInputStream.read(SocketInputStream.java:141)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpBufferedInputStream.fill(TcpBufferedInputStream.java:50)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpTransport$2.fill(TcpTransport.java:634)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpBufferedInputStream.read(TcpBufferedInputStream.java:59)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpTransport$2.read(TcpTransport.java:619)\n" +
        "\tat java.io.DataInputStream.readInt(DataInputStream.java:387)\n" +
        "\tat org.apache.activemq.openwire.OpenWireFormat.unmarshal(OpenWireFormat.java:268)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpTransport.readCommand(TcpTransport.java:240)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpTransport.doRun(TcpTransport.java:232)\n" +
        "\tat org.apache.activemq.transport.tcp.TcpTransport.run(TcpTransport.java:215)\n" +
        "\tat java.lang.Thread.run(Thread.java:745)";
    ThdStack thdStack = parser.parse(paragraph, new ArrayList<String>().listIterator());
    System.out.println(thdStack);
  }

  @Test
  void parse2() {
    final ThdStackParser parser = new ThdStackParser();
    final String p1 = "\"http-bio-7010-exec-112\" #15099 daemon prio=5 os_prio=0 tid=0x00007f33400dc800 nid=0x35f5 waiting on condition [0x00007f32948d7000]\n" +
        "   java.lang.Thread.State: TIMED_WAITING (parking)\n" +
        "\tat sun.misc.Unsafe.park(Native Method)\n" +
        "\t- parking to wait for  <0x00000005cd54ea90> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)\n" +
        "\tat java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)\n" +
        "\tat java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)\n" +
        "\tat java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)\n" +
        "\tat org.apache.tomcat.util.threads.TaskQueue.poll(TaskQueue.java:86)\n" +
        "\tat org.apache.tomcat.util.threads.TaskQueue.poll(TaskQueue.java:32)\n" +
        "\tat java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1066)\n" +
        "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1127)\n" +
        "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\n" +
        "\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n" +
        "\tat java.lang.Thread.run(Thread.java:745)\n";

    ArrayList<String> otherParagraphs = new ArrayList<>();
    otherParagraphs.add("   Locked ownable synchronizers:\n" +
                            "\t- None");
    ThdStack thdStack = parser.parse(p1, otherParagraphs.listIterator());
    System.out.println(thdStack);
  }
}