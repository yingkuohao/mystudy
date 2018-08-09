package com.mylearn.util.json;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.util.*;

public class JsonUtil {



    /**
     * ??????????????
     *
     * @param object bean ????
     * @return
     * @throws Exception
     */
    public static String objectToJson(Object object) throws Exception {
        //??????????null?????
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (value == null) {// ????????????
                    return true;
                }
                return false;
            }
        });
        JSONObject jo = JSONObject.fromObject(object, jsonConfig);
        return jo.toString();
    }

    /**
     * ??json??????????
     *
     * @param paramJson json??
     * @param c         class????
     * @param <T>       ??????????
     * @return
     * @throws Exception
     */
    public static <T> T jsonToObject(String paramJson, Class<T> c) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(paramJson);
        Object object = JSONObject.toBean(jsonObject, c);
        return (T) object;
    }

    public static Object[] jsonToObjectArray(String paramJson, Class c) throws Exception {
        JSONArray jsonArr = JSONArray.fromObject(paramJson);

        Object[] obj = new Object[jsonArr.size()];
        for (int j = 0; j < jsonArr.size(); j++) {
            JSONObject jsonObject = jsonArr.getJSONObject(j);
            obj[j] = JSONObject.toBean(jsonObject, c);
        }
        return obj;
    }

    /**
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String collection2Json(Collection<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     *
     * @param json
     * @param c
     * @param <T>
     * @return
     */
    public static <T> Collection<T> json2Collection(String json, Class<T> c) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        Collection collection = JSONArray.toCollection(jsonArray, c);
        return (Collection<T>) collection;
    }


    /**
     * map????json
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> String map2Json(Map<K,V> map) {
        JSONObject jsonObject = JSONObject.fromObject(map);
          return jsonObject.toString();
      }

    /**
     * json????map
     * @param json
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> json2Map(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        Map<K,V> map = new HashMap<K,V>();
          for(Iterator iter = jsonObject.keys(); iter.hasNext();){
              K key = (K)iter.next();
              map.put(key, (V) jsonObject.get(key));
          }
        return map;
    }


    public static void main(String args[]) {
        User user = new User("zhangsan", "boy", "20");
        try {
            //1.object ????json
            String userString = JSONObject.fromObject(user).toString();
            System.out.println("userString = " + userString);
            //2. json??????????
            String json = new String("{\"age\":\"20\",\"name\":\"zhangsan\",\"sex\":\"boy\"}");
            User user1 = JsonUtil.jsonToObject(json, User.class);
            System.out.println("user1.toString = " + user1.toString());
            //3. list????string
            List<User> lst = new ArrayList<User>();
            lst.add(user);
            lst.add(user1);
            JSONArray jsonArray = JSONArray.fromObject(lst);
            System.out.println("jsonList1 = " + jsonArray.toString());
            String jsonList2 = JsonUtil.collection2Json(lst);
            System.out.println("jsonList2 = " + jsonList2);
            //4. string????list
            JSONArray jsonArray1 = JSONArray.fromObject(jsonList2);
            List<User> userList = (List<User>) JSONArray.toCollection(jsonArray1, User.class);
            System.out.println(("userList = ") + Joiner.on(",").join(userList));
              //5. map??String
            ImmutableMap<String, Integer> mapImmutableInt = ImmutableMap.of("1", 1, "2", 2, "3", 3);
              Map<String,Integer> map=mapImmutableInt;
            String jsonMap =  JSONObject.fromObject(map).toString();
            System.out.println("jsonMap = "+ jsonMap);
            //6. String ?map
            JSONObject jsonObject = JSONObject.fromObject(jsonMap);
            Map<String,Integer> map1=jsonObject;
            System.out.println("jsonObject = " + map1.toString());

            Map<String,Integer> map2 = JsonUtil.json2Map(jsonMap);
            System.out.println("map2 = " + map2.toString());
            //7. array ?json
            int[] ints = {10, 9, 30, 8, 1};
            JSONArray jsonArray2 = JSONArray.fromObject(ints);
            System.out.println("jsonArray2 = " + jsonArray2);
            //8.json?array
            Object jsonArray3 = JSONArray.toArray(jsonArray2);
            System.out.println("jsonArray3 = " + jsonArray3);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}

