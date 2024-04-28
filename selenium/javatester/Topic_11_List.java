package javatester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList() {

        List<String> studentName = new ArrayList<String>();
        studentName.add("Nguyen van A");
        studentName.add("Nguyen Thi B");
        studentName.add("Tran Kieu C");

        // 3 element trong ist
        System.out.println(studentName.size());
        System.out.println(studentName.get(2));
        System.out.println(studentName.get(2));



    }
}

// tinh da hinh - ham cung ten