package javatester;

import org.testng.annotations.Test;

public class Topic_09_Array {

    public static void main(String[] args) {

        String[] Student_Name = new String[5];
        Student_Name[1] = "Nguyen Van A";
        Student_Name[2] = "Tran Thi B";
        Student_Name[3] = "Phan Van C";
        Student_Name[4] = "Dang Thi Vinh";
        Student_Name[0] = "PLalisa";

        // chi goi dc static qua day

        for (int i = 0; i < Student_Name.length; i++) {
            System.out.println(Student_Name[i]);

        }
    }

        private String fullName; // bat buoc phai dung bien private
        public String getFullName() {
            return fullName;
        }
        public void setFullName(String newValue) { // neu bien global va local cung ten co the dung this.
            fullName = newValue;
        }

        @Test
        public void test_getset() {
            setFullName("to lan anh");
            System.out.println(getFullName());
        }

}

// tinh da hinh - ham cung ten