package demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class deDuplicationController {
    @PostMapping("/dedup")
    public ResponseEntity<?> dedup(@RequestBody RequestData requestData) {
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

        for(String s:array){
            if(s.length()>100){
                System.out.println("length > 100");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "length > 100"));
            }
        }
        System.out.println("arr"+array);
        Set<String> set=new HashSet<>(array);
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
