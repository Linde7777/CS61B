package lab14;

import lab14lib.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
		/*
		Generator generator = new SawToothGenerator(512);
		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
		gav.drawAndPlay(4096, 1000000);
		 */

        Generator generator = new StrangeBitwiseGenerator(200);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);

    }
} 