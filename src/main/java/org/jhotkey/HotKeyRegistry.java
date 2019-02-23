package org.jhotkey;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotKeyRegistry {
    private static HotKey currentHotKey = new HotKey();
    private static NativeKeyListener nativeKeyListener = new RegistryNativeKeyListener();
    private static Map<HotKey, List<HotKeyListener>> registeredListeners = new HashMap<>();

    static {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            ex.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(nativeKeyListener);
    }

    private HotKeyRegistry() {
    }

    public static void registerHotKeyListener(HotKey hotKey, HotKeyListener hotKeyListener) {
        if (!registeredListeners.containsKey(hotKey)) {
            registeredListeners.put(hotKey, new ArrayList<>());
        }

        List<HotKeyListener> listeners = registeredListeners.get(hotKey);
        listeners.add(hotKeyListener);
    }

    public static void removeHotKeyListener(HotKey hotKey, HotKeyListener hotKeyListener) {
        if (registeredListeners.containsKey(hotKey)) {
            List<HotKeyListener> listeners = registeredListeners.get(hotKey);

            if (listeners.contains(hotKeyListener)) {
                listeners.remove(hotKeyListener);
            }
        }
    }

    public static List<HotKeyListener> getRegisteredHotKeyListeners(HotKey hotKey) {
        return registeredListeners.get(hotKey);
    }

    public static NativeKeyListener getNativeKeyListener() {
        return nativeKeyListener;
    }

    public static HotKey getCurrentHotKey() {
        return currentHotKey;
    }

    public static class RegistryNativeKeyListener implements NativeKeyListener {
        @Override
        public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
            currentHotKey.setModifiers(nativeKeyEvent.getModifiers());

            if (nativeKeyEvent.getKeyCode() != HotKey.VC_SHIFT
                    && nativeKeyEvent.getKeyCode() != HotKey.VC_CONTROL
                    && nativeKeyEvent.getKeyCode() != HotKey.VC_ALT
                    && nativeKeyEvent.getKeyCode() != HotKey.VC_META
                    && nativeKeyEvent.getKeyCode() != HotKey.VC_NUM_LOCK
                    && nativeKeyEvent.getKeyCode() != HotKey.VC_SCROLL_LOCK
                    && nativeKeyEvent.getKeyCode() != HotKey.VC_CAPS_LOCK) {
                currentHotKey.addKey(nativeKeyEvent.getKeyCode());
            }

            Iterator<HotKey> keysIterator = registeredListeners.keySet().iterator();
            while (keysIterator.hasNext()) {
                HotKey hotKey = keysIterator.next();

                if (hotKey.isSimilarTo(currentHotKey)) {
                    List<HotKeyListener> listeners = registeredListeners.get(hotKey);

                    Iterator<HotKeyListener> iterator = listeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().hotKeyFired();
                    }
                }
            }
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
            currentHotKey.setModifiers(nativeKeyEvent.getModifiers());
            currentHotKey.removeKey(nativeKeyEvent.getKeyCode());
        }
    }
}
