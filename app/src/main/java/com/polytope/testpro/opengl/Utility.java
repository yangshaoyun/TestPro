package com.polytope.testpro.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author: 雪豹高科
 * @time: 2021-12-20
 */
public class Utility {
    //创建顶点数据缓冲
    public static FloatBuffer createFloatBuffer(float data[]) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(data.length * 4); //创建数据缓冲
        vbb.order(ByteOrder.nativeOrder()); //设置字节顺序
        FloatBuffer outBuffer = vbb.asFloatBuffer(); //转换为float型缓冲
        outBuffer.put(data).position(0);//向缓冲中放入顶点坐标数据并设置缓冲区的起始位置
        return outBuffer;
    }
}
