package xyz.lihang.qqtietietie.utils.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import xyz.lihang.qqtietietie.Constant;
import xyz.lihang.qqtietietie.utils.BeanUtils;
import xyz.lihang.qqtietietie.utils.XSharedPreferencesUtil;

public class ParesFactory {
	

	public static ParseModel parseProperty (String str){
		ParseModel parseModel = new ParseModel();
		try{
			float parse = Float.parseFloat(str);
			parseModel.setAttr(parse/100);
			parseModel.setWay(Way.DIV.getFlag());
		}catch(NumberFormatException e){
			String replaceStr = null;
			if(str.contains(Way.X.getName())){
				parseModel.setWay(Way.X.getFlag());
				replaceStr = str.replaceAll(Way.X.getName(), "");
			}
			if(str.contains(Way.Y.getName())){
				parseModel.setWay(Way.Y.getFlag());
				replaceStr = str.replaceAll(Way.Y.getName(), "");
			}
			float parse = Float.parseFloat(replaceStr);
			parseModel.setAttr(parse/100);
		}
		return parseModel;
	}

	public static Face parseParseDto(ParseDto parseDto){
		if(parseDto == null || parseDto.getxProperty()==null || parseDto.getyProperty() == null){
			throw new NullPointerException();
		}
		Face face = new Face ();
		face.setxParseModel(parseProperty(parseDto.getxProperty()));
		face.setyParseModel(parseProperty(parseDto.getyProperty()));
		return face;
	}

	public static Stack<Object> getFaceList (Object object, List<Face> faces){
		Stack<Object> stack = new Stack<>();

		for(Face face :faces){
			Object topObject = object;
			//复制obj
			object = BeanUtils.copyObject(object);
			parseModel(object, face, topObject);
			stack.push(object);
		}
		return stack;
	}

	private static void parseModel(Object object, Face face, Object topObject) {
		ParseModel xparseModel = face.getxParseModel();
		ParseModel yparseModel = face.getyParseModel();
		//X轴
		if(Way.DIV.getFlag() == xparseModel.getWay() ){
            BeanUtils.setByField(object, Attr.X.getName(),xparseModel.getAttr());
        } else if(Way.X.getFlag() == xparseModel.getWay() ){
            //根据上一个obj来设置值
            float value = BeanUtils.getByField(topObject,Attr.X.getName()) + xparseModel.getAttr();
            BeanUtils.setByField(object,Attr.X.getName(),value);
        }else if(Way.Y.getFlag() == xparseModel.getWay() ){
            float value = BeanUtils.getByField(topObject,Attr.Y.getName()) + xparseModel.getAttr();
            BeanUtils.setByField(object,Attr.X.getName(),value);
        }
        //Y轴
		if(Way.DIV.getFlag() == yparseModel.getWay() ){
			BeanUtils.setByField(object, Attr.Y.getName(),yparseModel.getAttr());
		} else if(Way.X.getFlag() == yparseModel.getWay() ){
			//根据上一个obj来设置值
			float value = BeanUtils.getByField(topObject,Attr.X.getName()) + yparseModel.getAttr();
			BeanUtils.setByField(object,Attr.Y.getName(),value);
		}else if(Way.Y.getFlag() == yparseModel.getWay() ){
			float value = BeanUtils.getByField(topObject,Attr.Y.getName()) + yparseModel.getAttr();
			BeanUtils.setByField(object,Attr.Y.getName(),value);
		}
	}
	public static List<Face> defaultFaces(){
		return new ArrayList<>(Arrays.asList(
				parseParseDto(new ParseDto().setxProperty("x+5").setyProperty("y+0")),
				parseParseDto(new ParseDto().setxProperty("x+5").setyProperty("y+0"))
		));
	}

	public static List<Face> getFaceList(){
		List<Face> faces = new ArrayList<>();
		String faceJson = XSharedPreferencesUtil.getXSharedPreferences(Constant.facesName).getString(Constant.facesName, null);
			try {
				List<Face> temp = JSON.parseArray(faceJson, Face.class);
				faces.addAll(temp);
			}catch (Exception e){
				e.printStackTrace();
			}
		return faces;
	}

	public static void main(String[] args) {
		TestObject testObject = new TestObject();
		testObject.setHeight(15);
		testObject.setWidth(20);
		testObject.setX(0);
		testObject.setY(0);
		List<Face> faces = new ArrayList<>(Arrays.asList(
				parseParseDto(new ParseDto().setxProperty("x+20.5").setyProperty("y+20.5")),
				parseParseDto(new ParseDto().setxProperty("x+0").setyProperty("30.5"))
		));
		Stack<Object> faceList = getFaceList(testObject, faces);
		for(Object o:faceList){
			System.out.println("args = [" + o + "]");
		}
		//保存faces
		String json = JSON.toJSONString(faces);
		System.out.println(JSON.parseArray(json,Face.class));
		System.out.println(json);
	}
}
