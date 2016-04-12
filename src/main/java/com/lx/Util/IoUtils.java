package com.lx.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作文件的封装类
 */
public class IoUtils {
    private static final String TESTPATh = "/Users/douhua/lx/github";
    private static final String NUM_FILE = "/tmp/100num";

    /**
     * 获取目录下的所有文件, 包括子目录和子目录下的所有文件
     *
     * @param dirname
     * @return
     */
    public static DirInfo getAllFilesInDir(String dirname) {
        DirInfo result = traverseDir(new File(dirname));
        Common.print(result.toString());
        return result;
    }

    public static void test() {
//        testRandomAccessFile();
        testByteBuffer();
    }

    /**
     * 代表一个目录的所有信息
     */
    private static class DirInfo {
        private String mDirName;
        List<File> dirs = new ArrayList<>();
        List<File> files = new ArrayList<>();

        public List<File> getSubDirs() {
            return new ArrayList<>(dirs);
        }

        public List<File> getSubFiles() {
            return new ArrayList<>(files);
        }

        public void addAll(DirInfo info) {
            if (info == null) return;
            dirs.addAll(info.dirs);
            files.addAll(info.files);
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Dirs:\n");
            for (File dir : dirs) {
                builder.append("\t - ")
                        .append(dir.getName())
                        .append("\n");
            }
            builder.append("Files:\n");
            for (File dir : files) {
                builder.append("\t - ")
                        .append(dir.getName())
                        .append("\n");
            }

            return builder.toString();
        }
    }

    /**
     * 遍历一个目录, 获取这个目录的所有内容
     *
     * @param dir
     * @return
     */
    public static DirInfo traverseDir(File dir) {
        if (!dir.exists()) return null;
        if (!dir.isDirectory()) return null;

        DirInfo dirInfo = new DirInfo();
        File[] contents = dir.listFiles();
        for (File child : contents) {
            if (child.isFile()) {
                dirInfo.files.add(child);
            } else {
                dirInfo.dirs.add(child);
                dirInfo.addAll(traverseDir(child));
            }
        }

        return dirInfo;
    }

    /**
     * 查找文件中符合条件的行
     *
     * @param regex: 正则表达式
     * @return
     */
    public static List<String> grep(String file, String regex) {
        return grep(new File(file), regex);
    }

    public static List<String> grep(File file, String regex) {
        if (!file.exists()) return null;

        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Pattern pattern = Pattern.compile(regex);
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    result.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void testStreamsAndTers() {
        try {
            //write类api
            File file = new File("/tmp/dos");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            dos.writeInt(10);
            dos.writeLong(100000L);
            dos.writeFloat(10.01f);
            dos.writeDouble(10.02);
            dos.writeByte(0xcd);
            dos.writeBoolean(true);
            dos.writeShort(2);
            dos.flush();

            DataInputStream dis = new DataInputStream(new FileInputStream("/tmp/dos"));
            System.out.printf("%d, %d, %f, %f, %X, %b, %s\n",
                    dis.readInt(),
                    dis.readLong(),
                    dis.readFloat(),
                    dis.readDouble(),
                    dis.readByte(),
                    dis.readBoolean(),
                    dis.readShort());
            dis.close();

            //Ters 和 Stream 互换
            Reader fr = new InputStreamReader(new DataInputStream(new FileInputStream("/tmp/dos")));
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.printf("%c", ch);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试随机文件
     */
    public static void testRandomAccessFile() {
        generateNum();
        try {
            RandomAccessFile raf = new RandomAccessFile(NUM_FILE, "rw");
            raf.seek(100);
            byte[] b = new byte[100];
            raf.read(b, 0, 99);
            String s = new String(b);
            Common.print(s);
            raf.skipBytes(200);
            raf.write("hi dear god".getBytes());
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试filechannel的机制
     */
    public static void testFileChannel() {

    }

    /**
     * 测试bytebuffer
     */
    public static void testByteBuffer() {
        char[] testStr = "Hi, I am zhengchao xu.".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(testStr.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(testStr);

        System.out.println(new String(bb.array()));

        char c1, c2;
        for (int i = 0; i < 2; i++) {
            cb.position(0); //重设postion为0
            while (cb.hasRemaining()) {
                cb.mark(); //将mark设置为position的位置
                c1 = cb.get(); //每次get都会将postion的值加1
                c2 = cb.get();
                cb.reset(); //将position设为之前的mark位置
                cb.put(c2).put(c1); //每次put都将position的值+1
            }
            System.out.println(new String(bb.array()));
        }
    }

    private static void printBB(ByteBuffer bb) {
        Common.print("position:" + bb.position()
                + "; limit:" + bb.limit()
                + "; cap:" + bb.capacity() + "\n");
    }

    /**
     * 生成一百万个整数, 并写入文件
     */
    public static void generateNum() {
        File file = new File(NUM_FILE);
        if (file.exists()) return;

        Random random = new Random(10000);
        StringBuilder sb = new StringBuilder(101);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 1; i < 10000001; i++) {
                int n = random.nextInt(10000);
                sb.append(n).append(",");
                if (i % 100 == 0) {
                    sb.replace(sb.length() - 1, sb.length(), "\n");
                    bw.write(sb.toString());
                    sb = new StringBuilder(101);
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
