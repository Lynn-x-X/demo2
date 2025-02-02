package demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class arraySortController {
    @PostMapping("/sort")
    public ResponseEntity<?> sortArray(@RequestBody RequestData requestData) {
        System.out.println(requestData);

        List<Integer> array=requestData.getArray();

        if(array==null){
            System.out.println("array is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error","Array cannot be null"));
        }
        if( array.size()>1000){
            System.out.println("array is too long");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error","Array cannot be longer than 1000"));
        }
        for(Object element:array){
            if(!(element instanceof Integer)){
                System.out.println("element is not an integer");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error","Array must be an integer"));
            }
        }

        System.out.println(array);
        Collections.sort(array);
        return ResponseEntity.ok(Collections.singletonMap("SortedArray",array));

    }




    public static class RequestData {
        private List<Integer> array;
        public List<Integer> getArray() {
            return array;
        }
        public void setArray(List<Integer> array) {
            this.array = array;
        }
    }
}