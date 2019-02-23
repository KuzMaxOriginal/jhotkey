package org.jhotkey;

public class HotKeyListenerImplementation implements HotKeyListener {
    private int firedCount;

    public HotKeyListenerImplementation() {
        firedCount = 0;
    }

    public int getFiredCount() {
        return firedCount;
    }

    @Override
    public void hotKeyFired() {
        firedCount++;
    }
}
