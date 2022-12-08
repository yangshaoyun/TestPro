package com.polytope.testpro.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileUtils {
    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        HashMap<String, String> map = null;
        return FormetFileSize(blockSize, sizeType);
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    public static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case 1://b
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case 2://kb
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case 3://mb
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case 4://gb
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    // 根据路径生成文件
    public static void createFile(String path) {
        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileContent(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        }
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static void addLog(String filePath, String strTitle) {
        try {
            File sdCardDir = Environment.getExternalStorageDirectory();
            String path = sdCardDir + filePath;
            File saveFile = new File(path);
            File fileParent = saveFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss SSS");
            Date curDate = new Date(System.currentTimeMillis());
            String strDate = formatter.format(curDate);
            String str = strTitle + ", " + strDate + "\n";
            FileOutputStream outStream = new FileOutputStream(saveFile,
                    true);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static synchronized void addLogToFile(String filePath, String content) {
        try {
            File sdCardDir = Environment.getExternalStorageDirectory();
            String path = sdCardDir + "/" + filePath;
            File saveFile = new File(path);
            File fileParent = saveFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());
            String strDate = formatter.format(curDate);
            String str = content + " " + strDate + "\n";
            FileOutputStream outStream = new FileOutputStream(saveFile,
                    true);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void copyFile(InputStream is, String dirPath) {
        File toFile = new File(dirPath);
        copyFile(is, toFile, true);
    }

    private static boolean copyFile(InputStream src, File des, boolean reWrite) {
        if (!des.getParentFile().exists()) {
            des.getParentFile().mkdirs();
        }
        if (des.exists()) {
            if (reWrite) {
                des.delete();
            } else {
                return false;
            }
        }
        try {
            InputStream fis = src;
            FileOutputStream fos = new FileOutputStream(des);
            byte[] bytes = new byte[1024];
            int readLength = -1;
            while ((readLength = fis.read(bytes)) > 0) {
                fos.write(bytes, 0, readLength);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isFileExist(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            return true;
        }
        return false;
    }
}
