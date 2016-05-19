package com.lx.learnOkio;

import com.lx.util.Log;
import okio.*;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by douhua on 5/18/16.
 */
public class FileOp {
    public void readFile(String fullPath) {
        File f = new File(fullPath);
        if (!f.exists()) return;

        File sinkF = new File("/tmp/okio");

        try {
            if (!sinkF.exists()) sinkF.createNewFile();
            BufferedSource bsource = Okio.buffer(Okio.source(f));
//            bSourceOp(bsource);
            BufferedSink bsink = Okio.buffer(Okio.sink(sinkF));

            byte[] b = new byte[8 * 1024];
            bsource.read(b);

            bSinkOp(bsink);
//            bsink.write(b);
//            bsink.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bSourceOp(BufferedSource bs) throws IOException {
        Buffer buffer = bs.buffer();
        Log.e("size of buffer:" + buffer.size());

        //每读一个就弹出

        Options options = Options.of(
                ByteString.encodeUtf8("-0"),
                ByteString.encodeUtf8("adb"),
                ByteString.encodeUtf8("中文")
        );
        String line;
        while ((line = bs.readUtf8Line()) != null) {
            Log.e(line);
        }
        Log.e("read line: " + bs.readUtf8Line());
//        Log.e("select: " + bs.select(options));
//        Log.e("read byteString:" + bs.readByteString());
//        Log.e("read DLongLe:" + bs.readDecimalLong());
//        Log.e("read byte:" + bs.readByte());
//        Log.e("read HexLongLe:" + bs.readHexadecimalUnsignedLong());
//        Log.e("read short:" + bs.readShort());
//        Log.e("read shortLe:" + bs.readShortLe());
//        Log.e("read int:" + bs.readInt());
//        Log.e("read intLe:" + bs.readIntLe());
//        Log.e("read Long:" + bs.readLong());
//        Log.e("read LongLe:" + bs.readLongLe());
//
//        bs.readByte();
//        Log.e("read utf8:" + bs.readUtf8());
//        Log.e("read String:" + bs.readString(Charset.forName("US-ASCII")));
    }

    private void bSinkOp(BufferedSink bs) throws IOException {
        byte[] bns = new byte[]{0x66, 0x67, 0x68, 0x65};
        bs.writeUtf8("abcd");
        bs.writeInt(77);
        bs.writeLong(29L);
        bs.write(bns);

        bs.flush();
    }

    private void bufferOp() throws IOException {
        Buffer buffer = new Buffer();
        Log.e("size of buffer: " + buffer.size()); //0

//        buffer.require(10); //crash
        buffer.writeInt(0x4f4a4441);
        buffer.writeUtf8("helo wold");
        buffer.write(ByteString.encodeUtf8("\n"));
        Log.e("size of buffer: " + buffer.size()); //0

        FileOutputStream fileOutputStream = new FileOutputStream("/tmp/okio");
        buffer.copyTo(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        Log.e("size of buffer: " + buffer.size()); //0

        Log.e(buffer.md5().toString());
        Log.e(buffer.sha1().toString());
        Log.e(buffer.sha256().toString());

        buffer.readByte();
        Log.e("size of buffer: " + buffer.size()); //0
        buffer.readLongLe();
        Log.e("size of buffer: " + buffer.size()); //0
    }

    public static void test() {
        FileOp fop = new FileOp();
//        fop.readFile("/tmp/adb.log");
        try {
            fop.bufferOp();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
