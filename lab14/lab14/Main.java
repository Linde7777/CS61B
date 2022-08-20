package lab14;

import lab14lib.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/*
		Generator generator = new SineWaveGenerator(200);
		GeneratorDrawer gd = new GeneratorDrawer(generator);
		gd.draw(4096);

		 */
		Generator generator = new SawToothGenerator(512);
		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
		gav.drawAndPlay(4096, 1000000);
	}
} 