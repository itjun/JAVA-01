package homework2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 获取 Hello 这个类
        Class clazz = new HelloClassLoader().findClass("Hello");
        // 获取 hello 方法
        Method method = clazz.getDeclaredMethod("hello");
        // 调用 hello 方法
        method.invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) {
        File file = new File("Hello.xlass");
        byte[] data = new byte[(int) file.length()];
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            inputStream.readFully(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        decode(data);
        return defineClass(name, data, 0, data.length);
    }

    private void decode(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (255 - data[i]);
        }
    }
}
