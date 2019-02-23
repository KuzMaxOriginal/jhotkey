package org.jhotkey;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class HotKeyTest {
    private int mask;
    private int[] keys;
    private List<Integer> keysList;

    @BeforeEach
    void initVars() {
        mask = HotKey.SHIFT_MASK | HotKey.CTRL_MASK;
        keys =  new int[]{HotKey.VC_A, HotKey.VC_S, HotKey.VC_D, HotKey.VC_F};

        keysList = new ArrayList<>();
        keysList.add(HotKey.VC_Q);
        keysList.add(HotKey.VC_W);
        keysList.add(HotKey.VC_E);
        keysList.add(HotKey.VC_R);
        keysList.add(HotKey.VC_T);
        keysList.add(HotKey.VC_Y);
    }

    @Test
    void setsMaskAndKeys() {
        HotKey hotKey = new HotKey();

        hotKey.setModifiers(mask);
        Assertions.assertEquals(mask, hotKey.getModifiers());

        hotKey.setKeys(keysList);
        Assertions.assertEquals(keysList, hotKey.getKeys());

        hotKey.setKeys(keys);
        Assertions.assertArrayEquals(keys, hotKey.getKeys().stream().mapToInt(i -> i).toArray());
    }

    @Test
    void setsMaskAndKeysFromConstructor() {
        HotKey hotKey = new HotKey(mask, keys);

        Assertions.assertEquals(mask, hotKey.getModifiers());
        Assertions.assertArrayEquals(keys, hotKey.getKeys().stream().mapToInt(i -> i).toArray());
    }

    @Test
    void comparesAndHashesProperly() {
        HotKey hotKey = new HotKey(mask, keys);
        HotKey identicalHotKey = new HotKey(mask, keys);
        HotKey differentMaskHotKey = new HotKey(HotKey.NO_MASK, keys);
        HotKey differentKeysHotKey = new HotKey(mask, HotKey.VC_S);

        Assertions.assertEquals(hotKey, identicalHotKey);
        Assertions.assertNotEquals(hotKey, differentMaskHotKey);
        Assertions.assertNotEquals(hotKey, differentKeysHotKey);

        Assertions.assertEquals(hotKey.hashCode(), identicalHotKey.hashCode());
        Assertions.assertNotEquals(hotKey.hashCode(), differentMaskHotKey.hashCode());
        Assertions.assertNotEquals(hotKey.hashCode(), differentKeysHotKey.hashCode());
    }

    @Test
    void addsAndRemovesMask() {
        HotKey hotKey = new HotKey();

        hotKey.addModifer(HotKey.SHIFT_MASK);
        hotKey.addModifer(HotKey.CTRL_MASK);
        Assertions.assertEquals(mask, hotKey.getModifiers());

        hotKey.removeModifer(HotKey.SHIFT_MASK);
        hotKey.removeModifer(HotKey.CTRL_MASK);
        Assertions.assertEquals(HotKey.NO_MASK, hotKey.getModifiers());
    }

    @Test
    void addsAndRemovesKeys() {
        HotKey hotKey = new HotKey();

        for (int i = 0; i < keys.length; i++) {
            hotKey.addKey(keys[i]);
        }

        hotKey.addKey(keys[0]); // add a key that already exists

        Assertions.assertArrayEquals(keys, hotKey.getKeys().stream().mapToInt(i -> i).toArray());

        for (int i = 0; i < keys.length; i++) {
            hotKey.removeKey(keys[i]);
        }

        hotKey.removeKey(HotKey.VC_0); // remove a non-existing key

        Assertions.assertArrayEquals(new int[0], hotKey.getKeys().stream().mapToInt(i -> i).toArray());
    }

    @Test
    void uniqueKeysWhenSettingKeys() {
        int[] repeatedKeys = new int[]{HotKey.VC_S, HotKey.VC_S, HotKey.VC_0};
        int[] uniqueKeys = new int[]{HotKey.VC_S, HotKey.VC_0};

        HotKey hotKey = new HotKey();

        hotKey.setKeys(repeatedKeys);
        Assertions.assertArrayEquals(uniqueKeys, hotKey.getKeys().stream().mapToInt(i -> i).toArray());
    }

    @Test
    void properIsSimilarTo() {
        HotKey hotKey = new HotKey(HotKey.SHIFT_MASK | HotKey.CTRL_MASK | HotKey.ALT_MASK | HotKey.META_MASK, HotKey.VC_R);

        // simulate hot key as it'd appear in NativeKeyEvent
        HotKey hotKeyNative = new HotKey(HotKey.SHIFT_L_MASK | HotKey.CTRL_R_MASK | HotKey.ALT_R_MASK | HotKey.META_L_MASK, HotKey.VC_R);
        HotKey differentHotKey = new HotKey(HotKey.SHIFT_L_MASK, HotKey.VC_S);

        Assertions.assertTrue(hotKey.isSimilarTo(hotKeyNative));
        Assertions.assertTrue(hotKeyNative.isSimilarTo(hotKey));
        Assertions.assertFalse(hotKey.isSimilarTo(differentHotKey));
    }
}
