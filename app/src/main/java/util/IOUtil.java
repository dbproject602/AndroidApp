package util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOUtil {
    public static void writeFileDataTobytes(String filename, Object obj, Context context) throws Exception{
            FileOutputStream fout = context.openFileOutput(filename, Context.MODE_PRIVATE);//获得FileOutputStream
            byte[]  bytes = util.ObjToBytes.objtobytes(obj);;
            fout.write(bytes);//将byte数组写入文件
            fout.close();//关闭文件输出流
    }

    public static Object readFileDataToObj(String fileName,Context context) throws Exception{
            FileInputStream fin = context.openFileInput(fileName);
            int lenght = fin.available();
            byte[] buffer = new byte[lenght];
            fin.read(buffer);
            Object result = null;
            result = ObjToBytes.bytestoobj(buffer);
            return result;
    }

}


