import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.cscore.*;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class Gears {
  public static void main(String[] args) {
    // Loads our OpenCV library. This MUST be included
    //System.loadLibrary("opencv_java310");

    // Connect NetworkTables, and get access to the publishing table
    NetworkTable.setClientMode();
    // Set your team number here
    NetworkTable.setTeam(2761);

    NetworkTable.initialize();


    // This is the network port you want to stream the raw received image to
    // By rules, this has to be between 1180 and 1190, so 1185 is a good choice
    int streamPort = 1185;

    // This stores our reference to our mjpeg server for streaming the input image
    MjpegServer inputStream = new MjpegServer("MJPEG Server", streamPort);

    // Selecting a Camera
    // Uncomment one of the 2 following camera options
    // The top one receives a stream from another device, and performs operations based on that
    // On windows, this one must be used since USB is not supported
    // The bottom one opens a USB camera, and performs operations on that, along with streaming
    // the input image so other devices can see it.

    // HTTP Camera
    /*
    // This is our camera name from the robot. this can be set in your robot code with the following command
    // CameraServer.getInstance().startAutomaticCapture("YourCameraNameHere");
    // "USB Camera 0" is the default if no string is specified
    String cameraName = "USB Camera 0";
    HttpCamera camera = setHttpCamera(cameraName, inputStream);
    // It is possible for the camera to be null. If it is, that means no camera could
    // be found using NetworkTables to connect to. Create an HttpCamera by giving a specified stream
    // Note if this happens, no restream will be created
    if (camera == null) {
      camera = new HttpCamera("CoprocessorCamera", "YourURLHere");
      inputStream.setSource(camera);
    }
    */
    
      

    /***********************************************/

    // USB Camera
    
    // This gets the image from a USB camera 
    // Usually this will be on device 0, but there are other overloads
    // that can be used
    UsbCamera camera = setUsbCamera(0, inputStream);
    // Set the resolution for our camera, since this is over USB
    camera.setResolution(640,480);
    

    // This creates a CvSink for us to use. This grabs images from our selected camera, 
    // and will allow us to use those images in opencv
    CvSink imageSink = new CvSink("CV Image Grabber");
    imageSink.setSource(camera);

    // This creates a CvSource to use. This will take in a Mat image that has had OpenCV operations
    // operations 
    CvSource imageSource = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, 640, 480, 30);
    MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);
    cvStream.setSource(imageSource);

    // All Mats and Lists should be stored outside the loop to avoid allocations
    // as they are expensive to create
    Mat inputImage = new Mat();
    
    GripPipeline grip = new GripPipeline();
    
    NetworkTable table = NetworkTable.getTable("Gears"); 
    NetworkTable contourTable = NetworkTable.getTable("Gears");
    // Infinitely process image
    while (true) {
      // Grab a frame. If it has a frame time of 0, there was an error.
      // Just skip and continue
      long frameTime = imageSink.grabFrame(inputImage);
      if (frameTime == 0) continue;

      
      grip.process(inputImage);
      double[] imageSize = {inputImage.width(), inputImage.height()};
      Point imageCenter = new Point(imageSize[0] / 2, imageSize[1] / 2);
      table.putNumberArray("Image Size", imageSize);
      // Below is where you would do your OpenCV operations on the provided image
      // The sample below just changes color source to HSV
      ArrayList<MatOfPoint> pointList = grip.convexHullsOutput();
      table.putNumber("Number of Contours", pointList.size());
      ArrayList<Point> centerList = new ArrayList<Point>();
      for (int i = 0; i < pointList.size(); i++) {
    	  contourTable = (NetworkTable) table.getSubTable("Contour " + (i + 1));
    	  
    	  Rect rect = Imgproc.boundingRect(pointList.get(i));
    	  double[] size = {rect.width, rect.height};
    	  contourTable.putNumberArray("Size", size);
    	  double[] center = {rect.width / 2 + rect.x, rect.height / 2 + rect.y};
    	  contourTable.putNumberArray("Center", center);
    	  centerList.add(new Point(center[0], center[1]));
    	  
    	  Scalar color = new Scalar(0, 0, 255);
    	  Imgproc.rectangle(inputImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), color, 2);
      }
      
      Point gearCenter = new Point(0,0);
      for (Point center: centerList) {
    	  gearCenter.x = gearCenter.x + center.x;
    	  gearCenter.y = gearCenter.y + center.y;
      }
      gearCenter.x = gearCenter.x / (centerList.size());
      gearCenter.y = gearCenter.y / (centerList.size());
      double[] gearCenterArray = {gearCenter.x, gearCenter.y};
      table.putNumberArray("GearCenter", gearCenterArray);
      double[] centerDifference = {imageCenter.x - gearCenter.x, imageCenter.y - gearCenter.y};
      table.putNumberArray("CenterDifference", centerDifference);
      
      Scalar color = new Scalar(0, 0, 255);
      Imgproc.circle(inputImage, gearCenter, 1, color, 3);
      
      // Here is where you would write a processed image that you want to restreams
      // This will most likely be a marked up image of what the camera sees
      // For now, we are just going to stream the HSV image
      imageSource.putFrame(inputImage);
    }
  }

private static UsbCamera setUsbCamera(int cameraId, MjpegServer server) {
    // This gets the image from a USB camera 
    // Usually this will be on device 0, but there are other overloads
    // that can be used
    UsbCamera camera = new UsbCamera("CoprocessorCamera", cameraId);
    server.setSource(camera);
    return camera;
  }
}