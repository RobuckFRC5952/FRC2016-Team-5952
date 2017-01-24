import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;


import org.usfirst.frc.team5952.robot.visionSystem.CameraManager;


public class Main {

	static {
		//loadProperties();
	}
	static Properties prop;

	private static void loadProperties() {
		prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream("/home/pi/Robot2017/config.properties");
		} catch (FileNotFoundException e1) {
			System.out.println("Cannot found properties files");
			e1.printStackTrace();
		}
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Cannot found properties files");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Cannot found properties files");
		}
	}

	public static void main(String[] args) {
		
		// Loads our OpenCV library. This MUST be included
		//System.loadLibrary("opencv_java310");		

		if (prop != null) {

			CameraManager.getInstance().setTeamnumber(Integer.parseInt(prop.getProperty("teamnumber")));
			CameraManager.getInstance().setInputstreamport(Integer.parseInt(prop.getProperty("inputstreamport")));
			CameraManager.getInstance().setCameraName(prop.getProperty("networktablename"));

			try {
				System.out.println(prop.getProperty("networktablename")+" IP Adress: "+InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				System.out.println("Cannot found Network Card");
				e.printStackTrace();
			}
			
		}
		try {
			System.out.println("System IP Adress: "+InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("Cannot found Network Card");
			e.printStackTrace();
		}
		CameraManager.getInstance().init();
		CameraManager.getInstance().startPlayback();
		
	}

}