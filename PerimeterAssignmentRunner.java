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

    public int getNumPoints(Shape s) {
        // Initialize a counter to 0
        int numPoints = 0;
        // Iterate through each point in the shape and increment the counter
        for (Point point : s.getPoints()) {
            numPoints++;
        }
        // Return the total number of points
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Get the perimeter of the shape
        double perimeter = getPerimeter(s);
        // Get the number of points in the shape
        int numPoints = getNumPoints(s);
        // Calculate the average side length
        double averageLength = perimeter / numPoints;
        // Return the average side length
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;

        // Iterate through each point currPt in the shape
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);

            // Check if the current distance is greater than the current largestSide
            if (currDist > largestSide) {
                // Update largestSide if the current distance is larger
                largestSide = currDist;
            }

            // Update prevPt to be currPt
            prevPt = currPt;
        }

        // largestSide is the answer
        return largestSide;

    }
    

    public double getLargestX(Shape s) {
        double largestX = 0.0;

        // Iterate through each point in the shape
        for (Point currPt : s.getPoints()) {
            // Get the x component of the current point
            double currX = currPt.getX();

            // Check if the current x value is greater than the current largestX
            if (currX > largestX) {
                // Update largestX if the current x value is larger
                largestX = currX;
            }
        }

        // largestX is the answer
        return largestX;   
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;

        // Iterate through each file in the directory
        for (File f : dr.selectedFiles()) {
            // Create a FileResource for each file
            FileResource fr = new FileResource(f);

            // Create a Shape from the FileResource
            Shape s = new Shape(fr);

            // Get the perimeter of the shape
            double perimeter = getPerimeter(s);

            // Update largestPerimeter if the current perimeter is larger
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }
        }

        // largestPerimeter is the answer
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        
        // Iterate through each file in the directory
        for (File f : dr.selectedFiles()) {
            // Create a FileResource for each file
            FileResource fr = new FileResource(f);

            // Create a Shape from the FileResource
            Shape s = new Shape(fr);

            // Get the perimeter of the shape
            double perimeter = getPerimeter(s);

            // Update temp if the current perimeter is larger
            if (temp == null || perimeter > getPerimeter(new Shape(new FileResource(temp)))) {
                temp = f;
            }
        }
        
        // Return the name of the file with the largest perimeter
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        // Call getNumPoints and print the result
        int numPoints = getNumPoints(s);
        System.out.println("Number of points in the shape: " + numPoints);

        // Call getAverageLength and print the result
        double averageLength = getAverageLength(s);
        System.out.println("Average side length: " + averageLength);
        
        // Call getLargestSide and print the result
        double largestSide = getLargestSide(s);
        System.out.println("Largest side length: " + largestSide);

        // Call getLargestX and print the result
        double largestX = getLargestX(s);
        System.out.println("Largest X value: " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Call getLargestPerimeterMultipleFiles and store the result
        double largestPerimeter = getLargestPerimeterMultipleFiles();

        // Print the result
        System.out.println("Largest perimeter across all files: " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Call getFileWithLargestPerimeter and store the result
        String fileName = getFileWithLargestPerimeter();

        // Print the result
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
        
        // Uncomment the line below to run testPerimeter
        //pr.testPerimeter();

        // Uncomment the line below to run testPerimeterMultipleFiles
        pr.testPerimeterMultipleFiles();

        // Uncomment the line below to run testFileWithLargestPerimeter
        pr.testFileWithLargestPerimeter();
    }
}
