import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int totalNumPoints = 0;
        for (Point currPt : s.getPoints()) {
            totalNumPoints = totalNumPoints + 1;
        }
        
        return totalNumPoints;
    }

    public double getAverageLength(Shape s) {
        //(sum of all sides lengths) / (number of sides)
        //the number of sides is equal to the number of points
        double convertedNumPoints = getNumPoints(s);
        double averageLength = getPerimeter(s) / getNumPoints(s);
        
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        //the length of a side of a shape is the distance between two points of the shape)
        //we need to compare all of the sides of the shape, iterate through each side to find its length.  
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            
            if (prevPt.distance (currPt) > largestSide) {
                largestSide = currDist;
            }
            
            prevPt = currPt;
        }
        
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        //he largest X value of point in shape S, iterate through the points 
        //and compare their x components
        
        double largestX = 0.0;
        
        for (Point currPoint : s.getPoints()) {
            double currX = currPoint.getX();
            
            if (currX > largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            
            if (length > largestPerimeter) {
                largestPerimeter = length;
            }
        }
        
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        //String-type value that represents the filename contains the shape with the largest perim 
        File temp = null;    // replace this code
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double filePerimeter = getPerimeter(s);
            
            if (filePerimeter > largestPerimeter) {
                largestPerimeter = filePerimeter;
                temp = f;
            }
        }
        
        // Check if a file with a valid shape was found
        return temp != null ? temp.getName() : "No file with a valid shape found.";
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int numberOfPoints = getNumPoints (s);
        System.out.println("Number of points:" + numberOfPoints);
        
        double averageSideLength = getAverageLength(s);
        System.out.println("Average Side Length = " + averageSideLength);
        
        double largestFigSide = getLargestSide (s);
        System.out.println("Largest Side Length = " + largestFigSide);
        
        double largestXCoordinate = getLargestX (s);
        System.out.println("Largest X coordinate = " + largestXCoordinate);
        
    }
    
    public void testPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
    
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            System.out.println("Perimeter of this figure = " + length);
    
            if (length > largestPerimeter) {
                largestPerimeter = length;
            }
        }
    
        System.out.println("Largest Perimeter = " + largestPerimeter);
    }
    
    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileName = getFileWithLargestPerimeter();
        System.out.println("File with the largest perimeter: " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        
        double peri = getPerimeter(triangle);
        
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3) {
            break;
        }
        
        //System.out.println("index " + index);
        
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+3);
        
        //System.out.println("index after updating " + index);
    }
    }
    
    public void test() {
    //findAbc("abcd");
    //findAbc("abcdabc");
    //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    findAbc("abcabcabcabca");
    }
}
