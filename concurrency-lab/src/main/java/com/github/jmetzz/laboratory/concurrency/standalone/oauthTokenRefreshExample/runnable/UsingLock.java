package com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.runnable;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.model.OAuthToken;
import com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.util.TokenUtil;

/**
 * Created by jean on 21/03/2017.
 */
public class UsingLock implements Runnable {
    private final String name;

    private static OAuthToken token;

    private static final Lock lock = new ReentrantLock();

    static {
        /* make sure there is a valid token when the class is loaded */
        try {
            token = TokenUtil.requestSysToken();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public UsingLock(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (token != null && !token.isValid())
            token = getToken();

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private OAuthToken getToken() {

        lock.lock();
        if (token == null) {
            System.out.println("\tREQUEST token for " + name);
            try {
                token = TokenUtil.requestSysToken();
            } catch (IOException io) {
                lock.unlock(); // safe unlock in case of problems
                io.printStackTrace();
            }
            System.out.println("\tREQUEST complete for " + name);
        } else if (!token.isValid()) {
            System.out.println("\tREFRESH token for " + name);
            try {
                token = TokenUtil.refreshSysToken(token);
            } catch (IOException io) {
                lock.unlock(); // safe unlock in case of problems
                io.printStackTrace();
            }
            System.out.println("\tREFRESH complete for " + name);
        }
        lock.unlock();

        return token;
    }
}