class Type1{
    String test = "this is a test";
}

class Type2{
    String test = "this is also a test";
}

class Open1A{
    Type1 example1 = new Type1();
    Type2 example2 = new Type2();
}

// Tester Library v.3.0
// -----------------------------------
// Tests defined in the class: Open1A:
// ---------------------------
// Open1A:
// ---------------
// new Open1A:1(
//  this.example1 = new Type1:2(
//   this.test = "this is a test")
//  this.example2 = new Type2:3(
//   this.test = "this is also a test"))
// ---------------
// No test methods found.