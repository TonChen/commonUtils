package com.genaral.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具
 *
 * @author Administrator
 */
public class SerializeUtils {

    /**
     * object 转 byte
     *
     * @param o
     * @return
     */
    public static byte[] serialize(Object o) {
        if (o == null) {
            return null;
        }
        ;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outo = new ObjectOutputStream(out);
            outo.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * byte 转 object
     *
     * @param b
     * @return
     */
    public static Object deserialize(byte[] b) {
        if (b == null) {
            return null;
        }
        try {
            ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(b));
            try {
                return oin.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
