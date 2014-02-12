package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class Camera {
	AxisCamera camera;
	//ParticleAnalysisReport[] orderedParticles;
	int firstsWidth, pixelCentre, close;
	int deadBand, cameraCenterX, cameraCenterY, camHeight, deadBandPlusX, deadBandMinusX, deadBandPlusY, deadBandMinusY;
	AnalogChannel ultraSonic;
	ParticleAnalysisReport largestParticle;
	Relay relay;
	BinaryImage binaryImage;
	int camWidth;

	public Camera() {
		camera = AxisCamera.getInstance();
		camera.writeResolution(AxisCamera.ResolutionT.k320x240);
		//camera.writeBrightness(50);
		relay = new Relay(Config.LIGHTS);
		relay.setDirection(Relay.Direction.kReverse);
		camHeight = camera.getResolution().height;

		cameraCenterX = camWidth / 2;
		cameraCenterY = camHeight / 2;
		deadBand = 20;
		deadBandMinusX = cameraCenterX - deadBand;
		deadBandPlusX = cameraCenterX + deadBand;
		deadBandMinusY = cameraCenterY - deadBand;
		deadBandPlusY = cameraCenterY + deadBand;
	}

	public ParticleAnalysisReport[] getLargestParticle(int[] imageValues) {

		ColorImage colorImage;
		BinaryImage thresholdImage, convexImage, smallObjects;
		ParticleAnalysisReport[] particles = null;

		try {
			relay.set(Relay.Value.kOn);
			colorImage = camera.getImage();

			thresholdImage = colorImage.thresholdRGB(imageValues[0], imageValues[1], imageValues[2], imageValues[3], imageValues[4], imageValues[5]);

			convexImage = thresholdImage.convexHull(false);
			smallObjects = convexImage.removeSmallObjects(false, 1);
			particles = smallObjects.getOrderedParticleAnalysisReports();

			colorImage.free();
			thresholdImage.free();
			convexImage.free();
			smallObjects.free();

		} catch (AxisCameraException ex) {
			ex.printStackTrace();
		} catch (NIVisionException ex) {
			System.out.println("ZOMG ERROR " + ex.getMessage());
		} finally {

		}

		return particles;

	}
}
