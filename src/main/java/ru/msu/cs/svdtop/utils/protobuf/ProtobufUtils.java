package ru.msu.cs.svdtop.utils.protobuf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class ProtobufUtils {

    public static CodedInputStream getInputStream(File file) {
        Validate.isTrue(file.isFile(), "Must be file");
        CodedInputStream stream;
        try {
            stream = CodedInputStream.newInstance(new BufferedInputStream(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

    public static CodedOutputStream getOutputStream(File file) {
        Validate.isTrue(file.isFile(), "Must be file");
        CodedOutputStream stream;
        try {
            stream = CodedOutputStream.newInstance(new BufferedOutputStream(new FileOutputStream(file)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

}
