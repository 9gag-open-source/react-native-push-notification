package com.dieam.reactnativepushnotification.modules;

import android.content.Intent;

import java.util.ArrayList;

public class RNPushNotificationDelegate {
    private static SharedBuffer sSharedBuffer;

    public static synchronized SharedBuffer getSharedBuffer() {
        if (sSharedBuffer == null) {
            sSharedBuffer = new SharedBuffer();
        }
        return sSharedBuffer;
    }

    public void handleIntent(Intent intent) {
        getSharedBuffer().enqueue(intent);
    }

    public static final class SharedBuffer {
        private ArrayList<Intent> mBufferItems = new ArrayList<Intent>();

        public synchronized Intent pop() {
            return mBufferItems.size() == 0 ? null : mBufferItems.remove(0);
        }

        public synchronized void enqueue(Intent intent) {
            mBufferItems.add(intent);
        }
    }

}
