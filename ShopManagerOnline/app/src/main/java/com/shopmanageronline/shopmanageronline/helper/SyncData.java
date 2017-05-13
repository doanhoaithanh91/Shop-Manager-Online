package com.shopmanageronline.shopmanageronline.helper;

import com.shopmanageronline.shopmanageronline.entity.Provider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by ThanhDH - LA on 5/13/2017.
 */

public class SyncData {
    public static void serializeDataOut(List<Provider> providers) throws IOException {
        String fileName = "Test.txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(providers);
        oos.close();
    }

    public static List<Provider> serializeDataIn() throws IOException, ClassNotFoundException {
        String fileName= "Test.txt";
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        List<Provider> providers= (List<Provider>) ois.readObject();
        ois.close();
        return providers;
    }
}
