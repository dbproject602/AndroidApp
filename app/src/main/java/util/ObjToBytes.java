package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjToBytes {
    public static byte[] objtobytes(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj); //
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
    //序列化
    public static String serializeToString(Object obj) throws Exception{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(obj);
        String str = byteOut.toString("ISO-8859-1");//此处只能是ISO-8859-1,但是不会影响中文使用
        return str;
    }
    public static Object bytestoobj(byte[] bytes) throws IOException {
        ByteArrayInputStream byteinput = new ByteArrayInputStream(bytes);
        ObjectInputStream bitmap = new ObjectInputStream(byteinput);
        Object result = null;
        try {
            result = bitmap.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
