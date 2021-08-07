package cn.wolfcode.wolf2w;

import lombok.Getter;
import lombok.Setter;

class Test {
    public static void main(String[] args) {
        System.out.println(new SubClass().getId());
    }
}

@Getter
@Setter
class SuperClass {
    private Long id = 2L;
}

@Setter
@Getter
class SubClass extends SuperClass {

}