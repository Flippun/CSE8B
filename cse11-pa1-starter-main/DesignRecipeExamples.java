import tester.*;

class DesignRecipeExamples {

    // calculate the perimeter of a rectangle, assuming width and height are integers
    int perimeter(int width, int height){
        return (2 * width) + (2 * height);
    }

    // calculates the difference in area between a rectangle and a smaller rectangle cut out of it's center, 
    // ie. the difference in area of two rectangles, assuming width1/height1 represents the bigger rectangle
    int borderArea(int width1, int height1, int width2, int height2){
        return (width1 * height1) - (width2 * height2);
    }

    // converts from inches to the length of an average banana, assuming inches is an integer
    // from www.converttobananas.com, the average length of a banana is 7inches
    int bananaConverter(int inches){
        return (inches / 7);
    }

    // combines pool laps and yards into into total yards, assuming yards and laps are an integer, 
    // pool laps are considered in short-course, or 25 yards
    int totalYards(int yards, int laps){
        return (laps * 25) + yards;
    }

    int borderArea_test1 = this.borderArea(2, 2, 1, 1); // expected 3
    int borderArea_test2 = this.borderArea(10, 10, 5, 5); // expected 75
   
    int perimeter_test1 = this.perimeter(1, 1); // expected 4
    int perimeter_test2 = this.perimeter(5, 5); // expected 20
  
    // actual answer is 1.714, this difference is caused by the use of integers isntead of doubles
    int bananaConverter_test1 = this.bananaConverter(12); // expected 1
    // actual answer is 10.292, which is fairly similar despite using integers
    int bananaConverter_test2 = this.bananaConverter(72); // expected 10

    // no such output exists because all integers will give an accurate, if nonsensical (negative lengths can't exist), output
    // if the arguments were a double, they would give an incorrect output, but doubles will cause the method not to run
    int totalYards_test1 = this.totalYards(100, 4); // expected 200
    int totalYards_test2 = this.totalYards(1650, 8); // expected 1850
    int totalYards_test3 = this.totalYards(-5, 4); // expected 95
}