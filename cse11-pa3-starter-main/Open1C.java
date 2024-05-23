class Test2 {
    int method1(int parameter){
        return parameter;
    }

    int method2(int parameter){
        return parameter;
    }
}

class Open1C{
    Test2 example1 = new Test2();
    int call1 = example1.method1(1);
    int call2 = example1.method2(2);
}

// Tester Library v.3.0
// -----------------------------------
// Tests defined in the class: Open1C:
// ---------------------------
// Open1C:
// ---------------
// new Open1C:1(
//  this.example1 = new Test2:2()
//  this.call1 = 1
//  this.call2 = 2)
// ---------------
// No test methods found.