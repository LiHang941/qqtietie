package xyz.lihang.qqtietietie.utils;

import java.lang.reflect.Field;

/**
 * Created by LiHang on 2017/8/14.
 */
public class BeanUtils {

    /**
     *  复制对象
     * @param newobj
     * @param object
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void copyProperty(Object newobj,Object object) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        //新的class
        Class newClass = newobj.getClass();
        //老的class
        Class oldClass = object.getClass();
        //该类所有的属性
        Field[] newFields = newClass.getDeclaredFields();
        //新的属性
        Field newField = null;
        //老的属性
        Field oldField = null;
        for(Field f : newFields){
            //类中的属性名称
            String fieldName = f.getName();
            //通过属性名称获取属性
            newField = newClass.getDeclaredField(fieldName);
            //获取属性的值时需要设置为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
            //值为 false 则指示反射的对象应该实施 Java 语言访问检查。
            newField.setAccessible(true);
            //根据属性获取对象上的值
            Object newObject = newField.get(newobj);
            //过滤空的属性或者一些默认值
            if (isContinue(newObject)) {
                continue;
            }
            oldField = oldClass.getDeclaredField(fieldName);
            oldField.setAccessible(true);
            oldField.set(object, newObject);
        }
    }

    /**
     *  是否跳出本次循环
     * @author 2014-11-6 上午11:37:22
     * @param object
     * @return true 是 有null或者默认值
     *         false 否 有默认值
     */
    private static boolean isContinue(Object object){
        if (object == null || "".equals(object)) {
            return true;
        }
        return false;
    }


    /**
     *  复制对象
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object copyObject(Object obj){
        try {
            Object object = obj.getClass().newInstance();
            BeanUtils.copyProperty(obj,object);
            return object;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取属性值
     * @param obj
     * @param fieldName
     * @return
     */
    public static float getByField (Object obj,String fieldName){
        try {
            Field x = obj.getClass().getDeclaredField(fieldName);
            x.setAccessible(true);
            return x.getFloat(obj);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置属性值
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setByField (Object obj,String fieldName,float value){
        try {
            Field x = obj.getClass().getDeclaredField(fieldName);
            x.setAccessible(true);
            x.setFloat(obj,value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理下一个帖纸的显示
     * @param object
     * @return
     * @throws Exception
     */
    public Object objectNextHandle (Object object)throws Exception{
        Field x = object.getClass().getDeclaredField("x");
        Field height = object.getClass().getDeclaredField("height");
        Field width = object.getClass().getDeclaredField("width");
        Field y = object.getClass().getDeclaredField("y");
        x.setAccessible(true);
        y.setAccessible(true);
        x.setFloat(object,x.getFloat(object));
        y.setFloat(object,y.getFloat(object) + height.getFloat(object));
        return  object;
    }


}
