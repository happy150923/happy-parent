package com.happy.zkeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * @author chengxia
 * @version 2017-01-17 10:28
 */
public class ZookeeperTest {

    @Test
    public void digestGet() {
        try {
            String rst = DigestAuthenticationProvider.generateDigest("super:<password>");
            System.out.println(rst);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * input commands in console just like typing in the terminal after run "./zkCli.sh -server ip:port".
     * For instance, "create /h1 h1","set /h2 hello2","get /h1","delete /h1".
     * Type "q" to quit the process.
     * @param args
     */
    public static void main(String[] args) {
        WatcherImpl watcher = new WatcherImpl();
        try {
            Scanner scanner = new Scanner(System.in);
            String input;
            String[] cmdArgs;
            while (true) {
                try {
                    input = scanner.nextLine();
                    if (StringUtils.isBlank(input))
                        continue;
                    cmdArgs = input.split("\\s+");
                    switch (cmdArgs[0]) {
                        case "create":
                            System.out.println("Create " + watcher.create(cmdArgs[1], cmdArgs[2]));
                            break;
                        case "set":
                            System.out.println("Set " + watcher.set(cmdArgs[1], cmdArgs[2]));
                            break;
                        case "delete":
                            System.out.println("Delete!");
                            watcher.delete(cmdArgs[1]);
                            break;
                        case "q":
                            return;
                        case "get":
                            System.out.println("Get " + watcher.get(cmdArgs[1]));
                        default:
                            continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class WatcherImpl implements Watcher {

    private ZooKeeper zooKeeper;

    WatcherImpl() {
        try {
            /**
             * For node events, notify the watcher only when a node is deleted or node data changes in this way. 
             * Create and get do not count.
             */
            zooKeeper = new ZooKeeper(Constants.ZOOKEEPER_SERVERS_STR, 30000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("process " + event.toString());
    }

    public String create(String path, String val) throws Exception {
        return tryUtilConnected(() -> {
            return zooKeeper.create(path, val.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        });
    }

    public Stat set(String path, String val) throws Exception {
        return tryUtilConnected(() -> {
            return zooKeeper.setData(path, val.getBytes(), -1);
        });
    }

    public String get(String path) throws Exception {
        return tryUtilConnected(() -> {
            return new String(zooKeeper.getData(path, this, null));
        });
    }

    public void delete(String path) throws Exception {
        tryUtilConnected(() -> {
            zooKeeper.delete(path, -1);
            return null;
        });
    }

    private <T> T tryUtilConnected(Callable<T> callable) throws Exception {
        ZooKeeper.States states;
        long start = System.currentTimeMillis();
        while (true) {
            states = zooKeeper.getState();
            if (states.isConnected()) {
                System.out.println("Waited " + (System.currentTimeMillis() - start) + " milliseconds!");
                return callable.call();
            }
            if (System.currentTimeMillis() - start > 30000) {
                break;
            }
        }
        System.out.println(states.toString());
        return null;
    }
}
