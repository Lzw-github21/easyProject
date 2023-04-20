package easy.project.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import easy.project.entity.User;

import java.io.IOException;

public class JsonNodeExample {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName(null);
        user.setAge(25);

        JSONObject jsonObject = new JSONObject();
        JSONObject.parseObject("");
        JSON.parseObject("");
        String json = JSON.toJSONString(user, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.WriteMapNullValue);
        System.out.println(json);
        /**
         * {
         *   "name": "John",
         *   "age": 30,
         *   "city": "New York",
         *   "hobbies": ["reading", "running", "swimming"],
         *   "education": {
         *     "degree": "Bachelor",
         *     "major": "Computer Science"
         *   }
         * }
         */
        String jsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\",\"hobbies\":[\"reading\",\"running\",\"swimming\"],\"education\":{\"degree\":\"Bachelor\",\"major\":\"Computer Science\"}}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            // 获取属性值
            String name = jsonNode.get("name").asText();
            int age = jsonNode.get("age").asInt();
            String city = jsonNode.get("city").asText();
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("City: " + city);

            // 获取数组元素
            JsonNode hobbiesNode = jsonNode.get("hobbies");
            if (hobbiesNode.isArray()) {
                for (JsonNode hobbyNode : hobbiesNode) {
                    String hobby = hobbyNode.asText();
                    System.out.println("Hobby: " + hobby);
                }
            }

            // 获取嵌套对象属性值
            JsonNode educationNode = jsonNode.get("education");
            String degree = educationNode.get("degree").asText();
            String major = educationNode.get("major").asText();
            System.out.println("Degree: " + degree);
            System.out.println("Major: " + major);

            // 修改属性值
            ((ObjectNode) jsonNode).put("age", 31);
            System.out.println("Updated JSON: " + jsonNode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}