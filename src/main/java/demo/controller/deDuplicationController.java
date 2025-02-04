package demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

//1. 接收一个 JSON 格式的字符串数组。
//2. 对数组进行校验：
//确保数组中仅包含字符串，并且长度不超过 500 个元素。
//字符串长度不得超过 100 个字符。
//3. 进行数据处理：
//去重数组中的重复字符串。
//按字母顺序对字符串排序。
//4. 返回处理后的数组。
//5. 如果输入数据不符合要求，返回相关的错误信息。

@RestController
@RequestMapping("/api")
public class deDuplicationController {
    @PostMapping("/dedup")
    public ResponseEntity<?> dedup(@RequestBody RequestData requestData) {
        System.out.println("orii"+requestData);
        List<String> array=requestData.getArray();

        if(array==null){
            System.out.println("array is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "array is null"));
        }

        if (array.size()>500){
            System.out.println("array size > 500");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "array size > 500"));
        }

        List<String> validStrings = new ArrayList<>();
        for(Object s:array){
            if(!(s instanceof String)){
                System.out.println("error:"+s+" is not a String");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "All elements must be String"));
            }
            String str = (String) s;
            if(str.length()>100){
                System.out.println("length > 100");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "length > 100"));
            }
            validStrings.add(str);
        }
        System.out.println("arr"+validStrings);
        Set<String> set=new HashSet<>(validStrings);
        List<String> list=new ArrayList<>(set);
        Collections.sort(list);

        return ResponseEntity.ok(Collections.singletonMap("result", list));

    }


    public static class RequestData {
        private List<String> array;
        public List<String> getArray(){
            return array;
        }
        public void setArray(List<String> array){
            this.array=array;
        }
    }
}
